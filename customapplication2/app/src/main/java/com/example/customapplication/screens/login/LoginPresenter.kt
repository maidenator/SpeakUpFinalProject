package com.android.customapplication.screens.login

import android.app.Activity
import com.android.customapplication.data.UserInfo
import com.android.customapplication.utils.app

class LoginPresenter(private  val view: LoginContract.View,
                     private val loginModel: LoginModel)
    : LoginContract.Presenter {

    private var isLoggedIn = false
    private val app = (view as Activity).app()
    override fun login(username: String, password: String) {
        if (username.isNotEmpty() && !password.isNullOrEmpty()) {
            if (loginModel.login(username, password)) {
                isLoggedIn = true
                app.setUserInfo(UserInfo(username, password))
                view.showSuccessMessage()
                view.navigateToHomeScreen()
            } else {
                view.showInvalidCredentialMessage()
            }
        } else {
            view.showEmptyMessage()
        }
    }
}