package com.example.myproject.screens.login

import android.app.Activity
import com.example.myproject.data.UserInfo
import com.example.myproject.utils.app

// 1. Contract
interface LoginContract {
    interface View {
        fun showSuccessMessage()
        fun showInvalidCredentialMessage()
        fun showEmptyMessage()
        fun navigateToHomeScreen()
    }
    interface Presenter {
        fun login(username: String, password: String)
    }
}

// 2. Model
class LoginModel {
    fun login(u: String, p: String): Boolean = (u == "test" && p == "test")
}

// 3. Presenter
class LoginPresenter(private val view: LoginContract.View, private val model: LoginModel) : LoginContract.Presenter {
    override fun login(username: String, password: String) {
        val app = (view as Activity).app()
        if (username.isNotEmpty() && password.isNotEmpty()) {
            if (model.login(username, password)) {
                app.setUserInfo(UserInfo(username, password))
                view.showSuccessMessage()
                view.navigateToHomeScreen()
            } else view.showInvalidCredentialMessage()
        } else view.showEmptyMessage()
    }
}