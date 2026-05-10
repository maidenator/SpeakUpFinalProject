package com.android.customapplication.screens.login

interface LoginContract {

    interface View {
        fun showSuccessMessage()
        fun showInvalidCredentialMessage()
        fun showEmptyMessage()
        fun navigateToHomeScreen()
        fun showGenericErrorMessage()
    }

    interface Presenter {
        fun login(username: String, password: String)
    }
}