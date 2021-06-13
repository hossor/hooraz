package com.example.hooraz.Model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.hooraz.Views.CoinView
import com.example.hooraz.presenter.CoinPresenter
import org.json.JSONArray

class CoinModel : CoinPresenter {
    lateinit var CoinView: CoinView

    constructor(CoinView: CoinView) {
        this.CoinView = CoinView
    }

    override fun CoinChanges(CoinName: String, context: Context) {
        var queue = Volley.newRequestQueue(context)
        var url =
            "https://api.nomics.com/v1/currencies/ticker?key=1df9b33bff3f2c4dd9831f949856e338&ids=" + CoinName.toUpperCase() + "&interval=1d,30d&convert=USD&per-page=200&page=1"
        var stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { response ->
                Log.d("Responsee", response.toString() + CoinName)
                if (response.toString().isNotEmpty()) {
                    var jsonCoin: JSONArray = JSONArray(response)

                    CoinView.CoinChangesSuccesfully(jsonCoin)
                }

            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.d("Erorr", error!!.message.toString())
                }
            })
        queue.add(stringRequest)
    }

}