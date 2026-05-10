package com.example.speakupfinalproject.feature_question_feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.speakupfinalproject.R
import com.google.android.material.chip.Chip

class QuestionAdapter(
    private var questions: List<Question>,
    private val onQuestionClick: (Question) -> Unit,
    private val onUpvoteClick: (Question) -> Unit
) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val authorText: TextView = view.findViewById(R.id.textViewAuthor)
        val contentText: TextView = view.findViewById(R.id.textViewContent)
        val upvotesChip: Chip = view.findViewById(R.id.chipUpvotes)
        val timestampText: TextView = view.findViewById(R.id.textViewTimestamp)
        val answeredIndicator: View = view.findViewById(R.id.viewAnsweredIndicator)
        val answeredBadge: TextView = view.findViewById(R.id.textViewAnsweredBadge)

        fun bind(question: Question) {
            authorText.text = if (question.isAnonymous) "Anonymous" else question.author
            contentText.text = question.content
            upvotesChip.text = "${question.upvoteCount} Upvotes"
            timestampText.text = question.timeAgo

            // Emerald styling for answered questions
            if (question.isAnswered) {
                answeredIndicator.visibility = View.VISIBLE
                answeredBadge.visibility = View.VISIBLE
            } else {
                answeredIndicator.visibility = View.GONE
                answeredBadge.visibility = View.GONE
            }
            
            itemView.setOnClickListener { onQuestionClick(question) }
            upvotesChip.setOnClickListener { onUpvoteClick(question) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount() = questions.size

    fun updateData(newQuestions: List<Question>) {
        questions = newQuestions
        notifyDataSetChanged()
    }

    fun updateItem(updatedQuestion: Question) {
        val index = questions.indexOfFirst { it.id == updatedQuestion.id }
        if (index != -1) {
            val newList = questions.toMutableList()
            newList[index] = updatedQuestion
            questions = newList
            notifyItemChanged(index)
        }
    }
}
