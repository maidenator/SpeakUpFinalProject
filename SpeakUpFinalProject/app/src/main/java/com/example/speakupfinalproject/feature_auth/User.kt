package com.example.speakupfinalproject.feature_auth

enum class UserRole {
    STUDENT, PROFESSOR
}

data class User(
    val email: String,
    val role: UserRole,
    val isLoggedIn: Boolean = false
)
