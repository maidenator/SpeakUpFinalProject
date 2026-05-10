package com.example.assignment_4_12_26.data.repositories

import com.example.assignment_4_12_26.data.models.User

class UserRepository {
    fun authenticateUser(email: String, pass: String): Boolean {
        return email.endsWith(".edu") && pass.length >= 6
    }

    fun registerUser(name: String, email: String, pass: String): Boolean {
        return name.isNotEmpty() && email.endsWith(".edu") && pass.length >= 6
    }

    fun getUserProfile(): User {
        return User("101", "Gabriel Elorde", "gabriel.elorde@cit.edu", 15, 68)
    }
}