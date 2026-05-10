package com.example.speakupfinalproject.feature_question_feed

import com.example.speakupfinalproject.base.MvpPresenter
import com.example.speakupfinalproject.base.MvpView
import com.example.speakupfinalproject.feature_auth.UserRole

interface QuestionFeedContract {

    interface View : MvpView {
        fun showQuestions(questions: List<Question>)
        fun showEmptyState()
        fun updateQuestionItem(question: Question)
        fun setFabVisibility(isVisible: Boolean)
        fun navigateToAskQuestion()
        fun navigateToAnswerQuestion(questionId: String)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadQuestions(role: UserRole)
        fun onQuestionClicked(question: Question, role: UserRole)
        fun onAddQuestionClicked()
        fun onUpvoteClicked(question: Question)
        fun refreshFeed()
    }
}
