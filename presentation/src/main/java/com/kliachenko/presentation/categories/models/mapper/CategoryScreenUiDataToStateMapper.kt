package com.kliachenko.presentation.categories.models.mapper

import com.kliachenko.presentation.categories.CategoriesUiState
import com.kliachenko.presentation.categories.models.CategoryScreenUiData
import com.kliachenko.presentation.categories.models.CategoryUi
import javax.inject.Inject

class CategoryScreenUiDataToStateMapper @Inject constructor() :
    CategoryScreenUiData.Mapper<CategoriesUiState> {

    override fun map(
        publishedDate: String,
        categories: List<CategoryUi>
    ) = CategoriesUiState.Success(publishedDate, categories)

}