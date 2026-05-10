package com.example.speakupfinalproject.feature_interaction

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.speakupfinalproject.R
import com.example.speakupfinalproject.feature_question_feed.QuestionFeedModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText

class AskQuestionActivity : AppCompatActivity(), InteractionContract.AskView {

    private lateinit var presenter: InteractionContract.Presenter
    private lateinit var questionEditText: TextInputEditText
    private lateinit var anonymousSwitch: MaterialSwitch
    private lateinit var postButton: Button
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_question)

        initViews()
        initPresenter()
    }

    private fun initViews() {
        toolbar = findViewById(R.id.toolbar)
        questionEditText = findViewById(R.id.editTextQuestion)
        anonymousSwitch = findViewById(R.id.switchAnonymous)
        postButton = findViewById(R.id.buttonPost)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        postButton.setOnClickListener {
            val content = questionEditText.text.toString()
            val isAnonymous = anonymousSwitch.isChecked
            presenter.submitQuestion(content, isAnonymous)
        }
    }

    private fun initPresenter() {
        // In a real app, use Dependency Injection
        presenter = InteractionPresenter(QuestionFeedModel())
        presenter.attachView(this)
    }

    override fun onQuestionPosted(content: String, isAnonymous: Boolean) {
        val resultIntent = Intent().apply {
            putExtra("NEW_QUESTION_CONTENT", content)
            putExtra("IS_ANONYMOUS", isAnonymous)
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        postButton.isEnabled = false
    }

    override fun hideLoading() {
        postButton.isEnabled = true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
