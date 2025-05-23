package com.kliachenko.presentation.categories

import com.kliachenko.domain.usecase.Categories
import com.kliachenko.presentation.core.BaseViewModel
import com.kliachenko.presentation.core.RunAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categories: Categories,
    runAsync: RunAsync
): BaseViewModel(runAsync) {



}