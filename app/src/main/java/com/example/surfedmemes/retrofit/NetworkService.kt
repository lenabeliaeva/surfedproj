package com.example.surfedmemes.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "virtserver.swaggerhub.com/AndroidSchool/SurfAndroidSchool/1.0.0"

    fun genInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}