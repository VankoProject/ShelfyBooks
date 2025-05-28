package com.kliachenko.presentation.books.model.mapper

import com.kliachenko.domain.model.SellerMapper
import com.kliachenko.presentation.books.model.SellerUi
import javax.inject.Inject

class SellerMapperToUi @Inject constructor() : SellerMapper<SellerUi> {

    override fun map(name: String, url: String): SellerUi {
        return SellerUi.Base(name, url)
    }

}