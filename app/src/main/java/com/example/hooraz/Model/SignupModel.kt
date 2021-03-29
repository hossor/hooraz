package com.example.hooraz.Model

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hooraz.Views.SignupView
import com.example.hooraz.presenter.SignupPresenter

class SignupModel : SignupPresenter {
    lateinit var SignupView :SignupView
    constructor(signupView: SignupView){
        this.SignupView = signupView
    }
    override fun preformSignUp(username: String, password: String, Email: String , context: Context) {
        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("لطفا منتظر بمانید...");
        progressDialog.setMessage("در حال بارگذاری...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


            try {
                progressDialog.show()
                progressDialog.setCancelable(true)
                progressDialog.max = 100
                var pd =  progressDialog.show()
                //Toast.makeText(this , UsernameEdit.text , Toast.LENGTH_SHORT).show()
                var queue = Volley.newRequestQueue(context)
                var url = "http://horaz.ir/signup.php?username=${username}&password=${password}&email=${Email}&info="
                var stringRequest = StringRequest(
                    Request.Method.POST,
                    url,
                    Response.Listener { response ->
                        Log.d("Responsee", response.toString())
                        var status = response.toString()

                        if (status.contains("OK")) {
                            progressDialog.dismiss()
                            SignupView.signupSuccess()

                        }
                        else{
                            progressDialog.dismiss()
                            SignupView.SignupError()
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