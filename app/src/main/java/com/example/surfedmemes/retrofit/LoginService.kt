package com.example.surfedmemes.retrofit

import com.example.surfedmemes.model.LoginRequest
import com.example.surfedmemes.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/auth/login")
    fun authenticate(@Body request: LoginRequest): Call<LoginResponse>
}