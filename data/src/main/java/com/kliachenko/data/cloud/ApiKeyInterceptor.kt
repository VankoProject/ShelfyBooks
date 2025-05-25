package com.kliachenko.data.cloud

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Named

class ApiKeyInterceptor @Inject constructor(
    @Named("nyt_api_key") private val apiKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val url = origin.url.newBuilder()
            .addQueryParameter("api-key", apiKey)
            .build()
        val newRequest = origin.newBuilder()
            .url(url)
            .build()
        return chain.proceed(newRequest)
    }

}