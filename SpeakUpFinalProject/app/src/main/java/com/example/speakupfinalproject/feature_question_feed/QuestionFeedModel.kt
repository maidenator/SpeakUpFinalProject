package com.example.speakupfinalproject.feature_question_feed

import com.example.speakupfinalproject.QuestionRepository

class QuestionFeedModel {

    fun getQuestions(): List<Question> {
        return QuestionRepository.getQuestions()
    }

    fun addQuestion(question: Question) {
        QuestionRepository.addQuestion(question)
    }

    fun upvoteQuestion(questionId: String): Question? {
        return QuestionRepository.upvoteQuestion(questionId)
    }

    fun answerQuestion(questionId: String, answer: String): Question? {
        return QuestionRepository.answerQuestion(questionId, answer)
    }
}
