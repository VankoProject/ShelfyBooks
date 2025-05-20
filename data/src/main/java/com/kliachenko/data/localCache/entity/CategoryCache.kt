package com.kliachenko.data.localCache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryCache(
    @PrimaryKey
    @ColumnInfo(name = "list_name")
    val listName: String,

    @ColumnInfo(name = "published_date")
    val publishedDate: String,

    @ColumnInfo(name = "category_name")
    val categoryName: String,

    @ColumnInfo(name = "books_count")
    val booksCount: Int,

    @ColumnInfo(name = "update_period")
    val updatePeriod: String
)
