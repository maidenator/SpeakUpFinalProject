package com.example.assignment_4_12_26.screens.register

interface RegisterContract {
    interface View {
        fun showValidationError(msg: String)
        fun showRegistrationSuccess()
    }
    interface Presenter {
        fun register(name: String, email: String, pass: String, agreedToRules: Boolean)
    }
}