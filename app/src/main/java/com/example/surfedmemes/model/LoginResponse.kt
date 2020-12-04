package com.example.surfedmemes.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("accessToken")
    var accessToken: String? = null,
    @SerializedName("userInfo")
    var userInfo: User? = null
)