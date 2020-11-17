package com.example.surfedmemes.model

data class LoginResponse (
    var accessToken: String? = null,
    var userInfo: User? = null
)