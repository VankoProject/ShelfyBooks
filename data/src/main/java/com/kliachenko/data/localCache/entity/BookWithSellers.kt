package com.kliachenko.data.localCache.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.kliachenko.data.localCache.MapBookWithSellers
import com.kliachenko.data.mapper.BookWithSellersMapper

data class BookWithSellers(
    @Embedded
    val book: BookCache,
    @Relation(
        parentColumn = "bookId",
        entityColumn = "book_id"
    )
    val sellers: List<SellerLinkCache>
) : MapBookWithSellers {
    override fun <T : Any> map(mapper: BookWithSellersMapper<T>): T {
        return mapper.map(
            book = book,
            sellers = sellers
        )
    }
}
