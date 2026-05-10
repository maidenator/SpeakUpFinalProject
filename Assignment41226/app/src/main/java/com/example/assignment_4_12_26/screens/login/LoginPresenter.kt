package com.example.assignment_4_12_26.screens.login

import com.example.assignment_4_12_26.data.repositories.UserRepository

class LoginPresenter(
    private val view: LoginContract.View,
    private val repository: UserRepository
) : LoginContract.Presenter {

    override fun attemptLogin(email: String, pass: String) {
        if (!email.endsWith(".edu")) {
            view.showEmailError("Must use a valid .edu email")
            return
        }
        if (pass.length < 6) {
            view.showPasswordError("Password too short")
            return
        }

        if (repository.authenticateUser(email, pass)) {
            view.showLoginSuccess()
        } else {
            view.showLoginError()
        }
    }

    override fun onRegisterClicked() {
        view.navigateToRegister()
    }
}