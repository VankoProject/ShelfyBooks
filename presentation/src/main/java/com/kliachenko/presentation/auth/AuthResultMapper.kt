package com.kliachenko.presentation.auth

import com.kliachenko.domain.repository.AuthResult

class AuthResultMapper : AuthResult.Mapper<String> {

    override fun mapSuccess() = ""

    override fun mapFailed(error: String) = error

}