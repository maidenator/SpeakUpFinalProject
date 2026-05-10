package com.example.speakupfinalproject.feature_interaction

import com.example.speakupfinalproject.base.MvpView
import com.example.speakupfinalproject.feature_question_feed.QuestionFeedModel

/**
 * Presenter for handling user interactions (Asking and Answering questions).
 */
class InteractionPresenter(private val repository: QuestionFeedModel) : InteractionContract.Presenter {

    private var view: MvpView? = null

    override fun attachView(view: MvpView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun destroy() {}

    override fun submitQuestion(content: String, isAnonymous: Boolean) {
        if (content.isBlank()) {
            view?.showError("Question cannot be empty")
            return
        }
        
        // In a real app, this would be an API call
        // For the demo, we notify the view it's done
        (view as? InteractionContract.AskView)?.onQuestionPosted(content, isAnonymous)
    }

    override fun submitAnswer(questionId: String, answer: String) {
        // Logic: When professor posts, the question is marked as answered.
        // Save the answer in the repository for the demo.
        repository.answerQuestion(questionId, answer)

        (view as? InteractionContract.AnswerView)?.onAnswerPosted(questionId, answer)
    }

    override fun loadQuestionToAnswer(questionId: String) {
        val question = repository.getQuestions().find { it.id == questionId }
        if (question != null) {
            (view as? InteractionContract.AnswerView)?.displayQuestion(question)
        } else {
            view?.showError("Question not found")
        }
    }
}
