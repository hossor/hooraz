package com.example.hooraz.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.hooraz.R

class CoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)
        var extraCoin = intent.extras
        if (extraCoin!=null)
        {
            Log.d("CoinName" , extraCoin.getString("coinName").toString())
        }
    }
}