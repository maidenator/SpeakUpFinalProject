package com.android.customapplication.screens.login

class LoginModel {
    private val username = "test"
    private val password = "test"

    fun login(username: String, password: String): Boolean {
        // call login api / local database
        // clean data
        // decide true or false
        return username.equals(this.username, false)
                && password.equals(this.password, false)
    }
}