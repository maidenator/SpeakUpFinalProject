package com.example.speakupfinalproject.feature_interaction

import com.example.speakupfinalproject.base.MvpPresenter
import com.example.speakupfinalproject.base.MvpView
import com.example.speakupfinalproject.feature_question_feed.Question

interface InteractionContract {

    interface AskView : MvpView {
        fun onQuestionPosted(content: String, isAnonymous: Boolean)
    }

    interface AnswerView : MvpView {
        fun displayQuestion(question: Question)
        fun onAnswerPosted(questionId: String, answer: String)
    }

    interface Presenter : MvpPresenter<MvpView> {
        fun submitQuestion(content: String, isAnonymous: Boolean)
        fun submitAnswer(questionId: String, answer: String)
        fun loadQuestionToAnswer(questionId: String)
    }
}
