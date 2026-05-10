package com.example.speakupfinalproject.feature_submit_question

/**
 * Presenter for the Submit Question feature.
 * 
 * FLOW EXPLANATION (View -> Presenter -> Model/Callback):
 * 
 * 1. VIEW -> PRESENTER:
 *    The View (SubmitQuestionBottomSheet) captures the user's text input.
 *    When the user clicks "Post", the View calls 'presenter.submitQuestion(content)'.
 * 
 * 2. PRESENTER (Logic/Validation):
 *    The Presenter performs validation (pure Kotlin logic). 
 *    It checks if the content is blank. This happens independently of Android's UI thread or Context.
 * 
 * 3. PRESENTER -> VIEW (Success/Failure):
 *    - If invalid: The Presenter tells the View to show an error: 'view?.onValidationError(...)'.
 *    - If valid: The Presenter tells the View that validation succeeded: 'view?.onValidationSuccess(content)' 
 *      and then instructs the View to close: 'view?.dismissDialog()'.
 * 
 * 4. VIEW (Callback to Parent):
 *    The View (BottomSheet) receives 'onValidationSuccess' and triggers a callback to the 
 *    MainActivity (the parent listener) to actually add the question to the Feed.
 */
class SubmitQuestionPresenter : SubmitQuestionContract.Presenter {

    private var view: SubmitQuestionContract.View? = null

    override fun attachView(view: SubmitQuestionContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun destroy() {
        // Cleanup resources if necessary
    }

    override fun submitQuestion(content: String) {
        // Pure Kotlin validation logic
        if (content.isBlank()) {
            view?.onValidationError("Question cannot be empty")
        } else {
            // Logic to proceed if validation passes
            view?.onValidationSuccess(content)
            view?.dismissDialog()
        }
    }
}
