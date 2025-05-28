package com.kliachenko.data.cloud

import com.kliachenko.data.cloud.cloudResponse.BestsellersResult
import com.kliachenko.data.cloud.cloudResponse.CategoryCloud
import java.net.UnknownHostException
import javax.inject.Inject

interface CategoryCloudDataSource {

    suspend fun overview(): BestsellersResult

    class Base @Inject constructor(
        private val service: BooksApiService,
    ) : CategoryCloudDataSource {

        override suspend fun overview(): BestsellersResult {
            return service.overview().result
        }

    }

    class Fake @Inject constructor() : CategoryCloudDataSource {

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
                        categoryId = "hardcover-fiction",
                        categoryName = "Hardcover Fiction",
                        booksCount = 15,
                        updatePeriod = "WEEKLY",
                    )
                )
            )
        }
    }

}