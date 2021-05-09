package com.example.hooraz.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hooraz.Model.ListCoinModel
import com.example.hooraz.R
import com.example.hooraz.Recyclerview.CoinModel
import com.example.hooraz.Recyclerview.CoinsAdapter
import com.example.hooraz.Views.ListCoinView
import kotlinx.android.synthetic.main.activity_crypto.*
import kotlinx.android.synthetic.main.coinsoflist.*
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

class cryptoActivity : AppCompatActivity(), ListCoinView,CoinsAdapter.OnCoinClickListener{
    lateinit var ListCoinModel: ListCoinModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto)
        ListCoinModel = ListCoinModel(cryptoActivity@ this)
        ListCoinModel.Coinfun()

        adapterListCoin()

    }

    override fun adapterListCoin() {


        val coinslist: ArrayList<CoinModel> = ArrayList<CoinModel>()
        var ss = "ss"
        var queue = Volley.newRequestQueue(applicationContext)
        var url =
            "https://api.nomics.com/v1/currencies/ticker?key=1df9b33bff3f2c4dd9831f949856e338&ids=&interval=1d,30d&convert=USD&per-page=100&page=1"
        var stringRequest =
            StringRequest(Request.Method.GET, url, object : Response.Listener<String> {
                override fun onResponse(response: String?) {
                    Log.d("CoinStringJson", response.toString())
                    var jsonCoins = JSONArray(response)
                    var jsonCount = jsonCoins.length()
                    coinslist.clear()
                    for (i in 0..jsonCount - 1) {
                        var ID = jsonCoins.getJSONObject(i).optString("symbol")
                        var Name = jsonCoins.getJSONObject(i).optString("name")
                        var Price = jsonCoins.getJSONObject(i).optString("price")
                        var PriceChange = ""
                        var oneDayInfo = jsonCoins.getJSONObject(i).optString("1d")
                        Log.d("KKKK", oneDayInfo)
                        var json1DInfo = JSONArray("["+oneDayInfo+"]")
                        var json1DInfoLenght = json1DInfo.length()

                            PriceChange = json1DInfo.getJSONObject(0).optString("price_change_pct")
                        var floatPriceChange = PriceChange.toFloat() * 100


                        val Model = CoinModel(ID + "", Name + "", Price + "", floatPriceChange.toString() )
                        coinslist.add(Model)
                        Log.d("MODDEl", ID)
                    }
                    Log.d("CoinsInList2", coinslist.size.toString())



                    recyClerViewCoins.apply {


                        layoutManager = LinearLayoutManager(this@cryptoActivity)
                        itemAnimator = DefaultItemAnimator()
                        adapter = CoinsAdapter(coinslist , this@cryptoActivity)

                    }

                }
            }, Response.ErrorListener {
                Log.d("ErrorGetCoin", it.message.toString())
            })
        queue.add(stringRequest)

    }

    override fun onCoinClick(item: CoinModel, position: Int) {

        var coinIntent = Intent(this , CoinActivity::class.java)
        coinIntent.putExtra("coinName" , item.NameCoin)
        startActivity(coinIntent)
Toast.makeText(this , ""+item.NameCoin +" " , Toast.LENGTH_LONG).show()
    }


}