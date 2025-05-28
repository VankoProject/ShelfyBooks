package com.kliachenko.presentation.books.model.mapper

import com.kliachenko.domain.model.BookDomain
import com.kliachenko.domain.model.BookMapper
import com.kliachenko.domain.repository.LoadResult
import com.kliachenko.presentation.books.BookUiState
import com.kliachenko.presentation.books.model.BookUi
import javax.inject.Inject

class BookResultMapper @Inject constructor(
    private val bookMapper: BookMapper<BookUi>
) : LoadResult.Mapper<List<BookDomain>, BookUiState> {

    override fun mapSuccess(data: List<BookDomain>): BookUiState {
        val booksUi = data.map { it.map(bookMapper) }
        return BookUiState.Success(booksUi)
    }

    override fun mapError(errorMessage: String) = BookUiState.Error(errorMessage)

    override fun mapEmpty() = BookUiState.Empty
}