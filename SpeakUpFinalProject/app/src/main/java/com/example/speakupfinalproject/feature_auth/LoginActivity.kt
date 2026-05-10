package com.example.speakupfinalproject.feature_auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.speakupfinalproject.R
import com.example.speakupfinalproject.feature_dashboard.DashboardActivity
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity(), AuthContract.View {

    private lateinit var presenter: AuthContract.Presenter
    
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var roleToggleGroup: MaterialButtonToggleGroup
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        initPresenter()
    }

    private fun initViews() {
        emailInputLayout = findViewById(R.id.textInputLayoutEmail)
        passwordInputLayout = findViewById(R.id.textInputLayoutPassword)
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        roleToggleGroup = findViewById(R.id.toggleGroupRole)
        loginButton = findViewById(R.id.buttonLogin)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            
            val role = if (roleToggleGroup.checkedButtonId == R.id.buttonProfessor) {
                UserRole.PROFESSOR
            } else {
                UserRole.STUDENT
            }

            presenter.onLoginClicked(email, password, role)
        }
    }

    private fun initPresenter() {
        presenter = AuthPresenter()
        presenter.attachView(this)
    }

    override fun navigateToDashboard(role: UserRole) {
        val intent = Intent(this, DashboardActivity::class.java).apply {
            putExtra("USER_ROLE", role.name)
        }
        startActivity(intent)
        finish()
    }

    override fun showEmailError(message: String) {
        emailInputLayout.error = message
    }

    override fun showPasswordError(message: String) {
        passwordInputLayout.error = message
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        loginButton.isEnabled = false
    }

    override fun hideLoading() {
        loginButton.isEnabled = true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}