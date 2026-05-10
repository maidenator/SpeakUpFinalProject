package com.example.speakupfinalproject.feature_question_feed

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.speakupfinalproject.QuestionRepository
import com.example.speakupfinalproject.R
import com.example.speakupfinalproject.feature_auth.UserRole
import com.example.speakupfinalproject.feature_interaction.AnswerQuestionActivity
import com.example.speakupfinalproject.feature_interaction.AskQuestionActivity
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.UUID

/**
 * View implementation for the Question Feed.
 * 
 * This is the 'heart' of SpeakUp, where questions are displayed, upvoted, 
 * and managed based on the user's role.
 */
class QuestionFeedActivity : AppCompatActivity(), QuestionFeedContract.View {

    private lateinit var presenter: QuestionFeedContract.Presenter
    private lateinit var adapter: QuestionAdapter
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyTextView: TextView
    private lateinit var fab: FloatingActionButton
    private lateinit var roleToggleGroup: MaterialButtonToggleGroup

    private var currentRole: UserRole = UserRole.STUDENT

    // Registering for activity results to refresh the feed
    private val askQuestionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val content = result.data?.getStringExtra("NEW_QUESTION_CONTENT") ?: ""
            val isAnonymous = result.data?.getBooleanExtra("IS_ANONYMOUS", true) ?: true
            
            // Add new question to repository for the demo
            QuestionRepository.addQuestion(Question(
                id = UUID.randomUUID().toString(),
                content = content,
                timeAgo = "Just now",
                isAnonymous = isAnonymous
            ))
            presenter.refreshFeed()
        }
    }

    private val answerQuestionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val questionId = result.data?.getStringExtra("ANSWERED_QUESTION_ID") ?: ""
            val answer = result.data?.getStringExtra("ANSWER_CONTENT") ?: ""
            
            // Mark as answered in repository
            QuestionRepository.answerQuestion(questionId, answer)
            presenter.refreshFeed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initPresenter()
        handleIntentRole()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerViewQuestions)
        progressBar = findViewById(R.id.progressBar)
        emptyTextView = findViewById(R.id.textViewEmpty)
        fab = findViewById(R.id.fabAddQuestion)
        roleToggleGroup = findViewById(R.id.toggleGroupPerspective)

        adapter = QuestionAdapter(
            questions = emptyList(),
            onQuestionClick = { question -> presenter.onQuestionClicked(question, currentRole) },
            onUpvoteClick = { question -> presenter.onUpvoteClicked(question) }
        )
        recyclerView.adapter = adapter

        fab.setOnClickListener {
            presenter.onAddQuestionClicked()
        }

        // Demo Role Switcher logic
        roleToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                currentRole = if (checkedId == R.id.buttonProfessorPersp) {
                    UserRole.PROFESSOR
                } else {
                    UserRole.STUDENT
                }
                presenter.loadQuestions(currentRole)
            }
        }
    }

    private fun handleIntentRole() {
        val roleName = intent.getStringExtra("USER_ROLE") ?: UserRole.STUDENT.name
        currentRole = UserRole.valueOf(roleName)
        
        // Update toggle group state
        if (currentRole == UserRole.PROFESSOR) {
            roleToggleGroup.check(R.id.buttonProfessorPersp)
        } else {
            roleToggleGroup.check(R.id.buttonStudentPersp)
        }
        
        presenter.loadQuestions(currentRole)
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
        askQuestionLauncher.launch(intent)
    }

    override fun navigateToAnswerQuestion(questionId: String) {
        val intent = Intent(this, AnswerQuestionActivity::class.java).apply {
            putExtra("QUESTION_ID", questionId)
            putExtra("USER_ROLE", currentRole.name)
        }
        answerQuestionLauncher.launch(intent)
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
