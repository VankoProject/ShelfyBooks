package com.kliachenko.domain.core

interface HandleError<T: Any> {

    fun handle(exception: Exception): T
}

