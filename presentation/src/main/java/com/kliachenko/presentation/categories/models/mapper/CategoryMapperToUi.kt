package com.kliachenko.presentation.categories.models.mapper

import com.kliachenko.domain.model.CategoryMapper
import com.kliachenko.presentation.categories.models.CategoryUi
import javax.inject.Inject

class CategoryMapperToUi @Inject constructor() : CategoryMapper<CategoryUi> {
    override fun map(
        categoryId: String,
        categoryName: String,
        booksCount: Int,
        updatePeriod: String
    ) = CategoryUi.Base(categoryId, categoryName, booksCount, updatePeriod)
}