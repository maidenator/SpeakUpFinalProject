package com.example.speakupfinalproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.speakupfinalproject.feature_auth.UserRole
import com.example.speakupfinalproject.feature_interaction.AnswerQuestionActivity
import com.example.speakupfinalproject.feature_interaction.AskQuestionActivity
import com.example.speakupfinalproject.feature_question_feed.Question
import com.example.speakupfinalproject.feature_question_feed.QuestionAdapter
import com.example.speakupfinalproject.feature_question_feed.QuestionFeedContract
import com.example.speakupfinalproject.feature_question_feed.QuestionFeedModel
import com.example.speakupfinalproject.feature_question_feed.QuestionFeedPresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), QuestionFeedContract.View {

    private lateinit var presenter: QuestionFeedContract.Presenter
    private lateinit var adapter: QuestionAdapter
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyTextView: TextView
    private lateinit var fab: FloatingActionButton

    // For simplicity in MainActivity, we'll assume STUDENT role
    private val currentRole = UserRole.STUDENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initPresenter()
        
        presenter.loadQuestions(currentRole)
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerViewQuestions)
        progressBar = findViewById(R.id.progressBar)
        emptyTextView = findViewById(R.id.textViewEmpty)
        fab = findViewById(R.id.fabAddQuestion)

        adapter = QuestionAdapter(
            questions = emptyList(),
            onQuestionClick = { question -> presenter.onQuestionClicked(question, currentRole) },
            onUpvoteClick = { question -> presenter.onUpvoteClicked(question) }
        )
        recyclerView.adapter = adapter

        fab.setOnClickListener {
            presenter.onAddQuestionClicked()
        }
    }

    private fun initPresenter() {
        val model = QuestionFeedModel()
        presenter = QuestionFeedPresenter(model)
        presenter.attachView(this)
    }

    override fun showQuestions(questions: List<Question>) {
        recyclerView.visibility = View.VISIBLE
        emptyTextView.visibility = View.GONE
        adapter.updateData(questions)
    }

    override fun showEmptyState() {
        recyclerView.visibility = View.GONE
        emptyTextView.visibility = View.VISIBLE
    }

    override fun updateQuestionItem(question: Question) {
        adapter.updateItem(question)
    }

    override fun setFabVisibility(isVisible: Boolean) {
        fab.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun navigateToAskQuestion() {
        val intent = Intent(this, AskQuestionActivity::class.java)
        startActivity(intent)
    }

    override fun navigateToAnswerQuestion(questionId: String) {
        val intent = Intent(this, AnswerQuestionActivity::class.java).apply {
            putExtra("QUESTION_ID", questionId)
        }
        startActivity(intent)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        presenter.destroy()
    }
}
