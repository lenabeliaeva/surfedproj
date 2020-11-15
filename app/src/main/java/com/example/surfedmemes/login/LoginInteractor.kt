package com.example.surfedmemes.login

import android.os.Looper
import android.os.Handler

class LoginInteractor {
    private val PASSWORD_LENGTH = 6

    interface onLoginFinishedListener {
        fun onUserPhoneError()
        fun onPasswordError()
        fun onPasswordShort()
        fun onLoginSuccess()
        fun onLoginError()
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
                else -> listener.onLoginSuccess()
            }
        }, 2000)
    }
}