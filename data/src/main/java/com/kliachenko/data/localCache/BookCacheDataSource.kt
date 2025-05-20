package com.kliachenko.data.localCache

import com.kliachenko.data.localCache.dao.BookDao
import com.kliachenko.data.localCache.dao.SellerDao
import com.kliachenko.data.localCache.entity.BookCache
import com.kliachenko.data.localCache.entity.BookWithSellers
import com.kliachenko.data.localCache.entity.SellerLinkCache
import javax.inject.Inject

class BookCacheDataSource {

    interface Save {
        suspend fun save(books: List<BookCache>): List<Long>
        suspend fun save(sellers: List<SellerLinkCache>)
    }

    interface Read {
        suspend fun read(categoryListName: String): List<BookWithSellers>
    }

    interface Mutable : Save, Read

    class Base @Inject constructor(
        private val bookDao: BookDao,
        private val sellerDao: SellerDao,
    ) : Mutable {

        override suspend fun save(books: List<BookCache>): List<Long> {
            return bookDao.insert(books)
        }

        override suspend fun save(sellers: List<SellerLinkCache>) {
            sellerDao.insert(sellers)
        }

        override suspend fun read(categoryListName: String) =
            bookDao.booksWithSellers(categoryListName)
    }

}