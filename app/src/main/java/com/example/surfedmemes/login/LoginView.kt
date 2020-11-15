package com.example.surfedmemes.login

interface LoginView {
    fun setUserPhoneError()
    fun setPasswordError()
    fun setPasswordIsTooShort()
    fun showHidePassword()
    fun hideButtonText()
    fun showButtonText()
    fun showProgressBar()
    fun hideProgressBar()
    fun onLoginSuccess()
    fun onLoginError()
}