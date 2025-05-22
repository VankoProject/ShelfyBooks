package com.kliachenko.data.cloud

import com.kliachenko.data.cloud.cloudResponse.BookListResponse
import com.kliachenko.data.cloud.cloudResponse.OverviewResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApiService {

    @GET("lists/overview.json")
    suspend fun overview(): OverviewResponse

    @GET("lists/current/{list}.json")
    suspend fun booksByCategory(
        @Path("list") categoryId: String
    ): BookListResponse

}