package com.example.hooraz.Model

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.hooraz.presenter.ListPresenter

class ListCoinModel:ListPresenter, AppCompatActivity{
lateinit var context: Context
    constructor(context: Context)
    {
        this.context = context
    }
    override fun Coinfun() {


    }
}