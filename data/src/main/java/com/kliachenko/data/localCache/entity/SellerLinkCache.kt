package com.kliachenko.data.localCache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "buy_links",
    foreignKeys = [ForeignKey(
        entity = BookCache::class,
        parentColumns = ["id"],
        childColumns = ["book_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("book_id")]
)
data class SellerLinkCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "book_id")
    val bookId: Int,

    @ColumnInfo(name = "seller_name")
    val sellerName: String,

    @ColumnInfo(name = "url")
    val url: String
)
