package com.kliachenko.data.mapper

import com.kliachenko.data.localCache.entity.BookCache
import javax.inject.Inject

interface BookMapper<T : Any> {

    fun map(
        bookId: String,
        categoryListName: String,
        title: String,
        description: String,
        author: String,
        publisher: String,
        imageUrl: String,
        rank: Int,
    ): T

    interface ToCache : BookMapper<BookCache> {
        class Base @Inject constructor() : ToCache {
            override fun map(
                bookId: String,
                categoryListName: String,
                title: String,
                description: String,
                author: String,
                publisher: String,
                imageUrl: String,
                rank: Int
            ) = BookCache(
                bookId = bookId,
                categoryListName = categoryListName,
                title = title,
                description = description,
                author = author,
                publisher = publisher,
                imageUrl = imageUrl,
                rank = rank
            )
        }
    }

}

data class BookDomain(
    private val title: String,
    private val description: String,
    private val author: String,
    private val publisher: String,
    private val imageUrl: String,
    private val rank: Int,
    private val sellers: List<SellerDomain>
)