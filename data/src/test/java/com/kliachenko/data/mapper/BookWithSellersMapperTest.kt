package com.kliachenko.data.mapper

import com.kliachenko.data.localCache.entity.BookCache
import com.kliachenko.data.localCache.entity.SellerLinkCache
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BookWithSellersMapperTest {

    private lateinit var bookWithSellersMapper: BookWithSellersMapper.ToDomain
    private lateinit var sellersMapper: FakeSellersMapper

    @Before
    fun setup() {
        sellersMapper = FakeSellersMapper()
        bookWithSellersMapper = BookWithSellersMapper.ToDomain.Base(
            sellersMapper
        )
    }

    @Test
    fun mapCorrectly() {
        val book = BookCache(
            bookId = "isbn-123",
            categoryListName = "fakeCategory",
            title = "fakeTitle",
            description = "fakeDescription",
            author = "fakeAuthor",
            publisher = "fakePublisher",
            imageUrl = "http://fakeImage",
            rank = 1
        )

        val sellers = listOf(
            SellerLinkCache(
                id = 1,
                bookId = "isbn-123",
                sellerName = "Amazon",
                url = "http:/amazon.com"
            ),
            SellerLinkCache(
                id = 2,
                bookId = "isbn-123",
                sellerName = "Apple",
                url = "http:/apple.com"
            ),
        )

        val expected = BookDomain(
            title = "fakeTitle",
            description = "fakeDescription",
            author = "fakeAuthor",
            publisher = "fakePublisher",
            imageUrl = "http://fakeImage",
            rank = 1,
            sellers = listOf(
                SellerDomain(name = "Fake Amazon", url = "Fake http:/amazon.com"),
                SellerDomain(name = "Fake Apple", url = "Fake http:/apple.com")
            )
        )

        val result = bookWithSellersMapper.map(book, sellers)

        Assert.assertEquals(expected, result)
    }
}

private class FakeSellersMapper: SellerMapper.ToDomain{

    override fun map(bookId: String, sellerName: String, url: String): SellerDomain {
        return SellerDomain(
            name = "Fake $sellerName",
            url = "Fake $url"
        )
    }

}