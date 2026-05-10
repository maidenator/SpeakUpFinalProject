package com.example.assignment_4_12_26.screens.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_4_12_26.R
import com.example.assignment_4_12_26.data.models.User
import com.example.assignment_4_12_26.data.repositories.UserRepository
import com.example.assignment_4_12_26.screens.login.LoginActivity

class ProfileActivity : AppCompatActivity(), ProfileContract.View {
    private lateinit var presenter: ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        presenter = ProfilePresenter(this, UserRepository())

        presenter.loadProfile()

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            presenter.logout()
        }
    }

    override fun displayUserData(user: User) {
        findViewById<TextView>(R.id.tvProfileName).text = user.fullName
        findViewById<TextView>(R.id.tvProfileEmail).text = user.email

        findViewById<TextView>(R.id.tvQuestionsCount).text = user.questionsAsked.toString()
        findViewById<TextView>(R.id.tvUpvotesCount).text = user.upvotesReceived.toString()
    }

    override fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}