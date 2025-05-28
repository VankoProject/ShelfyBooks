package com.kliachenko.shelfybooks.di.data

import com.kliachenko.data.cloud.ApiKeyInterceptor
import com.kliachenko.data.cloud.BooksApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.nytimes.com/svc/books/v3/"

    @Provides
    @Named("nyt_api_key")
    fun provideApiKey(): String = com.kliachenko.data.BuildConfig.NYT_API_KEY

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(
        @Named("nyt_api_key") apiKey: String
    ): ApiKeyInterceptor = ApiKeyInterceptor(apiKey)

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: ApiKeyInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideBooksApiService(retrofit: Retrofit): BooksApiService =
        retrofit.create(BooksApiService::class.java)

}