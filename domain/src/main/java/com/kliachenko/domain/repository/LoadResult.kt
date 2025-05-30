package com.kliachenko.domain.repository

interface LoadResult<T : Any> {

    fun <S : Any> map(mapper: Mapper<T, S>): S

    interface Mapper<T : Any, S : Any> {
        fun mapSuccess(data: T): S
        fun mapError(errorMessage: String): S
        fun mapEmpty(): S
    }

    class Empty<T : Any> : LoadResult<T> {

        override fun <S : Any> map(mapper: Mapper<T, S>) = mapper.mapEmpty()

    }

    data class Error<T : Any>(private val errorMessage: String) : LoadResult<T> {

        override fun <S : Any> map(mapper: Mapper<T, S>) = mapper.mapError(errorMessage)

    }

    data class Success<T : Any>(private val data: T) : LoadResult<T> {

        override fun <S : Any> map(mapper: Mapper<T, S>) = mapper.mapSuccess(data)

    }

}