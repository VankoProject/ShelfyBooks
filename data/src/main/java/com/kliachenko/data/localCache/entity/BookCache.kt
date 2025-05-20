package com.kliachenko.data.localCache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "books",
    foreignKeys = [
        ForeignKey(
            entity = CategoryCache::class,
            parentColumns = ["list_name"],
            childColumns = ["category_list_name"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("category_list_name")]
)
data class BookCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "category_list_name")
    val categoryListName: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "publisher")
    val publisher: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "rank")
    val rank: Int
)
