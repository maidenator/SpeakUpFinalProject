package com.example.assignment_4_12_26.screens.login

interface LoginContract {
    interface View {
        fun showEmailError(msg: String)
        fun showPasswordError(msg: String)
        fun showLoginSuccess()
        fun showLoginError()
        fun navigateToRegister()
    }

    interface Presenter {
        fun attemptLogin(email: String, pass: String)
        fun onRegisterClicked()
    }
}