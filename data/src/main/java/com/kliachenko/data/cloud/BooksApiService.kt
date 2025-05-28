package com.kliachenko.data.cloud

import com.kliachenko.data.cloud.cloudResponse.BookListResponse
import com.kliachenko.data.cloud.cloudResponse.OverviewResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApiService {

    @GET(ENDPOINT_OVERVIEW)
    suspend fun overview(): OverviewResponse

    @GET(ENDPOINT_BOOKS_BY_CATEGORY)
    suspend fun booksByCategory(
        @Path("list") categoryId: String
    ): BookListResponse

    companion object {
        private const val ENDPOINT_OVERVIEW = "lists/overview.json"
        private const val ENDPOINT_BOOKS_BY_CATEGORY = "lists/current/{list}.json"
    }

}