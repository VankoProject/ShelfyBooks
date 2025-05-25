package com.kliachenko.presentation.categories

import com.kliachenko.domain.usecase.Categories
import com.kliachenko.presentation.categories.models.mapper.CategoryResultMapper
import com.kliachenko.presentation.core.BaseViewModel
import com.kliachenko.presentation.core.RunAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categories: Categories,
    private val resultMapper: CategoryResultMapper,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    private val _uiState = MutableStateFlow<CategoriesUiState>(CategoriesUiState.Empty)
    val uiState: StateFlow<CategoriesUiState> = _uiState

    init {
        load()
    }

    fun load() {
        _uiState.value = CategoriesUiState.Progress
        runAsync(
            categories()
        ) { result ->
            _uiState.value = result.map(resultMapper)
        }
    }

}