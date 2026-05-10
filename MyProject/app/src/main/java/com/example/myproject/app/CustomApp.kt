package com.example.myproject.app

import android.app.Application
import com.example.myproject.data.UserInfo

class CustomApp : Application() {
    private var userInfo = UserInfo()
    fun getUserInfo() = this.userInfo
    fun setUserInfo(userInfo: UserInfo) { this.userInfo = userInfo }
}