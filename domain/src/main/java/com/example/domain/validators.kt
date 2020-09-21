package com.example.domain

fun isEmailValid(email: String?): Boolean{
    if(email == null)
        return false

    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isPasswordValid(password: String?): Boolean{
    return !password.isNullOrEmpty()
}