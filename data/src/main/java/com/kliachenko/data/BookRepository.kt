package com.kliachenko.data

import com.kliachenko.data.cloud.BookCloudDataSource
import com.kliachenko.data.core.HandleError
import com.kliachenko.data.localCache.BookCacheDataSource
import com.kliachenko.data.localCache.SellersCacheDataSource
import com.kliachenko.data.mapper.BookMapper
import com.kliachenko.data.mapper.BookWithSellersMapper
import com.kliachenko.data.mapper.SellerMapper
import com.kliachenko.domain.model.BookDomain
import com.kliachenko.domain.repository.BookRepository
import com.kliachenko.domain.repository.LoadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookCloudDataSource: BookCloudDataSource,
    private val bookCacheDataSource: BookCacheDataSource.Mutable,
    private val sellersCacheDataSource: SellersCacheDataSource.Save,
    private val mapBookToCache: BookMapper.ToCache,
    private val mapSellerToCache: SellerMapper.ToCache,
    private val mapBookToDomain: BookWithSellersMapper.ToDomain,
    private val handleError: HandleError<String>,
) : BookRepository {

    override fun books(categoryId: String): Flow<LoadResult<List<BookDomain>>> = flow {
        val cached = bookCacheDataSource.read(categoryId).first()
        if (cached.isEmpty()) {
            try {
                val booksResponse = bookCloudDataSource.books(categoryId)
                val books = booksResponse.result.books
                val cachedBooks = books.map { it.map(categoryId, mapBookToCache) }
                bookCacheDataSource.save(cachedBooks)

                val cachedSellers = books.flatMap { book ->
                    book.sellers().map { seller ->
                        seller.map(book.bookId(), mapSellerToCache)
                    }
                }
                sellersCacheDataSource.save(cachedSellers)
            } catch (exception: Exception) {
                emit(LoadResult.Error(handleError.handle(exception)))
                return@flow
            }
        }
        val mappedBooksFlow: Flow<LoadResult<List<BookDomain>>> =
            bookCacheDataSource.read(categoryId).map { list ->
                LoadResult.Success(list.map { it.map(mapBookToDomain) })
            }

        emitAll(mappedBooksFlow)
    }
}