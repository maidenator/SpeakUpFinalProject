1. Manifest

Path: app/src/main/AndroidManifest.xml
XML

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
<application
android:name=".app.CustomApp"
android:allowBackup="true"
android:icon="@mipmap/ic_launcher"
android:label="@string/app_name"
android:theme="@android:style/Theme.Material.Light.NoActionBar">

<activity
android:name=".screens.login.LoginActivity"
android:exported="true">
<intent-filter>
<action android:name="android.intent.action.MAIN" />
<category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
</activity>
</application>
</manifest>

2. Layout

Path: app/src/main/res/layout/activity_login.xml
XML

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical"
android:padding="20dp"
android:gravity="center">

<EditText
android:id="@+id/edittextUsername"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="Username" />

<EditText
android:id="@+id/edittextPassword"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="Password"
android:inputType="textPassword" />

<Button
android:id="@+id/buttonLogin"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:text="Login"
android:layout_marginTop="20dp" />
</LinearLayout>

3. Data Model

Path: app/src/main/java/com/example/customapplication/data/UserInfo.kt
Kotlin

package com.example.customapplication.data

data class UserInfo(
    var username: String = "",
    var password: String = "",
    var firstname: String = "",
    var lastname: String = ""
) {
    var subjects = mutableListOf<Subject>()
}

data class Subject(var subjectCode: String = "")

4. Custom Application

Path: app/src/main/java/com/example/customapplication/app/CustomApp.kt
Kotlin

package com.example.customapplication.app

import android.app.Application
import com.example.customapplication.data.UserInfo

class CustomApp : Application() {
    private var userInfo = UserInfo()
    fun getUserInfo() = this.userInfo
    fun setUserInfo(userInfo: UserInfo) { this.userInfo = userInfo }
}

5. Kotlin Extensions

Path: app/src/main/java/com/example/customapplication/utils/Extensions.kt
Kotlin

package com.example.customapplication.utils

import android.app.Activity
import android.content.Intent
import android.widget.*
import com.example.customapplication.app.CustomApp

fun Activity.getEditTextValue(id: Int) = findViewById<EditText>(id).text.toString()
fun Activity.app(): CustomApp = application as CustomApp
fun Activity.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Activity.getButtonView(id: Int) = findViewById<Button>(id)
fun Activity.start(toClass: Class<*>) = startActivity(Intent(this, toClass))

6. Login Contract

Path: app/src/main/java/com/example/customapplication/screens/login/LoginContract.kt
Kotlin

package com.example.customapplication.screens.login

interface LoginContract {
    interface View {
        fun showSuccessMessage()
        fun showInvalidCredentialMessage()
        fun showEmptyMessage()
        fun navigateToHomeScreen()
    }
    interface Presenter {
        fun login(username: String, password: String)
    }
}

7. Login Model

Path: app/src/main/java/com/example/customapplication/screens/login/LoginModel.kt
Kotlin

package com.example.customapplication.screens.login

class LoginModel {
    fun login(u: String, p: String): Boolean = (u == "test" && p == "test")
}

8. Login Presenter

Path: app/src/main/java/com/example/customapplication/screens/login/LoginPresenter.kt
Kotlin

package com.example.customapplication.screens.login

import android.app.Activity
import com.example.customapplication.data.UserInfo
import com.example.customapplication.utils.app

class LoginPresenter(private val view: LoginContract.View, private val model: LoginModel) : LoginContract.Presenter {
    override fun login(username: String, password: String) {
        val app = (view as Activity).app()
        if (username.isNotEmpty() && password.isNotEmpty()) {
            if (model.login(username, password)) {
                app.setUserInfo(UserInfo(username, password))
                view.showSuccessMessage()
                view.navigateToHomeScreen()
            } else view.showInvalidCredentialMessage()
        } else view.showEmptyMessage()
    }
}

9. Login Activity

Path: app/src/main/java/com/example/customapplication/screens/login/LoginActivity.kt
Kotlin

package com.example.customapplication.screens.login

import android.app.Activity
import android.os.Bundle
import com.example.customapplication.R
import com.example.customapplication.utils.*

class LoginActivity : Activity(), LoginContract.View {
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this, LoginModel())

        getButtonView(R.id.buttonLogin).setOnClickListener {
            presenter.login(getEditTextValue(R.id.edittextUsername), getEditTextValue(R.id.edittextPassword))
        }
    }

    override fun showSuccessMessage() = toast("Login successful!")
    override fun showInvalidCredentialMessage() = toast("Invalid credentials!")
    override fun showEmptyMessage() = toast("Fields cannot be empty!")
    override fun navigateToHomeScreen() = toast("Navigation Triggered!")
}