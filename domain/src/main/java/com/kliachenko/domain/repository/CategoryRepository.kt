package com.kliachenko.domain.repository

import com.kliachenko.domain.model.CategoryDomain
import kotlinx.coroutines.flow.Flow


interface CategoryRepository {

    fun categories(): Flow<LoadResult<List<CategoryDomain>>>

    suspend fun publishedDate(): String
}