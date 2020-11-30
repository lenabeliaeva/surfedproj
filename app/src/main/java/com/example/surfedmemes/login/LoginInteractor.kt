package com.example.surfedmemes.login

import android.content.Context
import com.example.surfedmemes.model.LoginRequest
import com.example.surfedmemes.model.LoginResponse
import com.example.surfedmemes.retrofit.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginInteractor(context: Context) {

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

    fun canLogin(userPhone: String, password: String, listener: onLoginFinishedListener) {
        when {
            userPhone.isEmpty() && password.isEmpty() -> {
                listener.onUserPhoneError()
                listener.onPasswordError()
            }
            userPhone.isEmpty() -> listener.onUserPhoneError()
            password.isEmpty() -> listener.onPasswordError()
            password.length < PASSWORD_LENGTH -> listener.onPasswordShort()
            else ->
                userAPI?.authenticate(LoginRequest(userPhone, password))
                    ?.enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            val loginResponse: LoginResponse? = response.body()
                            prefsHelper.accessToken = loginResponse?.accessToken!!
                            //prefsHelper.userInfo = loginResponse.userInfo!!
                            listener.onLoginSuccess()
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            t.printStackTrace()
                            listener.onLoginError()
                        }
                    })

        }
    }
}