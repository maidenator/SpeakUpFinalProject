package com.android.customapplication.app

import android.app.Application
import android.util.Log
import com.android.customapplication.data.Subject
import com.android.customapplication.data.UserInfo

class CustomApp : Application() {

    val username = "test"
    val password = "test"

    private var userInfo = UserInfo()

    override fun onCreate() {
        super.onCreate()
        Log.e("CustomApp", "CustomeApp:onCreate() is called")
    }

    fun getUserInfo() = this.userInfo

    fun setUserInfo(userInfo: UserInfo) {
        this.userInfo = userInfo
    }

    fun appendSubject(subject: Subject) {
        this.userInfo.subjects.add(subject)
    }

    fun removeSubject(index: Int) {
        this.userInfo.subjects.removeAt(index)
    }
}