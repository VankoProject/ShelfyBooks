package com.kliachenko.domain.repository

import com.kliachenko.domain.model.CategoryScreenData
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun categories(): Flow<LoadResult<CategoryScreenData>>

}