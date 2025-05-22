package com.kliachenko.domain.usecase

import com.kliachenko.domain.model.CategoryScreenData
import com.kliachenko.domain.repository.CategoryRepository
import com.kliachenko.domain.repository.LoadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface Categories {

    operator fun invoke(): Flow<LoadResult<List<CategoryScreenData>>>
}

class CategoriesInteractor @Inject constructor(
    private val repository: CategoryRepository
) : Categories {
    override fun invoke(): Flow<LoadResult<List<CategoryScreenData>>> = flow {
        combine(
            repository.categories(),
            flow { emit(repository.publishedDate()) }
        ) { result, date ->
            result.mapData { categoryList ->
                CategoryScreenData(
                    publishedDate = date,
                    categories = categoryList
                )
            }
        }
    }

}