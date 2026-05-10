package com.example.midterms

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LoginActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login)

        val usernameInput = findViewById<EditText>(R.id.usernameInput);
        val passwordInput = findViewById<EditText>(R.id.passwordInput);
        val buttonLogin = findViewById<Button>(R.id.buttonLogin);



        buttonLogin.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if(!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid Input! Please try again", Toast.LENGTH_SHORT).show()
            }
        }
    }
}