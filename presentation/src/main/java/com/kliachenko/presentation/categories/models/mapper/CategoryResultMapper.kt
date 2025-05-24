package com.kliachenko.presentation.categories.models.mapper

import com.kliachenko.domain.model.CategoryScreenData
import com.kliachenko.domain.model.ScreenDataMapper
import com.kliachenko.domain.repository.LoadResult
import com.kliachenko.presentation.categories.CategoriesUiState
import com.kliachenko.presentation.categories.models.CategoryScreenUiData
import javax.inject.Inject

class CategoryResultMapper @Inject constructor(
    private val screenMapper: ScreenDataMapper<CategoryScreenUiData>,
    private val uiMapper: CategoryScreenUiData.Mapper<CategoriesUiState>
) : LoadResult.Mapper<CategoryScreenData, CategoriesUiState> {

    override fun mapSuccess(data: CategoryScreenData) = data.map(screenMapper).map(uiMapper)

    override fun mapError(errorMessage: String) = CategoriesUiState.Error(errorMessage)


    override fun mapEmpty() = CategoriesUiState.Empty

}