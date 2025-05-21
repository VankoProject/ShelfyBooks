package com.kliachenko.data

import com.kliachenko.data.cloud.BookCloudDataSource
import com.kliachenko.data.core.HandleError
import com.kliachenko.data.localCache.BookCacheDataSource
import com.kliachenko.data.localCache.CategoryCacheDataSource
import com.kliachenko.data.localCache.SellersCacheDataSource
import com.kliachenko.data.mapper.BookMapper
import com.kliachenko.data.mapper.CategoryDomain
import com.kliachenko.data.mapper.CategoryMapper
import com.kliachenko.data.mapper.SellerMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CategoryRepository {

    fun categories(): Flow<LoadResult<CategoryDomain>>
}

class CategoryRepositoryImpl @Inject constructor(
    private val bookCloudDataSource: BookCloudDataSource,
    private val categoryCacheDataSource: CategoryCacheDataSource.Mutable,
    private val bookCacheDataSource: BookCacheDataSource.Save,
    private val sellersCacheDataSource: SellersCacheDataSource.Save,
    private val mapCategoryToCache: CategoryMapper.ToCache,
    private val mapCategoryToDomain: CategoryMapper.ToDomain,
    private val mapBookToCache: BookMapper.ToCache,
    private val mapSellersToCache: SellerMapper.ToCache,
    private val handleError: HandleError<String>
) : CategoryRepository {

    override fun categories(): Flow<LoadResult<CategoryDomain>> =
        flow {
            try {
                val cloudResponse = bookCloudDataSource.overview()
                val categories = cloudResponse.categoriesList
                if (categories.isEmpty()) emit(LoadResult.Empty())
                else {
                    val cacheCategories = categories.map { it.map(mapCategoryToCache) }
                    categoryCacheDataSource.save(cacheCategories)
                    val cacheBooks = categories.flatMap { category ->
                        category.bookList().map { it.map(category.categoryId(), mapBookToCache) }
                    }
                    bookCacheDataSource.save(cacheBooks)

                    val cacheSellers = categories.flatMap { category ->
                        category.bookList().flatMap { book ->
                            book.sellers().map { seller ->
                                seller.map(book.bookId(), mapSellersToCache)
                            }
                        }
                    }
                    sellersCacheDataSource.save(cacheSellers)
                }
                val domainFlow = categoryCacheDataSource.read()
                    .map { categoryList -> categoryList.map { it.map(mapCategoryToDomain) } }
                emitAll(domainFlow.map { LoadResult.Success(it) })
            } catch (e: Exception) {
                emit(LoadResult.Error(handleError.handle(e)))
            }
        }

}

interface LoadResult<T : Any> {

    fun <S : Any> map(mapper: Mapper<T, S>): S

    interface Mapper<T : Any, S : Any> {
        fun mapSuccess(data: List<T>): S
        fun mapError(errorMessage: String): S
        fun mapEmpty(): S
    }

    class Empty<T : Any> : LoadResult<T> {
        override fun <S : Any> map(mapper: Mapper<T, S>) = mapper.mapEmpty()
    }

    data class Error<T : Any>(private val errorMessage: String) : LoadResult<T> {
        override fun <S : Any> map(mapper: Mapper<T, S>) = mapper.mapError(errorMessage)
    }

    data class Success<T : Any>(private val data: List<T>) : LoadResult<T> {
        override fun <S : Any> map(mapper: Mapper<T, S>) = mapper.mapSuccess(data)
    }

}