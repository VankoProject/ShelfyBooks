package com.kliachenko.data.cloud.cloudResponse

import com.google.gson.annotations.SerializedName
import com.kliachenko.data.localCache.MapBook
import com.kliachenko.data.localCache.MapSeller
import com.kliachenko.data.mapper.BookMapper
import com.kliachenko.data.mapper.SellerMapper

data class BookListResponse(
    @SerializedName("results")
    val result: BookListResult
)

data class BookListResult(
    @SerializedName("list_name")
    val listName: String,

    @SerializedName("books")
    val books: List<BookCloud>
)

data class BookCloud(
    @SerializedName("primary_isbn13")
    private val bookId: String,
    @SerializedName("title")
    private val title: String,
    @SerializedName("description")
    private val description: String,
    @SerializedName("author")
    private val author: String,
    @SerializedName("publisher")
    private val publisher: String,
    @SerializedName("book_image")
    private val imageUrl: String,
    @SerializedName("rank")
    private val rank: Int,
    @SerializedName("buy_links")
    private val sellers: List<SellerLinkCloud>
) : MapBook {
    override fun <T : Any> map(categoryName: String, mapper: BookMapper<T>): T {
        return mapper.map(
            bookId = bookId,
            categoryListName = categoryName,
            title = title,
            description = description,
            author = author,
            publisher = publisher,
            imageUrl = imageUrl,
            rank = rank
        )
    }

    fun sellers(): List<SellerLinkCloud> = sellers

    fun bookId() = bookId

}

data class SellerLinkCloud(
    @SerializedName("name")
    private val sellerName: String,
    @SerializedName("url")
    private val url: String,
) : MapSeller {
    override fun <T : Any> map(bookId: String, mapper: SellerMapper<T>): T {
        return mapper.map(
            bookId = bookId,
            sellerName = sellerName,
            url = url
        )
    }

}


