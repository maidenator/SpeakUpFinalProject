package com.example.speakupfinalproject.feature_auth

import com.example.speakupfinalproject.base.MvpPresenter
import com.example.speakupfinalproject.base.MvpView

interface AuthContract {

    interface View : MvpView {
        fun navigateToDashboard(role: UserRole)
        fun showEmailError(message: String)
        fun showPasswordError(message: String)
    }

    interface Presenter : MvpPresenter<View> {
        fun onLoginClicked(email: String, password: String, role: UserRole)
    }
}
