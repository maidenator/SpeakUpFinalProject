package com.example.speakupfinalproject.feature_question_feed

import com.example.speakupfinalproject.feature_auth.UserRole

/**
 * Presenter for the Question Feed.
 */
class QuestionFeedPresenter(private val model: QuestionFeedModel) : QuestionFeedContract.Presenter {

    private var view: QuestionFeedContract.View? = null

    override fun attachView(view: QuestionFeedContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun destroy() {}

    override fun loadQuestions(role: UserRole) {
        view?.showLoading()
        view?.setFabVisibility(role == UserRole.STUDENT)
        
        val questions = model.getQuestions()
        view?.hideLoading()
        
        if (questions.isEmpty()) {
            view?.showEmptyState()
        } else {
            view?.showQuestions(questions)
        }
    }

    override fun onQuestionClicked(question: Question, role: UserRole) {
        // Professors can always click to answer/edit.
        // Students can click only if the question has been answered.
        if (role == UserRole.PROFESSOR || question.isAnswered) {
            view?.navigateToAnswerQuestion(question.id)
        }
    }

    override fun onAddQuestionClicked() {
        view?.navigateToAskQuestion()
    }

    override fun refreshFeed() {
        // Just reload the data from the model
        val questions = model.getQuestions()
        if (questions.isEmpty()) {
            view?.showEmptyState()
        } else {
            view?.showQuestions(questions)
        }
    }

    override fun onUpvoteClicked(question: Question) {
        val updated = model.upvoteQuestion(question.id)
        if (updated != null) {
            view?.updateQuestionItem(updated)
        }
    }
}
