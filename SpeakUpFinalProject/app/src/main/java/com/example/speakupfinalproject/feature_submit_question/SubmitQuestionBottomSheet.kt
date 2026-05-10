package com.example.speakupfinalproject.feature_submit_question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.speakupfinalproject.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SubmitQuestionBottomSheet : BottomSheetDialogFragment(), SubmitQuestionContract.View {

    private lateinit var presenter: SubmitQuestionContract.Presenter
    private var onQuestionSubmittedListener: ((String) -> Unit)? = null

    private lateinit var questionEditText: TextInputEditText
    private lateinit var questionInputLayout: TextInputLayout
    private lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_submit_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        questionEditText = view.findViewById(R.id.editTextQuestion)
        questionInputLayout = view.findViewById(R.id.textInputLayoutQuestion)
        submitButton = view.findViewById(R.id.buttonSubmit)

        presenter = SubmitQuestionPresenter()
        presenter.attachView(this)

        submitButton.setOnClickListener {
            val content = questionEditText.text.toString()
            presenter.submitQuestion(content)
        }
    }

    fun setOnQuestionSubmittedListener(listener: (String) -> Unit) {
        this.onQuestionSubmittedListener = listener
    }

    override fun onValidationSuccess(content: String) {
        onQuestionSubmittedListener?.invoke(content)
    }

    override fun onValidationError(errorMessage: String) {
        questionInputLayout.error = errorMessage
    }

    override fun dismissDialog() {
        dismiss()
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        submitButton.isEnabled = false
    }

    override fun hideLoading() {
        submitButton.isEnabled = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}
