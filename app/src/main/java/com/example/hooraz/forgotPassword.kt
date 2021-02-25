package com.example.hooraz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_forgot_password.*

class forgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        supportActionBar?.hide()
        ForogotToLogin.setOnClickListener{
            var intent = Intent(forgotPassword@this , MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        var intent = Intent(forgotPassword@this , MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}