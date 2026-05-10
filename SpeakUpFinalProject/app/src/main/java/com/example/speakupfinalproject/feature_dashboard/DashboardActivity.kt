package com.example.speakupfinalproject.feature_dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.speakupfinalproject.R
import com.example.speakupfinalproject.feature_question_feed.QuestionFeedActivity

class DashboardActivity : AppCompatActivity(), DashboardContract.View {

    private lateinit var presenter: DashboardContract.Presenter
    private lateinit var adapter: CourseAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        initViews()
        initPresenter()
        
        presenter.loadCourses()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerViewCourses)
        progressBar = findViewById(R.id.progressBar)

        adapter = CourseAdapter(emptyList()) { course ->
            presenter.onCourseClicked(course)
        }
        recyclerView.adapter = adapter
    }

    private fun initPresenter() {
        presenter = DashboardPresenter()
        presenter.attachView(this)
    }

    override fun showCourses(courses: List<Course>) {
        adapter.updateData(courses)
    }

    override fun navigateToFeed(course: Course) {
        val intent = Intent(this, QuestionFeedActivity::class.java).apply {
            putExtra("COURSE_CODE", course.code)
            putExtra("COURSE_NAME", course.name)
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}