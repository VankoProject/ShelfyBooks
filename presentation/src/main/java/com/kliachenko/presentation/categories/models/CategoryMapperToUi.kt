package com.kliachenko.presentation.categories.models

import com.kliachenko.domain.model.CategoryMapper
import javax.inject.Inject

class CategoryMapperToUi @Inject constructor() : CategoryMapper<CategoryUi> {
    override fun map(
        categoryId: String,
        categoryName: String,
        booksCount: Int,
        updatePeriod: String
    ) = CategoryUi.Base(categoryId, categoryName, booksCount, updatePeriod)
}