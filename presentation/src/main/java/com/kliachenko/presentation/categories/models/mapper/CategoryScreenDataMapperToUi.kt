package com.kliachenko.presentation.categories.models.mapper

import com.kliachenko.domain.model.CategoryDomain
import com.kliachenko.domain.model.CategoryMapper
import com.kliachenko.domain.model.ScreenDataMapper
import com.kliachenko.presentation.categories.models.CategoryScreenUiData
import com.kliachenko.presentation.categories.models.CategoryUi
import javax.inject.Inject

class CategoryScreenDataMapperToUi @Inject constructor(
    private val categoryMapper: CategoryMapper<CategoryUi>
) : ScreenDataMapper<CategoryScreenUiData> {

    override fun map(
        publishedDate: String,
        categories: List<CategoryDomain>
    ): CategoryScreenUiData {
        val uiCategories = categories.map { it.map(categoryMapper) }
        return CategoryScreenUiData.Base(
            publishedDate = publishedDate,
            categories = uiCategories
        )
    }

}