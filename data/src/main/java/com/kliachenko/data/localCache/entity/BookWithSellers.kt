package com.kliachenko.data.localCache.entity

import androidx.room.Embedded
import androidx.room.Relation

data class BookWithSellers(
    @Embedded val book: BookCache,
    @Relation(
        parentColumn = "id",
        entityColumn = "book_id"
    )
    val sellers: List<SellerLinkCache>
)
