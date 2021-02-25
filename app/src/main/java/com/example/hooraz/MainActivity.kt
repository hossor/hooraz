package com.example.hooraz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        LoginToSignUp.setOnClickListener{
            //start activity
            var intent = Intent(MainActivity@this , SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
        ForgotPasswordtext.setOnClickListener{
            var intent = Intent(MainActivity@this , forgotPassword::class.java)
            startActivity(intent)
finish()
        }
    }
}