package com.example.assignment_4_12_26.data.models

data class User(
    val id: String,
    val fullName: String,
    val email: String,
    val questionsAsked: Int = 0,
    val upvotesReceived: Int = 0
)