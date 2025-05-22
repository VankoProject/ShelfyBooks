package com.kliachenko.data.cloud

import com.kliachenko.data.cloud.cloudResponse.BookCloud
import com.kliachenko.data.cloud.cloudResponse.BookListResponse
import com.kliachenko.data.cloud.cloudResponse.BookListResult
import com.kliachenko.data.cloud.cloudResponse.SellerLinkCloud
import java.net.UnknownHostException
import javax.inject.Inject

interface BookCloudDataSource {

    suspend fun books(categoryId: String): BookListResponse

    class Base @Inject constructor(
        private val service: BooksApiService,
    ) : BookCloudDataSource {
        override suspend fun books(categoryId: String): BookListResponse {
            return service.booksByCategory(categoryId)
        }

    }

    class Fake @Inject constructor() : BookCloudDataSource {

        private var firstTime: Boolean = true

        override suspend fun books(categoryId: String): BookListResponse {
            if (firstTime) {
                firstTime = false
                throw UnknownHostException()
            }
            return BookListResponse(
                result = BookListResult(
                    listName = "Hardcover Fiction",
                    books = listOf(
                        BookCloud(
                            bookId = "123",
                            title = "First Fake Book Title",
                            description = "First fake book for testing.",
                            author = "Jane Doe",
                            publisher = "Fake House",
                            imageUrl = "https://example.com/fake.jpg",
                            rank = 1,
                            sellers = listOf(
                                SellerLinkCloud(
                                    sellerName = "Amazon",
                                    url = "https://example.com/buy"
                                )
                            )
                        ),
                        BookCloud(
                            bookId = "234",
                            title = "Second Fake Book Title",
                            description = "Second fake book for testing.",
                            author = "John Smith",
                            publisher = "Fake House",
                            imageUrl = "https://example.com/second.jpg",
                            rank = 2,
                            sellers = listOf(
                                SellerLinkCloud(
                                    sellerName = "Apple Books",
                                    url = "https://example.com/buy2"
                                )
                            )
                        )
                    )
                )
            )
        }
    }

}