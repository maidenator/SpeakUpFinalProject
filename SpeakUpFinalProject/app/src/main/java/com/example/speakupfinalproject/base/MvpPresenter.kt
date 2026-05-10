package com.example.speakupfinalproject.base

/**
 * Base interface for all Presenters in the MVP pattern.
 */
interface MvpPresenter<V : MvpView> {
    fun attachView(view: V)
    fun detachView()
    fun destroy()
}
