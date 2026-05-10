package com.example.speakupfinalproject

import com.example.speakupfinalproject.feature_question_feed.Question

/**
 * Singleton Repository to share data between different features in the demo.
 */
object QuestionRepository {
    private val questions = arrayListOf(
        Question("1", "What is the difference between MVP and MVVM?", 5, "2m ago", isAnonymous = false, author = "Student A"),
        Question("2", "Can you explain Vertical Slicing again?", 3, "5m ago", isAnswered = true, author = "Student B", answer = "Vertical slicing means organizing by feature rather than by layer."),
        Question("3", "How do I implement a RecyclerView in Material 3?", 10, "10m ago", isAnonymous = true),
        Question("4", "Is Kotlin better than Java for Android?", 7, "15m ago", isAnswered = true, isAnonymous = true, answer = "Kotlin is generally preferred for its conciseness and safety features.")
    )

    fun getQuestions(): List<Question> = questions

    fun addQuestion(question: Question) {
        questions.add(0, question)
    }

    fun upvoteQuestion(questionId: String): Question? {
        val index = questions.indexOfFirst { it.id == questionId }
        if (index != -1) {
            val updated = questions[index].copy(upvoteCount = questions[index].upvoteCount + 1)
            questions[index] = updated
            return updated
        }
        return null
    }

    fun answerQuestion(questionId: String, answer: String): Question? {
        val index = questions.indexOfFirst { it.id == questionId }
        if (index != -1) {
            val updated = questions[index].copy(isAnswered = true, answer = answer)
            questions[index] = updated
            return updated
        }
        return null
    }
}
