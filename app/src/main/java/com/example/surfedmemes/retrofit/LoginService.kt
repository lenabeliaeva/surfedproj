package com.example.surfedmemes.`interface`

import com.example.surfedmemes.model.LoginRequest
import com.example.surfedmemes.model.LoginResponse
import retrofit2.Call
import retrofit2.http.POST

interface LoginService {
    @POST("/auth/login")
    fun authenticate(request: LoginRequest): Call<LoginResponse>
}