package com.kliachenko.data.cloud

import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {

    @GET("lists/overview.json")
    suspend fun overview(): OverviewResponse

}