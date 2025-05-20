package com.kliachenko.data.mapper

import com.kliachenko.data.localCache.entity.BookCache
import com.kliachenko.data.localCache.entity.SellerLinkCache
import javax.inject.Inject

interface BookWithSellersMapper<T> {

    fun map(
        book: BookCache,
        sellers: List<SellerLinkCache>
    ): T

    interface ToDomain : BookWithSellersMapper<BookDomain> {
        class Base @Inject constructor(
            private val sellerMapper: SellerMapper<SellerDomain>
        ) : ToDomain {
            override fun map(book: BookCache, sellers: List<SellerLinkCache>): BookDomain {
                return BookDomain(
                    title = book.title,
                    description = book.description,
                    author = book.author,
                    publisher = book.publisher,
                    imageUrl = book.imageUrl,
                    rank = book.rank,
                    sellers = sellers.map {
                        it.map(book.id, sellerMapper)
                    }
                )
            }
        }
    }

}