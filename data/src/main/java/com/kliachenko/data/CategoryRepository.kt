package com.kliachenko.data

import com.kliachenko.data.cloud.CategoryCloudDataSource
import com.kliachenko.data.core.HandleError
import com.kliachenko.data.localCache.CategoryCacheDataSource
import com.kliachenko.data.localCache.MetaInfoCacheDataSource
import com.kliachenko.data.mapper.CategoryDomain
import com.kliachenko.data.mapper.CategoryMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CategoryRepository {

    fun categories(): Flow<LoadResult<CategoryDomain>>

    suspend fun publishedDate(): String
}

class CategoryRepositoryImpl @Inject constructor(
    private val categoryCloudDataSource: CategoryCloudDataSource,
    private val categoryCacheDataSource: CategoryCacheDataSource.Mutable,
    private val metaInfoCacheDataSource: MetaInfoCacheDataSource.Mutable,
    private val mapCategoryToCache: CategoryMapper.ToCache,
    private val mapCategoryToDomain: CategoryMapper.ToDomain,
    private val handleError: HandleError<String>
) : CategoryRepository {

    override fun categories(): Flow<LoadResult<CategoryDomain>> = flow {
        val cached = categoryCacheDataSource.read().first()
        if (cached.isEmpty()) {
            try {
                val cloudResponse = categoryCloudDataSource.overview()
                val categories = cloudResponse.categoriesList
                val publishedDate = cloudResponse.publishedDate

                if (categories.isEmpty()) emit(LoadResult.Empty())
                else {
                    val cacheCategories = categories.map { it.map(mapCategoryToCache) }
                    categoryCacheDataSource.save(cacheCategories)
                    metaInfoCacheDataSource.save(publishedDate)
                }
            } catch (exception: Exception) {
                emit(LoadResult.Error(handleError.handle(exception)))
                return@flow
            }
        }
        emitAll(
            categoryCacheDataSource.read().map { list ->
                LoadResult.Success(list.map { it.map(mapCategoryToDomain) })
            }
        )
    }

    override suspend fun publishedDate() = metaInfoCacheDataSource.read()

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