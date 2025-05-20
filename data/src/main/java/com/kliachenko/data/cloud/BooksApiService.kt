package com.kliachenko.data.cloud

import retrofit2.http.GET

interface BooksApiService {

    @GET("lists/overview.json")
    suspend fun overview(): OverviewResponse

}