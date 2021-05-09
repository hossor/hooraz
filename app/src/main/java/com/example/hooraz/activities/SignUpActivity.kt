 package com.example.hooraz.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.hooraz.AnimationBetweenActivity
import com.example.hooraz.Model.SignupModel
import com.example.hooraz.R
import com.example.hooraz.Views.SignupView
import kotlinx.android.synthetic.main.activity_sign_up.*
import www.sanju.motiontoast.MotionToast


 class SignUpActivity : AppCompatActivity() , SignupView{
     lateinit var SignupModel:SignupModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        SignupModel = SignupModel(SignUpActivity@this)
        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    supportActionBar?.hide()

        SignUpToLogin.setOnClickListener{
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            AnimationBetweenActivity.animateSlideRight(this)
            finish()
        }
        SignUpButton.setOnClickListener {
            SignupModel.preformSignUp(UsernameEdit.text.toString() ,passwordEdit.text.toString() , EmailEdit.text.toString() , this)

        }


    }
    override fun onBackPressed() {
        var intent = Intent(forgotPassword@ this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

     override fun signupValidations() {
         Log.d("signupValidations" , "signupValidations")
     }


     override fun signupSuccess() {
         var intent = Intent(this , cryptoActivity::class.java)
         var typeface : Typeface? = ResourcesCompat.getFont(this,
             R.font.helvetica_regular
         )

         MotionToast.Companion.createColorToast(this
             ,"خوش آمدید"
             ,"ثبت نام با موفقیت انجام شد",
             MotionToast.Companion.TOAST_SUCCESS,
             MotionToast.Companion.GRAVITY_BOTTOM,
             MotionToast.Companion.LONG_DURATION,
             typeface)
         startActivity(intent)
         finish()
     }

     override fun SignupError() {
         var typeface : Typeface? = ResourcesCompat.getFont(this,
             R.font.helvetica_regular
         )
         MotionToast.Companion.createColorToast(this
             ,"اخطار"
             ,"ثبت نام ناموفق",
             MotionToast.Companion.TOAST_ERROR,
             MotionToast.Companion.GRAVITY_BOTTOM,
             MotionToast.Companion.LONG_DURATION,
             typeface)       }
 }


