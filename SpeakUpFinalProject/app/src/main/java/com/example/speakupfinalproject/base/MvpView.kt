package com.example.speakupfinalproject.base

/**
 * Base interface for all Views in the MVP pattern.
 */
interface MvpView {
    fun showError(message: String)
    fun showLoading()
    fun hideLoading()
}
