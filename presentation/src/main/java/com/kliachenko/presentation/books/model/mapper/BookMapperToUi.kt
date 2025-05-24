package com.kliachenko.presentation.books.model.mapper

import com.kliachenko.domain.model.BookMapper
import com.kliachenko.domain.model.SellerDomain
import com.kliachenko.domain.model.SellerMapper
import com.kliachenko.presentation.books.model.BookUi
import com.kliachenko.presentation.books.model.SellerUi
import javax.inject.Inject

class BookMapperToUi @Inject constructor(
    private val sellerMapper: SellerMapper<SellerUi>
) : BookMapper<BookUi> {
    override fun map(
        title: String,
        description: String,
        author: String,
        publisher: String,
        imageUrl: String,
        rank: Int,
        sellers: List<SellerDomain>
    ): BookUi {
        val uiSellers = sellers.map { it.map(sellerMapper) }
        return BookUi.Base(title, description, author, publisher, imageUrl, rank, uiSellers)
    }
}