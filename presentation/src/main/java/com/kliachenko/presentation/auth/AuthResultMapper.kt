package com.kliachenko.presentation.auth

import com.kliachenko.domain.repository.AuthResult
import javax.inject.Inject

class AuthResultMapper @Inject constructor() : AuthResult.Mapper<String> {

    override fun mapSuccess() = ""

    override fun mapFailed(error: String) = error

}