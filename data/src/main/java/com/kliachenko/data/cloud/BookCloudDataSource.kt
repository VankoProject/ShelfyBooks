package com.kliachenko.data.cloud

import java.net.UnknownHostException
import javax.inject.Inject

interface BookCloudDataSource {

    suspend fun overview(): BestsellersResult

    class Base @Inject constructor(
        private val service: BooksApiService,
    ) : BookCloudDataSource {
        override suspend fun overview(): BestsellersResult {
            return service.overview().result
        }
    }

    class Fake @Inject constructor() : BookCloudDataSource {

        private var firstTime: Boolean = true

        override suspend fun overview(): BestsellersResult {
            if (firstTime) {
                firstTime = false
                throw UnknownHostException()
            }
            return BestsellersResult(
                bestsellersDate = "2025-05-20",
                publishedDate = "2025-05-27",
                categoriesList = listOf(
                    CategoryCloud(
                        listName = "hardcover-fiction",
                        publishedDate = "2025-05-27",
                        categoryName = "Hardcover Fiction",
                        booksCount = 15,
                        updatedPeriod = "WEEKLY",
                        booksList = listOf(
                            BookCloud(
                                title = "First Fake Book Title",
                                description = "First fake book for testing.",
                                author = "Jane Doe",
                                publisher = "Fake House",
                                imageUrl = "https://example.com/fake.jpg",
                                rank = 1,
                                buyLinks = listOf(
                                    BuyLinkCloud(
                                        sellerName = "Amazon",
                                        url = "https://example.com/buy"
                                    )
                                )
                            ),
                            BookCloud(
                                title = "Second Fake Book Title",
                                description = "Second fake book for testing.",
                                author = "John Smith",
                                publisher = "Fake House",
                                imageUrl = "https://example.com/second.jpg",
                                rank = 2,
                                buyLinks = listOf(
                                    BuyLinkCloud(
                                        sellerName = "Apple Books",
                                        url = "https://example.com/buy2"
                                    )
                                )
                            )
                        )
                    )
                )
            )
        }
    }

}