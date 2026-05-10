package com.example.assignment_4_12_26.screens.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_4_12_26.R
import com.example.assignment_4_12_26.data.repositories.UserRepository
import com.example.assignment_4_12_26.screens.profile.ProfileActivity
import com.example.assignment_4_12_26.screens.register.RegisterActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var presenter: LoginPresenter
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this, UserRepository())
        etEmail = findViewById(R.id.etLoginEmail)
        etPass = findViewById(R.id.etLoginPassword)

        findViewById<Button>(R.id.btnLoginProceed).setOnClickListener {
            presenter.attemptLogin(etEmail.text.toString(), etPass.text.toString())
        }

        findViewById<TextView>(R.id.tvRegisterLink).setOnClickListener {
            presenter.onRegisterClicked()
        }
    }

    override fun showEmailError(msg: String) { etEmail.error = msg }
    override fun showPasswordError(msg: String) { etPass.error = msg }
    override fun showLoginError() { Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show() }

    override fun showLoginSuccess() {
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }

    override fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}