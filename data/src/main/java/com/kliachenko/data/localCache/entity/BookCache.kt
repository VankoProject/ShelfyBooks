package com.kliachenko.data.localCache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kliachenko.data.localCache.MapBook
import com.kliachenko.data.mapper.BookMapper

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
    @PrimaryKey
    val bookId: String,

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
) : MapBook {
    override fun <T : Any> map(categoryName: String, mapper: BookMapper<T>): T {
        return mapper.map(
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
