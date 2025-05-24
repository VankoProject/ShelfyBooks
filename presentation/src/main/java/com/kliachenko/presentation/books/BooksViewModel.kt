package com.kliachenko.presentation.books

import com.kliachenko.domain.usecase.BooksByCategory
import com.kliachenko.presentation.core.BaseViewModel
import com.kliachenko.presentation.core.RunAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksByCategory: BooksByCategory,
    runAsync: RunAsync,
) : BaseViewModel(runAsync) {

}