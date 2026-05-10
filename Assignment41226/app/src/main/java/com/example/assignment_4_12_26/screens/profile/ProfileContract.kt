package com.example.assignment_4_12_26.screens.profile

import com.example.assignment_4_12_26.data.models.User

interface ProfileContract {
    interface View {
        fun displayUserData(user: User)
        fun navigateToLogin()
    }
    interface Presenter {
        fun loadProfile()
        fun logout()
    }
}