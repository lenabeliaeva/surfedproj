package com.example.surfedmemes.model

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("login")
    var login: String? = null,
    @SerializedName("password")
    var password: String? = null
)