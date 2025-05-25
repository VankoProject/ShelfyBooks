package com.kliachenko.domain.usecase

import com.kliachenko.domain.model.CategoryScreenData
import com.kliachenko.domain.repository.CategoryRepository
import com.kliachenko.domain.repository.LoadResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface Categories {

    operator fun invoke(): Flow<LoadResult<CategoryScreenData>>

    class Interactor @Inject constructor(
        private val repository: CategoryRepository
    ) : Categories {
        override fun invoke(): Flow<LoadResult<CategoryScreenData>> = repository.categories()

    }

}

