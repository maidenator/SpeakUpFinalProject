package com.example.speakupfinalproject.feature_auth

/**
 * Presenter for the Authentication feature.
 * 
 * FLOW EXPLANATION (View -> Presenter -> Navigation):
 * 
 * 1. VIEW -> PRESENTER:
 *    User enters credentials and selects a role, then clicks 'LOG IN'.
 *    The View calls 'presenter.onLoginClicked(email, password, role)'.
 * 
 * 2. PRESENTER (Logic):
 *    The Presenter validates the input. 
 *    Requirement: Email must end with '@university.edu'.
 * 
 * 3. PRESENTER -> VIEW:
 *    - If invalid: Presenter tells View to show specific errors.
 *    - If valid: Presenter tells View to 'navigateToDashboard(role)'.
 */
class AuthPresenter : AuthContract.Presenter {

    private var view: AuthContract.View? = null

    override fun attachView(view: AuthContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun destroy() {
        // Cleanup
    }

    override fun onLoginClicked(email: String, password: String, role: UserRole) {
        var isValid = true

        if (password.length < 6) {
            view?.showPasswordError("Password must be at least 6 characters")
            isValid = false
        }

        if (!email.endsWith("@university.edu")) {
            view?.showEmailError("Please use a valid @university.edu email")
            isValid = false
        }

        if (isValid) {
            view?.navigateToDashboard(role)
        }
    }
}
