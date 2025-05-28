package com.kliachenko.domain.repository

interface AuthRepository {

    suspend fun authWithGoogle(idToken: String): AuthResult

    fun isLoggedIn(): Boolean
}

interface AuthResult {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun mapSuccess(): T
        fun mapFailed(error: String): T
    }

    object Success : AuthResult {
        override fun <T> map(mapper: Mapper<T>) = mapper.mapSuccess()
    }

    data class Error(private val error: String) : AuthResult {
        override fun <T> map(mapper: Mapper<T>) = mapper.mapFailed(error)
    }

}