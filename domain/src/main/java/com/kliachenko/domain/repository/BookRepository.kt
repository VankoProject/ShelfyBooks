package com.kliachenko.domain.repository

import com.kliachenko.domain.model.BookDomain
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    fun books(categoryId: String): Flow<LoadResult<List<BookDomain>>>

}