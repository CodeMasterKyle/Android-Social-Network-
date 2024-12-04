package com.incorp.ichat

// AuthResponse.kt
data class AuthResponse(
    val error: Boolean,
    val error_code: Int,
    val accessToken: String?,
    val accountId: String?,
    val account: List<Account>?
)

data class Account(
    val error: Boolean,
    val error_code: Int,
    val id: String,
    val username: String,
    val fullname: String,
    // Добавьте другие поля по необходимости
)
