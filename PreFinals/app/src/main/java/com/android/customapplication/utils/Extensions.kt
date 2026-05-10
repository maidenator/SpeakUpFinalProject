package com.android.customapplication.utils
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.widget.*
import com.android.customapplication.data.UserInfo

class CustomApp : Application() {
    private var userInfo = UserInfo()
    fun setUserInfo(info: UserInfo) { userInfo = info }
    fun getUserInfo(): UserInfo = userInfo
}

fun Activity.app() = application as CustomApp
fun Activity.toast(m: String) = Toast.makeText(this, m, Toast.LENGTH_SHORT).show()
fun Activity.start(c: Class<*>) = startActivity(Intent(this, c))
fun Activity.getButtonView(id: Int) = findViewById<Button>(id)
fun Activity.getEditTextValue(id: Int) = findViewById<EditText>(id).text.toString()