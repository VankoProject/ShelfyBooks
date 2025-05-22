package com.kliachenko.domain.usecase

import com.kliachenko.domain.model.BookDomain
import com.kliachenko.domain.repository.BookRepository
import com.kliachenko.domain.repository.LoadResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BooksByCategory {

    operator fun invoke(categoryId: String): Flow<LoadResult<List<BookDomain>>>

}

class BooksInteractor @Inject constructor(
    private val repository: BookRepository
) : BooksByCategory {
    override fun invoke(categoryId: String): Flow<LoadResult<List<BookDomain>>> =
        repository.books(categoryId)

}