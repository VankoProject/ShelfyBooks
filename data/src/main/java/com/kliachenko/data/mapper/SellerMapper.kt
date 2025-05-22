package com.kliachenko.data.mapper

import com.kliachenko.data.localCache.entity.SellerLinkCache
import com.kliachenko.domain.model.SellerDomain
import javax.inject.Inject

interface SellerMapper<T : Any> {

    fun map(
        bookId: String,
        sellerName: String,
        url: String,
    ): T

    interface ToCache : SellerMapper<SellerLinkCache> {
        class Base @Inject constructor() : ToCache {
            override fun map(bookId: String, sellerName: String, url: String) =
                SellerLinkCache(
                    bookId = bookId,
                    sellerName = sellerName,
                    url = url
                )
        }
    }

    interface ToDomain : SellerMapper<SellerDomain> {
        class Base @Inject constructor() : ToDomain {
            override fun map(bookId: String, sellerName: String, url: String): SellerDomain {
                return SellerDomain(name = sellerName, url = url)
            }

        }
    }
}