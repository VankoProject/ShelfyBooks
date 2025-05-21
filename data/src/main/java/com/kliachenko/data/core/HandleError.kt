package com.kliachenko.data.core

interface HandleError<T: Any> {

    fun handle(exception: Exception): T
}