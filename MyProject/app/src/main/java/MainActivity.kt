///**
// * PROJECT: MyProject
// * PACKAGE: com.example.myproject
// * DATE: April 15, 2026 (Pre-Finals)
// * * --------------------------------------------------------------------------
// * 1. MANIFEST FILE
// * File Name: AndroidManifest.xml
// * Path: app/src/main/AndroidManifest.xml
// * --------------------------------------------------------------------------
// */
//
///*
//<?xml version="1.0" encoding="utf-8"?>
//<manifest xmlns:android="http://schemas.android.com/apk/res/android">
//    <application
//        android:name=".app.CustomApp"
//        android:allowBackup="true"
//        android:icon="@mipmap/ic_launcher"
//        android:label="@string/app_name"
//        android:theme="@style/Theme.MyProject">
//
//        <activity
//            android:name=".screens.login.LoginActivity"
//            android:exported="true">
//            <intent-filter>
//                <action android:name="android.intent.action.MAIN" />
//                <category android:name="android.intent.category.LAUNCHER" />
//            </intent-filter>
//        </activity>
//    </application>
//</manifest>
//*/
//
///**
// * --------------------------------------------------------------------------
// * 2. LAYOUT FILE (XML)
// * File Name: activity_login.xml
// * Path: app/src/main/res/layout/activity_login.xml
// * --------------------------------------------------------------------------
// */
//
///*
//<?xml version="1.0" encoding="utf-8"?>
//<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:orientation="vertical"
//    android:padding="32dp"
//    android:gravity="center"
//    android:background="#F7F9FC"> <TextView
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:text="SpeakUp"
//        android:textSize="42sp"
//        android:textStyle="bold"
//        android:textColor="#D32F2F" android:layout_marginBottom="4dp" />
//
//    <TextView
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:text="Your Voice, Your Safety."
//        android:textSize="16sp"
//        android:textStyle="italic"
//        android:textColor="#555555"
//        android:layout_marginBottom="48dp" />
//
//    <EditText
//        android:id="@+id/edittextUsername"
//        android:layout_width="match_parent"
//        android:layout_height="55dp"
//        android:hint="Username"
//        android:paddingHorizontal="16dp"
//        android:background="#FFFFFF"
//        android:elevation="2dp"
//        android:layout_marginBottom="16dp" />
//
//    <EditText
//        android:id="@+id/edittextPassword"
//        android:layout_width="match_parent"
//        android:layout_height="55dp"
//        android:hint="Password"
//        android:inputType="textPassword"
//        android:paddingHorizontal="16dp"
//        android:background="#FFFFFF"
//        android:elevation="2dp"
//        android:layout_marginBottom="32dp" />
//
//    <Button
//        android:id="@+id/buttonLogin"
//        android:layout_width="match_parent"
//        android:layout_height="60dp"
//        android:text="Secure Login"
//        android:textSize="18sp"
//        android:textStyle="bold"
//        android:textColor="#FFFFFF"
//        android:backgroundTint="#D32F2F"
//        android:elevation="4dp" />
//
//</LinearLayout>
//*/
//
//
///**
// * --------------------------------------------------------------------------
// * 3. DATA CLASS
// * File Name: UserInfo.kt
// * Path: app/src/main/java/com/example/myproject/data/UserInfo.kt
// * --------------------------------------------------------------------------
// */
//
//package com.example.myproject.data
//
//data class UserInfo(
//    var username: String = "",
//    var password: String = "",
//    var firstname: String = "",
//    var lastname: String = ""
//) {
//    var subjects = mutableListOf<Subject>()
//}
//
//data class Subject(var subjectCode: String = "")
//
///**
// * --------------------------------------------------------------------------
// * 4. CUSTOM APPLICATION CLASS
// * File Name: CustomApp.kt
// * Path: app/src/main/java/com/example/myproject/app/CustomApp.kt
// * --------------------------------------------------------------------------
// */
//
//package com.example.myproject.app
//
//import android.app.Application
//import com.example.myproject.data.UserInfo
//
//class CustomApp : Application() {
//    private var userInfo = UserInfo()
//    fun getUserInfo() = this.userInfo
//    fun setUserInfo(userInfo: UserInfo) { this.userInfo = userInfo }
//}
//
///**
// * --------------------------------------------------------------------------
// * 5. KOTLIN EXTENSIONS (UTILS)
// * File Name: Extensions.kt
// * Path: app/src/main/java/com/example/myproject/utils/Extensions.kt
// * --------------------------------------------------------------------------
// */
//
//package com.example.myproject.utils
//
//import android.app.Activity
//import android.content.Intent
//import android.widget.*
//import com.example.myproject.app.CustomApp
//
//fun Activity.getEditTextValue(id: Int) = findViewById<EditText>(id).text.toString()
//fun Activity.app(): CustomApp = application as CustomApp
//fun Activity.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
//fun Activity.getButtonView(id: Int) = findViewById<Button>(id)
//fun Activity.start(toClass: Class<*>) = startActivity(Intent(this, toClass))
//
///**
// * --------------------------------------------------------------------------
// * 6. MVP CONTRACT, MODEL, & PRESENTER
// * File Name: LoginMVP.kt
// * Path: app/src/main/java/com/example/myproject/screens/login/LoginMVP.kt
// * --------------------------------------------------------------------------
// */
//
//package com.example.myproject.screens.login
//
//import android.app.Activity
//import com.example.myproject.data.UserInfo
//import com.example.myproject.utils.app
//
//interface LoginContract {
//    interface View {
//        fun showSuccessMessage()
//        fun showInvalidCredentialMessage()
//        fun showEmptyMessage()
//        fun navigateToHomeScreen()
//    }
//    interface Presenter {
//        fun login(username: String, password: String)
//    }
//}
//
//class LoginModel {
//    fun login(u: String, p: String): Boolean = (u == "test" && p == "test")
//}
//
//class LoginPresenter(private val view: LoginContract.View, private val model: LoginModel) : LoginContract.Presenter {
//    override fun login(username: String, password: String) {
//        val app = (view as Activity).app()
//        if (username.isNotEmpty() && password.isNotEmpty()) {
//            if (model.login(username, password)) {
//                app.setUserInfo(UserInfo(username, password))
//                view.showSuccessMessage()
//                view.navigateToHomeScreen()
//            } else view.showInvalidCredentialMessage()
//        } else view.showEmptyMessage()
//    }
//}
//
///**
// * --------------------------------------------------------------------------
// * 7. LOGIN ACTIVITY (THE VIEW)
// * File Name: LoginActivity.kt
// * Path: app/src/main/java/com/example/myproject/screens/login/LoginActivity.kt
// * --------------------------------------------------------------------------
// */
//
//package com.example.myproject.screens.login
//
//import android.app.Activity
//import android.os.Bundle
//import com.example.myproject.R
//import com.example.myproject.utils.*
//
//class LoginActivity : Activity(), LoginContract.View {
//    private lateinit var presenter: LoginPresenter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        // Initialize Presenter with the View and Model
//        presenter = LoginPresenter(this, LoginModel())
//
//        getButtonView(R.id.buttonLogin).setOnClickListener {
//            presenter.login(
//                getEditTextValue(R.id.edittextUsername),
//                getEditTextValue(R.id.edittextPassword)
//            )
//        }
//    }
//
//    override fun showSuccessMessage() = toast("Login successful!")
//    override fun showInvalidCredentialMessage() = toast("Invalid credentials!")
//    override fun showEmptyMessage() = toast("Fields cannot be empty!")
//    override fun navigateToHomeScreen() = toast("Success: Model Verified")
//}