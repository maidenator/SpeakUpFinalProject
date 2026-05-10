// LoginPresenter.kt
package com.android.customapplication.screens.login
import android.app.Activity
import com.android.customapplication.data.UserInfo
import com.android.customapplication.utils.app

class LoginPresenter(private val view: LoginContract.View, private val model: LoginModel) : LoginContract.Presenter {
    override fun login(username: String, password: String) {
        val app = (view as Activity).app()
        if (username.isEmpty() || password.isEmpty()) {
            view.showEmptyMessage()
        } else if (model.login(username, password)) {
            app.setUserInfo(UserInfo(username, password))
            view.showSuccessMessage()
            view.navigateToHomeScreen()
        } else {
            view.showInvalidCredentialMessage()
        }
    }
}