package com.kliachenko.data.localCache

import com.kliachenko.data.localCache.dao.BookDao
import com.kliachenko.data.localCache.entity.BookCache
import com.kliachenko.data.localCache.entity.BookWithSellers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookCacheDataSource {

    interface Save {
        suspend fun save(books: List<BookCache>)
    }

    interface Read {
        fun read(categoryListName: String): Flow<List<BookWithSellers>>
    }

    interface Mutable : Save, Read

    class Base @Inject constructor(
        private val bookDao: BookDao,
    ) : Mutable {

        override suspend fun save(books: List<BookCache>) {
            return bookDao.insert(books)
        }

        override fun read(categoryListName: String) =
            bookDao.booksWithSellers(categoryListName)
    }

}