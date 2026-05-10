package com.example.speakupfinalproject.feature_dashboard

import com.example.speakupfinalproject.base.MvpPresenter
import com.example.speakupfinalproject.base.MvpView

interface DashboardContract {

    interface View : MvpView {
        fun showCourses(courses: List<Course>)
        fun navigateToFeed(course: Course)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadCourses()
        fun onCourseClicked(course: Course)
    }
}
