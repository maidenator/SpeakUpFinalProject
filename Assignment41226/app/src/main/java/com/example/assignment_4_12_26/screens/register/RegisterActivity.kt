package com.example.assignment_4_12_26.screens.register

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_4_12_26.R
import com.example.assignment_4_12_26.data.repositories.UserRepository

class RegisterActivity : AppCompatActivity(), RegisterContract.View {
    private lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter = RegisterPresenter(this, UserRepository())

        // Find the UI elements we just created in the XML
        val etName = findViewById<EditText>(R.id.etRegName)
        val etEmail = findViewById<EditText>(R.id.etRegEmail)
        val etPass = findViewById<EditText>(R.id.etRegPassword)
        val cbRules = findViewById<CheckBox>(R.id.cbCodeOfConduct)
        val btnSubmit = findViewById<Button>(R.id.btnRegisterSubmit)

        // When the button is clicked, grab the actual text the user typed
        btnSubmit.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val pass = etPass.text.toString()
            val agreed = cbRules.isChecked

            // Send real data to the presenter
            presenter.register(name, email, pass, agreed)
        }
    }

    override fun showValidationError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showRegistrationSuccess() {
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
        finish() // Closes register screen and goes back to login
    }
}