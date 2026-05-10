package com.example.myproject.data

data class UserInfo(
    var username: String = "",
    var password: String = "",
    var firstname: String = "",
    var lastname: String = ""
) {
    var subjects = mutableListOf<Subject>()
}

data class Subject(var subjectCode: String = "")