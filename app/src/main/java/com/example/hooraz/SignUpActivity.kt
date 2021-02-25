package com.example.hooraz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    supportActionBar?.hide()
        SignUpToLogin.setOnClickListener{
            var intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            AnimationBetweenActivity.animateSlideRight(this)
            finish()
        }

    }
    override fun onBackPressed() {
        var intent = Intent(forgotPassword@this , MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}