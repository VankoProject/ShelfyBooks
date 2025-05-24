package com.kliachenko.presentation.di

import com.kliachenko.domain.model.CategoryMapper
import com.kliachenko.domain.model.ScreenDataMapper
import com.kliachenko.presentation.categories.CategoriesUiState
import com.kliachenko.presentation.categories.models.mapper.CategoryMapperToUi
import com.kliachenko.presentation.categories.models.mapper.CategoryScreenDataMapperToUi
import com.kliachenko.presentation.categories.models.CategoryScreenUiData
import com.kliachenko.presentation.categories.models.mapper.CategoryScreenUiDataToStateMapper
import com.kliachenko.presentation.categories.models.CategoryUi
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

}