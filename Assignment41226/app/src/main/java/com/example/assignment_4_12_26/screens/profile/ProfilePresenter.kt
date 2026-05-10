package com.example.assignment_4_12_26.screens.profile

import com.example.assignment_4_12_26.data.repositories.UserRepository

class ProfilePresenter(
    private val view: ProfileContract.View,
    private val repository: UserRepository
) : ProfileContract.Presenter {

    override fun loadProfile() {
        val user = repository.getUserProfile()
        view.displayUserData(user)
    }

    override fun logout() {
        view.navigateToLogin()
    }
}