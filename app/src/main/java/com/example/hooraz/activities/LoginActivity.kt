package com.example.hooraz.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.hooraz.Model.PresenterLogin
import com.example.hooraz.R
import com.example.hooraz.Views.LoginView
import kotlinx.android.synthetic.main.activity_main.*
import www.sanju.motiontoast.MotionToast


open class LoginActivity : AppCompatActivity() , LoginView{
    lateinit var presenterLogin:PresenterLogin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenterLogin = PresenterLogin(LoginActivity@this)
        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        supportActionBar?.hide()
        LoginToSignUp.setOnClickListener{
            //start activity
            var intent = Intent(MainActivity@this , SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
//        ForgotPasswordtext.setOnClickListener{
//            var intent = Intent(MainActivity@this , forgotPassword::class.java)
//            startActivity(intent)
//finish()
//
//        }
        LoginButton.setOnClickListener {
            presenterLogin.preFormLogin(UsernameLoginedit.text.toString() , passwordEdit.text.toString(), this)
        }

    }

    override fun loginValidations() {
        TODO("Not yet implemented")
    }

    override fun loginSuccess() {


        var intent = Intent(this , cryptoActivity::class.java)
        var typeface : Typeface? = ResourcesCompat.getFont(this,
            R.font.helvetica_regular
        )

        MotionToast.Companion.createColorToast(this
            ,"خوش آمدید"
            ,"ورود با موفقیت انجام شد",
            MotionToast.Companion.TOAST_SUCCESS,
            MotionToast.Companion.GRAVITY_BOTTOM,
            MotionToast.Companion.LONG_DURATION,
            typeface)

        startActivity(intent)
        finish()    }

    override fun loginError() {
        var typeface : Typeface? = ResourcesCompat.getFont(this,
            R.font.helvetica_regular
        )
        MotionToast.Companion.createColorToast(this
            ,"اخطار"
            ,"ورود ناموفق",
            MotionToast.Companion.TOAST_ERROR,
            MotionToast.Companion.GRAVITY_BOTTOM,
            MotionToast.Companion.LONG_DURATION,
            typeface)    }
}