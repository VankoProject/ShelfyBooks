package com.kliachenko.data

import com.kliachenko.data.core.HandleError
import com.kliachenko.data.localCache.BookCacheDataSource
import com.kliachenko.data.mapper.BookDomain
import com.kliachenko.data.mapper.BookWithSellersMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface BookRepository {

    fun books(categoryId: String): Flow<LoadResult<BookDomain>>

    class BookRepositoryImpl @Inject constructor(
        private val bookCacheDataSource: BookCacheDataSource.Read,
        private val mapBookToDomain: BookWithSellersMapper.ToDomain,
        private val handleError: HandleError<String>,
    ) : BookRepository {

        override fun books(categoryId: String): Flow<LoadResult<BookDomain>> {
            return bookCacheDataSource.read(categoryId).map { listBookWithSellers ->
                if (listBookWithSellers.isEmpty()) LoadResult.Empty()
                else {
                    val domainBooks = listBookWithSellers.map { it.map(mapBookToDomain) }
                    LoadResult.Success(domainBooks)
                }
            }.catch { emit(LoadResult.Error(handleError.handle(it as Exception))) }
        }
    }
}