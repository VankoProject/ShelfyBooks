package com.kliachenko.presentation.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

abstract class BaseViewModel(
    private val runAsync: RunAsync
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    protected fun <T : Any> runAsync(
        backGround: suspend () -> T,
        uiContent: (T) -> Unit
    ) {
        runAsync.start(
            coroutineScope = viewModelScope,
            backGround = backGround,
            uiContent = uiContent
        )
    }

    protected fun <T : Any> runAsync(
        backGround: Flow<T>,
        uiContent: (T) -> Unit
    ) {
        runAsync.collect(
            coroutineScope = viewModelScope,
            background = backGround,
            uiContent = uiContent
        )
    }

}

interface RunAsync {

    fun <T : Any> start(
        coroutineScope: CoroutineScope,
        backGround: suspend () -> T,
        uiContent: (T) -> Unit
    )

    fun <T : Any> collect(
        coroutineScope: CoroutineScope,
        background: Flow<T>,
        uiContent: (T) -> Unit
    )

    class Base @Inject constructor() : RunAsync {

        override fun <T : Any> start(
            coroutineScope: CoroutineScope,
            backGround: suspend () -> T,
            uiContent: (T) -> Unit
        ) {
            coroutineScope.launch(Dispatchers.IO) {
                val result = backGround.invoke()
                withContext(Dispatchers.Main) {
                    uiContent.invoke(result)
                }
            }
        }

        override fun <T : Any> collect(
            coroutineScope: CoroutineScope,
            background: Flow<T>,
            uiContent: (T) -> Unit
        ) {
            coroutineScope.launch(Dispatchers.IO) {
                background.collect { value ->
                    withContext(Dispatchers.Main) {
                        uiContent(value)
                    }
                }
            }
        }
    }

}