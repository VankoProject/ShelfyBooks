package com.kliachenko.data.mapper

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SellersMapperToDomainTest {

    private lateinit var mapper: SellerMapper.ToDomain

    @Before
    fun setup() {
        mapper = SellerMapper.ToDomain.Base()
    }

    @Test
    fun mapCorrectly() {
        val bookId = "FakeBookId"
        val sellerName = "Amazon"
        val url = "http:/amazon.com"

        val result = mapper.map(bookId = bookId, sellerName = sellerName, url = url)

        val expected = SellerDomain(name = sellerName, url = url)

        Assert.assertEquals(expected, result)
    }
}