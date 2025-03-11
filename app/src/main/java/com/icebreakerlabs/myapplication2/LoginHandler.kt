package com.icebreakerlabs.myapplication2

import kotlinx.coroutines.delay

class LoginHandler {
    private val validEmail = "a@pp.com"
    private val validPassword = "pass"

    suspend fun submitLogin(email: String, password: String): Result<String?> {
        delay(3000)

        return when {
            email != validEmail -> Result.failure<String>(Exception("Wrong Email"))
            password != validPassword -> Result.failure<String>(Exception("Wrong Password"))
            else -> Result.success(null)
        }
    }
}