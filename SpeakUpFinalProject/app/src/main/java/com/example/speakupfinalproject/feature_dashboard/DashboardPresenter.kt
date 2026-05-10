package com.example.speakupfinalproject.feature_dashboard

/**
 * Presenter for the Dashboard feature.
 * 
 * FLOW EXPLANATION (View -> Presenter -> Model):
 * 
 * 1. VIEW -> PRESENTER:
 *    The Activity (DashboardActivity) calls 'presenter.loadCourses()' on startup.
 * 
 * 2. PRESENTER (Logic):
 *    The Presenter fetches data. In this demo, it provides a hardcoded list of courses.
 * 
 * 3. PRESENTER -> VIEW:
 *    The Presenter tells the View to show the courses: 'view?.showCourses(courses)'.
 */
class DashboardPresenter : DashboardContract.Presenter {

    private var view: DashboardContract.View? = null

    override fun attachView(view: DashboardContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun destroy() {
        // Cleanup
    }

    override fun loadCourses() {
        view?.showLoading()
        
        // Hardcoded list of courses for the demo
        val courses = listOf(
            Course("CSIT284", "Distributed Systems", 42, true),
            Course("MATH101", "Advanced Calculus", 120, false),
            Course("PHYS202", "Quantum Mechanics", 28, true),
            Course("CSIT301", "Mobile Development", 55, false),
            Course("ENGL110", "Business Communication", 80, true)
        )
        
        view?.hideLoading()
        view?.showCourses(courses)
    }

    override fun onCourseClicked(course: Course) {
        view?.navigateToFeed(course)
    }
}
