package com.example.hooraz.Model

import android.text.TextUtils
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

    override fun preFormLogin(username: String, password: String) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            loginView.loginValidations()
        } else {
            var queue = Volley.newRequestQueue(this)
            var url = "http://horaz.ir/login.php?username=${username}&password=${password}"
            var stringRequest = StringRequest(Request.Method.GET,
                url,
                Response.Listener { response ->
                    var status = response.toString()
                    if (status.contains("Login successfully"))
                    {
                        loginView.loginSuccess()
                    }
                    else if (status.contains("Login Faild")){
                        loginView.loginError()
                    }
                }, Response.ErrorListener {

                }
            )
            queue.add(stringRequest)
        }
    }
}