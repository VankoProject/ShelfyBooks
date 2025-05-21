package com.kliachenko.data.cloud

import com.google.gson.annotations.SerializedName
import com.kliachenko.data.localCache.MapCategory
import com.kliachenko.data.mapper.CategoryMapper

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
    private val listName: String,
    @SerializedName("published_date")
    private val publishedDate: String,
    @SerializedName("display_name")
    private val categoryName: String,
    @SerializedName("normal_list_ends_at")
    private val booksCount: Int,
    @SerializedName("updated")
    private val updatedPeriod: String,
    @SerializedName("books")
    private val booksList: List<BookCloud>
) : MapCategory {
    override fun <T : Any> map(mapper: CategoryMapper<T>): T {
        return mapper.map(
            listName = listName,
            publishedDate = publishedDate,
            categoryName = categoryName,
            booksCount = booksCount,
            updatedPeriod = updatedPeriod
        )
    }

    fun bookList(): List<BookCloud> = booksList

    fun categoryId(): String = listName

}

