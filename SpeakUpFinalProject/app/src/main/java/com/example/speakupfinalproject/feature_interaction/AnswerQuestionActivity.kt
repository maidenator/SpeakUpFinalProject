package com.example.speakupfinalproject.feature_interaction

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.speakupfinalproject.R
import com.example.speakupfinalproject.feature_auth.UserRole
import com.example.speakupfinalproject.feature_question_feed.Question
import com.example.speakupfinalproject.feature_question_feed.QuestionFeedModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText

class AnswerQuestionActivity : AppCompatActivity(), InteractionContract.AnswerView {

    private lateinit var presenter: InteractionContract.Presenter
    private lateinit var questionTextView: TextView
    private lateinit var answerEditText: TextInputEditText
    private lateinit var postAnswerButton: Button
    private lateinit var toolbar: MaterialToolbar
    private lateinit var responseLabel: TextView

    private var currentRole: UserRole = UserRole.STUDENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_question)

        currentRole = UserRole.valueOf(intent.getStringExtra("USER_ROLE") ?: UserRole.STUDENT.name)

        initViews()
        initPresenter()

        val questionId = intent.getStringExtra("QUESTION_ID")
        if (questionId != null) {
            presenter.loadQuestionToAnswer(questionId)
        }
    }

    private fun initViews() {
        toolbar = findViewById(R.id.toolbar)
        questionTextView = findViewById(R.id.textViewQuestionContent)
        answerEditText = findViewById(R.id.editTextAnswer)
        postAnswerButton = findViewById(R.id.buttonPostAnswer)
        // Note: activity_answer_question.xml has a "YOUR RESPONSE" label, but no ID. 
        // We'll just use the ones we have.
        
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        if (currentRole == UserRole.STUDENT) {
            toolbar.title = "View Answer"
            answerEditText.isEnabled = false
            answerEditText.hint = "No answer provided yet."
            postAnswerButton.visibility = View.GONE
        } else {
            toolbar.title = "Provide Answer"
            postAnswerButton.setOnClickListener {
                val answer = answerEditText.text.toString()
                val questionId = intent.getStringExtra("QUESTION_ID") ?: ""
                presenter.submitAnswer(questionId, answer)
            }
        }
    }

    private fun initPresenter() {
        presenter = InteractionPresenter(QuestionFeedModel())
        presenter.attachView(this)
    }

    override fun displayQuestion(question: Question) {
        questionTextView.text = question.content
        if (question.answer != null) {
            answerEditText.setText(question.answer)
        }
    }

    override fun onAnswerPosted(questionId: String, answer: String) {
        val resultIntent = Intent().apply {
            putExtra("ANSWERED_QUESTION_ID", questionId)
            putExtra("ANSWER_CONTENT", answer)
        }
        setResult(Activity.RESULT_OK, resultIntent)
        Toast.makeText(this, "Question marked as answered", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        postAnswerButton.isEnabled = false
    }

    override fun hideLoading() {
        postAnswerButton.isEnabled = true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
