package com.kliachenko.presentation.categories.models

import com.kliachenko.presentation.categories.CategoriesUiState
import javax.inject.Inject

class CategoryScreenUiDataToStateMapper @Inject constructor() :
    CategoryScreenUiData.Mapper<CategoriesUiState> {

    override fun map(
        publishedDate: String,
        categories: List<CategoryUi>
    ) = CategoriesUiState.Success(publishedDate, categories)

}