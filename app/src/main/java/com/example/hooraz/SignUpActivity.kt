 package com.example.hooraz

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*
import www.sanju.motiontoast.MotionToast


 class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    supportActionBar?.hide()

        SignUpToLogin.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            AnimationBetweenActivity.animateSlideRight(this)
            finish()
        }
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("لطفا منتظر بمانید...");
        progressDialog.setMessage("در حال بارگذاری...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        SignUpButton.setOnClickListener{
            try {
               progressDialog.show()
progressDialog.setCancelable(true)
                progressDialog.max = 100
               var pd =  progressDialog.show()
                //Toast.makeText(this , UsernameEdit.text , Toast.LENGTH_SHORT).show()
            var queue = Volley.newRequestQueue(this)
            var url = "http://horaz.ir/signup.php?username=${UsernameEdit.text}&password=${passwordEdit.text}&email=${EmailEdit.text}&info="
            var stringRequest = StringRequest(
                Request.Method.POST,
                url,
                Response.Listener { response ->
                    Log.d("Responsee", response.toString())
                    var status = response.toString()
                    var typeface :Typeface? = ResourcesCompat.getFont(this,R.font.helvetica_regular)

                    if (status.contains("OK")) {
progressDialog.setMessage("کاربر با موفقیت ساخته شد")
                        Log.d("checkLogin" , "کاربر با موفقیت ساخته شد.")
                        progressDialog.max
                        progressDialog.hide()
                       progressDialog.dismiss()
                        var intent = Intent(this , cryptoActivity::class.java)
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
                    else{

                        progressDialog.hide()
                        progressDialog.dismiss()
                        MotionToast.Companion.createColorToast(this
                            ,"خطات"
                            ,"ایمیل یا نام کاربری وارد شده تکراری میباشد",
                            MotionToast.Companion.TOAST_ERROR,
                            MotionToast.Companion.GRAVITY_BOTTOM,
                            MotionToast.Companion.LONG_DURATION,
                            typeface)
                    }
                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?) {
                        Log.d("Erorr", error!!.message.toString())
                    }
                })
queue.add(stringRequest)
        }catch (ex: Exception){
            Log.d("EXEROR", ex.message.toString())
        }
            }

    }
    override fun onBackPressed() {
        var intent = Intent(forgotPassword@ this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}


