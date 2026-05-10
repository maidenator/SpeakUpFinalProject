package com.example.myproject.utils

import android.app.Activity
import android.content.Intent
import android.widget.*
import com.example.myproject.app.CustomApp

fun Activity.getEditTextValue(id: Int) = findViewById<EditText>(id).text.toString()
fun Activity.app(): CustomApp = application as CustomApp
fun Activity.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Activity.getButtonView(id: Int) = findViewById<Button>(id)
fun Activity.start(toClass: Class<*>) = startActivity(Intent(this, toClass))