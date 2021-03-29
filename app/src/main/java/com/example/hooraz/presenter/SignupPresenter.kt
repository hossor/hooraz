package com.example.hooraz.presenter

import android.content.Context

interface SignupPresenter {
    fun preformSignUp(username:String , password:String , Email:String , context: Context)
}