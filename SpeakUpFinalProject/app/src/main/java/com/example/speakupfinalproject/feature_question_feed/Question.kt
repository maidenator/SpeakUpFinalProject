package com.example.speakupfinalproject.feature_question_feed

data class Question(
    val id: String,
    val content: String,
    val upvoteCount: Int = 0,
    val timeAgo: String,
    val isAnonymous: Boolean = true,
    val isAnswered: Boolean = false,
    val author: String = "Anonymous",
    val answer: String? = null
)
