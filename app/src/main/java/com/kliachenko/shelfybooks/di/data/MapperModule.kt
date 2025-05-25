package com.kliachenko.shelfybooks.di.data

import com.kliachenko.data.mapper.BookMapper
import com.kliachenko.data.mapper.BookWithSellersMapper
import com.kliachenko.data.mapper.CategoryMapper
import com.kliachenko.data.mapper.SellerMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindCategoryMapperToCache(
        impl: CategoryMapper.ToCache.Base
    ): CategoryMapper.ToCache

    @Binds
    abstract fun bindCategoryMapperToDomain(
        impl: CategoryMapper.ToDomain.Base
    ): CategoryMapper.ToDomain

    @Binds
    abstract fun bindBookMapperToCache(
        impl: BookMapper.ToCache.Base
    ): BookMapper.ToCache

    @Binds
    abstract fun bindSellerMapperToCache(
        impl: SellerMapper.ToCache.Base
    ): SellerMapper.ToCache

    @Binds
    abstract fun bindSellerMapperToDomain(
        impl: SellerMapper.ToDomain.Base
    ): SellerMapper.ToDomain

    @Binds
    abstract fun bindBookWithSellersMapperToDomain(
        impl: BookWithSellersMapper.ToDomain.Base
    ): BookWithSellersMapper.ToDomain

}