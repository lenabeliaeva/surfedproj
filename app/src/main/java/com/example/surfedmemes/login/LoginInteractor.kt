package com.example.surfedmemes.login

import android.content.Context
import android.os.Looper
import android.os.Handler
import com.example.surfedmemes.model.LoginRequest
import com.example.surfedmemes.model.LoginResponse
import com.example.surfedmemes.retrofit.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginInteractor (context: Context) {

    companion object {
        private const val PASSWORD_LENGTH = 6
    }

    private val userAPI = NetworkService.getLoginServiceApi()
    private val prefsHelper = SharedPrefsHelper(context)

    interface onLoginFinishedListener {
        fun onUserPhoneError()
        fun onPasswordError()
        fun onPasswordShort()
        fun onLoginSuccess()
        fun onLoginError()
    }

    private fun tryLogin(userPhone: String, password: String): Boolean {
        var result = false
        userAPI?.authenticate(LoginRequest(userPhone, password))?.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val loginResponse: LoginResponse? = response.body()
                prefsHelper.accessToken = loginResponse?.accessToken!!
                prefsHelper.userInfo = loginResponse.userInfo!!
                result = true
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            }

        })
        return result
    }

    fun canLogin(userPhone: String, password: String, listener: onLoginFinishedListener) {
        Handler(Looper.getMainLooper()).postDelayed({
            when {
                userPhone.isEmpty() && password.isEmpty() -> {
                    listener.onUserPhoneError()
                    listener.onPasswordError()
                }
                userPhone.isEmpty() -> listener.onUserPhoneError()
                password.isEmpty() -> listener.onPasswordError()
                password.length < PASSWORD_LENGTH -> listener.onPasswordShort()
                tryLogin(userPhone, password) -> listener.onLoginSuccess()
                else -> listener.onLoginError()
            }
        }, 2000)
    }
}