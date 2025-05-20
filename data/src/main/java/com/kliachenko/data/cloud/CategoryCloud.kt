package com.kliachenko.data.cloud

import com.google.gson.annotations.SerializedName

data class OverviewResponse(
    @SerializedName("results")
    val result: BestsellersResult
)

data class BestsellersResult(
    @SerializedName("bestsellers_date")
    val bestsellersDate: String,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("lists")
    val categoriesList: List<CategoryCloud>
)

data class CategoryCloud(
    @SerializedName("list_name")
    val listName: String,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("display_name")
    val categoryName: String,
    @SerializedName("normal_list_ends_at")
    val booksCount: Int,
    @SerializedName("updated")
    val updatedPeriod: String,
    @SerializedName("books")
    val booksList: List<BookCloud>
)

