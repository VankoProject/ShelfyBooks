package com.kliachenko.data.cloud

import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {

    @GET("lists/overview.json")
    suspend fun overview(
        @Query(QUERY_PARAM_API_KEY) apiKey: String
    ): OverviewResponse

    companion object {
        private const val QUERY_PARAM_API_KEY = "api-key"
    }
}