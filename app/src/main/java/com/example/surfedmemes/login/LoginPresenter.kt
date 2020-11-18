package com.example.surfedmemes.login

import android.content.Context

class LoginPresenter(loginView: LoginView, loginInteractor: LoginInteractor): LoginInteractor.onLoginFinishedListener {

    val lv = loginView
    val li = loginInteractor

    override fun onUserPhoneError() {
        lv.setUserPhoneError()
    }

    override fun onPasswordError() {
        lv.setPasswordError()
    }

    override fun onPasswordShort() {
        lv.setPasswordIsTooShort()
    }

    override fun onLoginSuccess() {
        lv.onLoginSuccess()
    }

    override fun onLoginError() {
        lv.onLoginError()
    }

    fun validateCredentials(userPhone: String, password: String) {
        li.canLogin(userPhone, password, this)
    }
}