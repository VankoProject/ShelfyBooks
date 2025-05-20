package com.kliachenko.data.localCache

import com.kliachenko.data.mapper.BookMapper
import com.kliachenko.data.mapper.BookWithSellersMapper
import com.kliachenko.data.mapper.CategoryMapper
import com.kliachenko.data.mapper.SellerMapper

interface MapCategory {
    fun <T:Any> map(mapper: CategoryMapper<T>): T

}

interface MapBook {
    fun <T: Any> map(categoryName: String, mapper: BookMapper<T>): T

}

interface MapSeller {
    fun <T: Any> map(bookId: Int, mapper: SellerMapper<T>): T

}

interface MapBookWithSellers {
    fun <T: Any> map(mapper: BookWithSellersMapper<T>): T

}