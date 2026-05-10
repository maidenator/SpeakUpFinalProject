package com.example.speakupfinalproject.feature_dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.speakupfinalproject.R

class CourseAdapter(
    private var courses: List<Course>,
    private val onCourseClick: (Course) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val codeText: TextView = view.findViewById(R.id.textViewCourseCode)
        val nameText: TextView = view.findViewById(R.id.textViewCourseName)
        val studentCountText: TextView = view.findViewById(R.id.textViewStudentCount)
        val liveBadge: View = view.findViewById(R.id.badgeLive)

        fun bind(course: Course) {
            codeText.text = course.code
            nameText.text = course.name
            studentCountText.text = "${course.studentCount} Students"
            liveBadge.visibility = if (course.isLive) View.VISIBLE else View.GONE
            
            itemView.setOnClickListener { onCourseClick(course) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount() = courses.size

    fun updateData(newCourses: List<Course>) {
        courses = newCourses
        notifyDataSetChanged()
    }
}