package com.kliachenko.data.cloud.cloudResponse

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
    @SerializedName("list_name_encoded")
    private val categoryId: String,
    @SerializedName("display_name")
    private val categoryName: String,
    @SerializedName("normal_list_ends_at")
    private val booksCount: Int,
    @SerializedName("updated")
    private val updatePeriod: String,
) : MapCategory {
    override fun <T : Any> map(mapper: CategoryMapper<T>): T {
        return mapper.map(
            categoryId = categoryId,
            categoryName = categoryName,
            booksCount = booksCount,
            updatedPeriod = updatePeriod
        )
    }

}

