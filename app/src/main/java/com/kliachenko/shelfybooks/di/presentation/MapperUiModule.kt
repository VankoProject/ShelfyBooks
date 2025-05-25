package com.kliachenko.shelfybooks.di.presentation

import com.kliachenko.domain.model.BookMapper
import com.kliachenko.domain.model.CategoryMapper
import com.kliachenko.domain.model.ScreenDataMapper
import com.kliachenko.domain.model.SellerMapper
import com.kliachenko.presentation.books.model.BookUi
import com.kliachenko.presentation.books.model.SellerUi
import com.kliachenko.presentation.books.model.mapper.BookMapperToUi
import com.kliachenko.presentation.books.model.mapper.SellerMapperToUi
import com.kliachenko.presentation.categories.CategoriesUiState
import com.kliachenko.presentation.categories.models.CategoryScreenUiData
import com.kliachenko.presentation.categories.models.CategoryUi
import com.kliachenko.presentation.categories.models.mapper.CategoryMapperToUi
import com.kliachenko.presentation.categories.models.mapper.CategoryScreenDataMapperToUi
import com.kliachenko.presentation.categories.models.mapper.CategoryScreenUiDataToStateMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UiMapperModule {

    @Binds
    fun bindCategoryMapper(
        impl: CategoryMapperToUi
    ): CategoryMapper<CategoryUi>

    @Binds
    fun bindScreenDataMapper(
        impl: CategoryScreenDataMapperToUi
    ): ScreenDataMapper<CategoryScreenUiData>

    @Binds
    fun bindCategoryScreenUiMapper(
        impl: CategoryScreenUiDataToStateMapper
    ): CategoryScreenUiData.Mapper<CategoriesUiState>

    @Binds
    fun bindBookMapperToUi(
        impl: BookMapperToUi
    ): BookMapper<BookUi>

    @Binds
    fun bindSellerMapperToUi(
        impl: SellerMapperToUi
    ): SellerMapper<SellerUi>

}