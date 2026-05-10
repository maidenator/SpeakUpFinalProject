package com.android.customapplication.screens.login
import android.app.Activity
import android.os.Bundle
import com.android.customapplication.R
import com.android.customapplication.screens.home.HomeActivity
import com.android.customapplication.utils.*

class LoginActivity : Activity(), LoginContract.View {
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this, LoginModel())

        getButtonView(R.id.buttonLogin).setOnClickListener {
            presenter.login(getEditTextValue(R.id.edittextUsername), getEditTextValue(R.id.edittextPassword))
        }
    }

    override fun showSuccessMessage() = toast("Login successful!")
    override fun showInvalidCredentialMessage() = toast("Invalid credentials!")
    override fun showEmptyMessage() = toast("Fields cannot be empty!")
    override fun navigateToHomeScreen() = start(HomeActivity::class.java)
}