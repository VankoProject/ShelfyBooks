package com.kliachenko.presentation.core

interface HandleWebViewException {

    fun handle(errorCode: Int?, description: CharSequence?): String

}