package com.example.hooraz.presenter

import android.content.Context

interface LoginPresenter {
    fun preFormLogin(username:String ,password: String , context: Context)
}