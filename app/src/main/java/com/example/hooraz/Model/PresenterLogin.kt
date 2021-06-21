package com.example.hooraz.Model

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hooraz.Views.LoginView
import com.example.hooraz.presenter.LoginPresenter

class PresenterLogin : LoginPresenter, AppCompatActivity {
    lateinit var loginView: LoginView

    constructor(loginView: LoginView) {
        this.loginView = loginView
    }

    val MyPref = "username"
    val Name = "nameKey"
    override fun preFormLogin(username: String, password: String , context: Context) {
//check Login
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            loginView.loginValidations()
        } else {
            val progressDialog = ProgressDialog(context)
            progressDialog.setTitle("لطفا منتظر بمانید...");
            progressDialog.setMessage("در حال بارگذاری...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show()
            var queue = Volley.newRequestQueue(context)
            var url = "http://horaz.ir/login.php?username=${username}&password=${password}"
            var stringRequest = StringRequest(Request.Method.GET,
                url,
                Response.Listener { response ->
                    var status = response.toString()
                    Log.d("Response" , status)
                    var shPref:SharedPreferences  = context.getSharedPreferences(MyPref, Context.MODE_PRIVATE);
                    if (status.contains("Login successfully"))
                    {   progressDialog.hide()
                        progressDialog.dismiss()
                        var sEdit:SharedPreferences.Editor = shPref.edit()
                        sEdit.putString(Name , username)
                        sEdit.apply()
                        loginView.loginSuccess()


                    }
                    else if (status.contains("Login faild")){

                        progressDialog.dismiss()
                        progressDialog.hide()
                        loginView.loginError()


                    }
                }, Response.ErrorListener {
                    Log.d("Response" , it.message.toString())

                }
            )
            queue.add(stringRequest)
        }
    }
}