package com.example.hooraz.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hooraz.R
import com.example.hooraz.Recyclerview.CoinModel
import com.example.hooraz.Recyclerview.CoinsAdapter
import com.example.hooraz.Views.ListCoinView
import com.example.hooraz.activities.CoinInfo
import kotlinx.android.synthetic.main.fragment_coinlist.*
import org.json.JSONArray
import kotlin.math.abs


class Coinlist(var con: Context) : Fragment(), ListCoinView, CoinsAdapter.OnCoinClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterListCoin()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coinlist, container, false)
    }

    override fun adapterListCoin() {


        val coinslist: ArrayList<CoinModel> = ArrayList<CoinModel>()
        var ss = "ss"
        var queue = Volley.newRequestQueue(con)
        var url =
            "https://api.nomics.com/v1/currencies/ticker?key=1df9b33bff3f2c4dd9831f949856e338&sort=rank&status=active&per-page=50"
        var stringRequest =
            StringRequest(Request.Method.GET, url, object : Response.Listener<String> {
                override fun onResponse(response: String?) {
                    try {


                        Log.d("CoinStringJson", response.toString())
                        var jsonCoins = JSONArray(response)
                        var jsonCount = jsonCoins.length()
                        coinslist.clear()
                        for (i in 0..jsonCount - 1) {
                            var ID = jsonCoins.getJSONObject(i).optString("symbol")
                            var Name = jsonCoins.getJSONObject(i).optString("name")
                            var Price = jsonCoins.getJSONObject(i).optString("price")
                            var URL = jsonCoins.getJSONObject(i).optString("logo_url")


                            var oneDayInfo = jsonCoins.getJSONObject(i).optString("1d")
                            if (oneDayInfo.length != 0) {
                                Log.d("KKKK", oneDayInfo.length.toString())
                                var json1DInfo = JSONArray("[" + oneDayInfo + "]")
                                var json1DInfoLenght = json1DInfo.length()
                                var PriceChange =
                                    json1DInfo.getJSONObject(0).optString("price_change")
                                Log.d("KKKK", PriceChange)

                                var floatPriceChange =
                                    ((PriceChange.toFloat()) / (abs(Price.toFloat()))) * 100


                                val Model = CoinModel(
                                    ID + "",
                                    Name + "",
                                    Price + "",
                                    floatPriceChange.toString(),URL+""
                                )
                                coinslist.add(Model)
                                Log.d("CoinsInList2", coinslist.size.toString())

                                Log.d("MODDEl", ID)
                            }
                        }
                        Log.d("CoinsInList2", coinslist.size.toString())



                        recyClerViewCoins.apply {


                            layoutManager = LinearLayoutManager(con)
                            itemAnimator = DefaultItemAnimator()
                            adapter = CoinsAdapter(coinslist, this@Coinlist,con)

                        }
                    } catch (ex: Exception) {
                        Log.e("KKKK", ex.message.toString())

                    }

                }
            }, Response.ErrorListener {
                Log.d("ErrorGetCoin", it.message.toString())
            })
        queue.add(stringRequest)
    }

    override fun onCoinClick(item: CoinModel, position: Int) {
        var coinIntent = Intent(con, CoinInfo::class.java)
        coinIntent.putExtra("coinName", item.IDName)
        startActivity(coinIntent)
        //Toast.makeText(con , ""+item.IDName +" " , Toast.LENGTH_LONG).show()
    }


}