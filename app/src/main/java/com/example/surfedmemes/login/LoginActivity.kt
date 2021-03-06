package com.example.surfedmemes.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.example.surfedmemes.main.MainActivity
import com.example.surfedmemes.R
import com.google.android.material.snackbar.Snackbar
import studio.carbonylgroup.textfieldboxes.ExtendedEditText
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var etLogin: ExtendedEditText
    private lateinit var tfbLogin: TextFieldBoxes
    private lateinit var etPassword: ExtendedEditText
    private lateinit var tfbPassword: TextFieldBoxes
    private lateinit var btnLogin: Button
    private lateinit var pbLogin: ProgressBar

    private lateinit var loginPresenter: LoginPresenter

    private var isHidden = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
        loginPresenter = LoginPresenter(this, LoginInteractor(applicationContext))
        btnLogin.setOnClickListener { login() }
        tfbPassword.endIconImageButton.setOnClickListener { showHidePassword() }
    }

    private fun login() {
        showProgressBar()
        loginPresenter.validateCredentials(etLogin.text.toString(), etPassword.text.toString())
    }

    private fun initViews() {
        etLogin = findViewById(R.id.et_login)
        tfbLogin = findViewById(R.id.tfb_login)
        etPassword = findViewById(R.id.et_password)
        tfbPassword = findViewById(R.id.tfb_password)
        btnLogin = findViewById(R.id.btn_login)
        pbLogin = findViewById(R.id.pb_login)
    }

    override fun setUserPhoneError() {
        hideProgressBar()
        tfbLogin.setError(getString(R.string.empty_field_error), true)
    }

    override fun setPasswordError() {
        hideProgressBar()
        tfbPassword.setError(getString(R.string.empty_field_error), true)
    }

    override fun setPasswordIsTooShort() {
        hideProgressBar()
        tfbPassword.helperText = getString(R.string.password_helper)
    }

    override fun showHidePassword() {
        if (isHidden) {
            tfbPassword.setEndIcon(R.drawable.ic_password_closed_eye)
            etPassword.inputType = InputType.TYPE_CLASS_TEXT
        } else {
            tfbPassword.setEndIcon(R.drawable.ic_password_eye)
            etPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        isHidden = !isHidden
    }

    override fun hideButtonText() {
        btnLogin.text = ""
    }

    override fun showButtonText() {
        btnLogin.text = getText(R.string.enter)
    }

    override fun showProgressBar() {
        hideButtonText()
        pbLogin.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        showButtonText()
        pbLogin.visibility = ProgressBar.GONE
    }

    override fun onLoginSuccess() {
        showProgressBar()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onLoginError() {
        hideProgressBar()
        val snackBar = Snackbar.make(
            findViewById(android.R.id.content),
            R.string.login_error_snackBar,
            Snackbar.LENGTH_LONG
        )
        snackBar.view.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.error))
        snackBar.show()
    }
}