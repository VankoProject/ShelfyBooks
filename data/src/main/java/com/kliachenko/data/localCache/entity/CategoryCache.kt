package com.kliachenko.data.localCache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kliachenko.data.localCache.MapCategory
import com.kliachenko.data.mapper.CategoryMapper

@Entity(tableName = "categories")
data class CategoryCache(
    @PrimaryKey
    @ColumnInfo(name = "category_id")
    val categoryId: String,

    @ColumnInfo(name = "category_name")
    val categoryName: String,

    @ColumnInfo(name = "books_count")
    val booksCount: Int,

    @ColumnInfo(name = "update_period")
    val updatePeriod: String
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
