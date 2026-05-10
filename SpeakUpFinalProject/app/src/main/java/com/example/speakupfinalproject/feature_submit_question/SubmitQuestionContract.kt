package com.example.speakupfinalproject.feature_submit_question

import com.example.speakupfinalproject.base.MvpPresenter
import com.example.speakupfinalproject.base.MvpView

interface SubmitQuestionContract {

    interface View : MvpView {
        fun onValidationSuccess(content: String)
        fun onValidationError(errorMessage: String)
        fun dismissDialog()
    }

    interface Presenter : MvpPresenter<View> {
        fun submitQuestion(content: String)
    }
}
