package com.kliachenko.presentation.categories.models.mapper

import com.kliachenko.presentation.categories.CategoriesUiState
import com.kliachenko.presentation.categories.models.CategoryScreenUiData
import com.kliachenko.presentation.categories.models.CategoryUi
import com.kliachenko.presentation.core.FormatedDate
import javax.inject.Inject

class CategoryScreenUiDataToStateMapper @Inject constructor(
    private val formatedDate: FormatedDate
) :
    CategoryScreenUiData.Mapper<CategoriesUiState> {

    override fun map(
        publishedDate: String,
        categories: List<CategoryUi>
    ): CategoriesUiState {
        val formatedDate = formatedDate.dateFormat(publishedDate)
        return CategoriesUiState.Success(formatedDate, categories)
    }

}