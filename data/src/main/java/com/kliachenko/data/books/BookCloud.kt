package com.kliachenko.data.books

import com.google.gson.annotations.SerializedName

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
    val buyLinks: List<BuyLinkCloud>
)

data class BuyLinkCloud(
    @SerializedName("name")
    val sellerName: String,
    @SerializedName("url")
    val url: String,
)

