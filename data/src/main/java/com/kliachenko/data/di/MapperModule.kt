package com.kliachenko.data.di

import com.kliachenko.data.mapper.BookMapper
import com.kliachenko.data.mapper.CategoryMapper
import com.kliachenko.data.mapper.SellerMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    fun provideCategoryMapperToCache(): CategoryMapper.ToCache =
        CategoryMapper.ToCache.Base()

    @Provides
    fun provideCategoryMapperToDomain(): CategoryMapper.ToDomain =
        CategoryMapper.ToDomain.Base()

    @Provides
    fun provideBookMapperToCache(): BookMapper.ToCache =
        BookMapper.ToCache.Base()

    @Provides
    fun provideSellerMapperToCache(): SellerMapper.ToCache =
        SellerMapper.ToCache.Base()

}