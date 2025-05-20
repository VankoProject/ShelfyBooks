package com.kliachenko.data.cloud

import com.google.gson.annotations.SerializedName
import com.kliachenko.data.localCache.MapBook
import com.kliachenko.data.localCache.MapSeller
import com.kliachenko.data.mapper.BookMapper
import com.kliachenko.data.mapper.SellerMapper

data class BookCloud(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("book_image")
    val imageUrl: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("buy_links")
    val buyLinks: List<SellerLinkCloud>
) : MapBook {
    override fun <T : Any> map(categoryName: String, mapper: BookMapper<T>): T {
        return mapper.map(
            categoryListName = categoryName,
            title = title,
            description = description,
            author = author,
            publisher = publisher,
            imageUrl = imageUrl,
            rank = rank
        )
    }

}

data class SellerLinkCloud(
    @SerializedName("name")
    val sellerName: String,
    @SerializedName("url")
    val url: String,
) : MapSeller {
    override fun <T : Any> map(bookId: Int, mapper: SellerMapper<T>): T {
        return mapper.map(
            bookId = bookId,
            sellerName = sellerName,
            url = url
        )
    }

}


