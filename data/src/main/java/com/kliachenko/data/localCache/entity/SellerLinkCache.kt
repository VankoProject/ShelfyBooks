package com.kliachenko.data.localCache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kliachenko.data.localCache.MapSeller
import com.kliachenko.data.mapper.SellerMapper

@Entity(
    tableName = "sellers",
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
) : MapSeller {
    override fun <T : Any> map(bookId: Int, mapper: SellerMapper<T>): T {
        return mapper.map(
            bookId = bookId,
            sellerName = sellerName,
            url = url
        )
    }
}