package com.example.hooraz.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.hooraz.Model.CoinModel
import com.example.hooraz.Model.Info
import com.example.hooraz.R
import com.example.hooraz.Views.CoinView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.activity_coin.*
import org.json.JSONArray
import kotlin.math.abs

class CoinInfo : AppCompatActivity(), CoinView {


    lateinit var coinModel: CoinModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)
        coinModel = CoinModel(CoinModel@ this)
        var extraCoin = intent.extras
        if (extraCoin != null) {
            var name = extraCoin.getString("coinName").toString()
            coinModel.CoinChanges(name, this)


        }
    }


    override fun CoinChange(jsonCoin: JSONArray) {
        try {
            Log.d(
                "CoinInfo",
                "Load ${jsonCoin.optJSONObject(0).optString("logo_url")} successfully"
            )
            var info: Info = Info(
                jsonCoin.optJSONObject(0).optString("logo_url"),
                jsonCoin.optJSONObject(0).optString("name"),
                jsonCoin.optJSONObject(0).optString("price"),
                jsonCoin.optJSONObject(0).optString("1d"),
                jsonCoin.optJSONObject(0).optString("7d"),
                jsonCoin.optJSONObject(0).optString("30d"),
                jsonCoin.optJSONObject(0).optString("365d"),
                jsonCoin.optJSONObject(0).optString("ytd")
            )

            this.CoinName.text = info.name

            if (info.url.contains("svg")) {
                GlideToVectorYou.init()
                    .with(CoinInfo@ this)
                    .load(Uri.parse(info.url), CoinIMG)


            } else {
                Glide.with(CoinInfo@ this).load(info.url).into(CoinIMG)
            }
            Log.d("ErrorCoin", info.day)

            var oneday = JSONArray("[" + info.day + "]").getJSONObject(0).optString("price_change")
            val week = JSONArray("[" + info.week + "]").getJSONObject(0).optString("price_change")
            val month = JSONArray("[" + info.month + "]").getJSONObject(0).optString("price_change")
            val year = JSONArray("[" + info.year + "]").getJSONObject(0).optString("price_change")
            val alltime =
                JSONArray("[" + info.alltime + "]").getJSONObject(0).optString("price_change")
            Log.d("oneday", oneday)
            onedayTextView.text =
                (((oneday.toFloat()) / (abs(info.price.toFloat()))) * 100).toString()
            weekTextview.text = (((week.toFloat()) / (abs(info.price.toFloat()))) * 100).toString()
            monthTextView.text =
                (((month.toFloat()) / (abs(info.price.toFloat()))) * 100).toString()
            yearTextView.text = (((year.toFloat()) / (abs(info.price.toFloat()))) * 100).toString()
            ytdTextView.text =
                (((alltime.toFloat()) / (abs(info.price.toFloat()))) * 100).toString()
            PriceTextView3.text = info.price

        } catch (ex: Exception) {
            Log.e("ErrorCoin", ex.message.toString())
        }
    }


}