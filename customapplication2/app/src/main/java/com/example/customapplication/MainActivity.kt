////Manifest
//<?xml version="1.0" encoding="utf-8"?>
//<manifest xmlns:android="http://schemas.android.com/apk/res/android">
//<application
//android:name=".app.CustomApp"
//android:allowBackup="true"
//android:icon="@mipmap/ic_launcher"
//android:label="@string/app_name"
//android:theme="@android:style/Theme.Material.Light.NoActionBar">
//
//<activity
//android:name=".screens.login.LoginActivity"
//android:exported="true">
//<intent-filter>
//<action android:name="android.intent.action.MAIN" />
//<category android:name="android.intent.category.LAUNCHER" />
//</intent-filter>
//</activity>
//</application>
//</manifest>
////--------------------------------------------------------------------------
////login_activity.xml
//<?xml version="1.0" encoding="utf-8"?>
//<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//android:orientation="vertical"
//android:padding="20dp"
//android:gravity="center">
//
//<EditText
//android:id="@+id/edittextUsername"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:hint="Username" />
//
//<EditText
//android:id="@+id/edittextPassword"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:hint="Password"
//android:inputType="textPassword" />
//
//<Button
//android:id="@+id/buttonLogin"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:text="Login"
//android:layout_marginTop="20dp" />
//</LinearLayout>
////------------------------------------------------------------------------------
////UserInfo.kt
//package com.android.customapplication.data
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
//data class Subject (
//    var subjectCode: String = ""
//)
////------------------------------------------------------------------------------
////CustomApp.kt
//package com.android.customapplication.app
//
//import android.app.Application
//import android.util.Log
//import com.android.customapplication.data.Subject
//import com.android.customapplication.data.UserInfo
//
//class CustomApp : Application() {
//    val username = "test"
//    val password = "test"
//    private var userInfo = UserInfo()
//
//    override fun onCreate() {
//        super.onCreate()
//        Log.e("CustomApp", "CustomApp:onCreate() is called")
//    }
//
//    fun getUserInfo() = this.userInfo
//    fun setUserInfo(userInfo: UserInfo) { this.userInfo = userInfo }
//    fun appendSubject(subject: Subject) { this.userInfo.subjects.add(subject) }
//    fun removeSubject(index: Int) { this.userInfo.subjects.removeAt(index) }
//}
////----------------------------------------------------------------------
////KotlinExtensions.kt
//package com.android.customapplication.utils
//
//import android.app.Activity
//import android.content.Intent
//import android.view.View
//import android.widget.*
//import com.android.customapplication.app.CustomApp
//
//fun Activity.getEditTextValue(id: Int) = findViewById<EditText>(id).text.toString()
//fun Activity.app(): CustomApp = application as CustomApp
//fun Activity.start(toClass: Class<*>?) { startActivity(Intent(this, toClass)) }
//fun Activity.toast(msg: String) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show() }
//fun Activity.getButtonView(id: Int) = findViewById<Button>(id)
//fun Activity.setTextViewText(id: Int, string: String) { (findViewById<TextView>(id)).setText(string) }
//fun Activity.setEditTextText(id: Int, string: String) { findViewById<EditText>(id).setText("$string") }
//fun Activity.enable(id: Int) { (findViewById<View>(id)).isEnabled = true }
//fun Activity.disable(id: Int) { (findViewById<View>(id)).isEnabled = false }
////-----------------------------------------------------------------------------------
////LoginContract.kt
//package com.android.customapplication.screens.login
//
//interface LoginContract {
//    interface View {
//        fun showSuccessMessage()
//        fun showInvalidCredentialMessage()
//        fun showEmptyMessage()
//        fun navigateToHomeScreen()
//        fun showGenericErrorMessage()
//    }
//    interface Presenter {
//        fun login(username: String, password: String)
//    }
//}
////----------------------------------------------------------------------------
////LoginPresenter.kt
//package com.android.customapplication.screens.login
//
//import android.app.Activity
//import com.android.customapplication.data.UserInfo
//import com.android.customapplication.utils.app
//
//class LoginPresenter(private val view: LoginContract.View,
//                     private val loginModel: LoginModel) : LoginContract.Presenter {
//
//    private val app = (view as Activity).app()
//
//    override fun login(username: String, password: String) {
//        if (username.isNotEmpty() && password.isNotEmpty()) {
//            if (loginModel.login(username, password)) {
//                app.setUserInfo(UserInfo(username = username, password = password))
//                view.showSuccessMessage()
//                view.navigateToHomeScreen()
//            } else {
//                view.showInvalidCredentialMessage()
//            }
//        } else {
//            view.showEmptyMessage()
//        }
//    }
//}
////-----------------------------------------------------------------------------
////LoginActivity.kt
//package com.android.customapplication.screens.login
//
//import android.app.Activity
//import android.os.Bundle
//import com.android.customapplication.R
//import com.android.customapplication.utils.*
//
//class LoginActivity : Activity(), LoginContract.View {
//    private lateinit var presenter: LoginPresenter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//        presenter = LoginPresenter(this, LoginModel())
//
//        getButtonView(R.id.buttonLogin).setOnClickListener {
//            val username = getEditTextValue(R.id.edittextUsername)
//            val password = getEditTextValue(R.id.edittextPassword)
//            presenter.login(username, password)
//        }
//    }
//
//    override fun showSuccessMessage() { toast("Login successful!") }
//    override fun showInvalidCredentialMessage() { toast("Invalid credentials!") }
//    override fun showEmptyMessage() { toast("Fields cannot be empty!") }
//
//    override fun navigateToHomeScreen() {
//        // start(HomeActivity::class.java)
//        toast("Success! Navigation is disabled for this exam.")
//    }
//
//    override fun showGenericErrorMessage() { toast("Unexpected error encountered.") }
//}