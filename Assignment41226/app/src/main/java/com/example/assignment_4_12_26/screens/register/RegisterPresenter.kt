package com.example.assignment_4_12_26.screens.register

import com.example.assignment_4_12_26.data.repositories.UserRepository

class RegisterPresenter(
    private val view: RegisterContract.View,
    private val repository: UserRepository
) : RegisterContract.Presenter {

    override fun register(name: String, email: String, pass: String, agreedToRules: Boolean) {
        if (!agreedToRules) {
            view.showValidationError("You must agree to the Code of Conduct")
            return
        }
        if (repository.registerUser(name, email, pass)) {
            view.showRegistrationSuccess()
        } else {
            view.showValidationError("Invalid inputs. Ensure .edu email is used.")
        }
    }
}