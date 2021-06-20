package com.example.hooraz.activities

import android.graphics.Color
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
            //Send currency name to receive information
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

            //Receive currency information
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

            //set name currency
            this.CoinName.text = info.name
            //check svg format
            if (info.url.contains("svg")) {
                GlideToVectorYou.init()
                    .with(CoinInfo@ this)
                    .load(Uri.parse(info.url), CoinIMG)


            } else {
                Glide.with(CoinInfo@ this).load(info.url).into(CoinIMG)
            }
            Log.d("ErrorCoin", info.day)
            //Convert prices to Jason
            var oneday = (JSONArray("[" + info.day + "]").getJSONObject(0).optString("price_change")
                .toFloat()).toDouble().toString()
            var week = JSONArray("[" + info.week + "]").getJSONObject(0).optString("price_change")
            var month = JSONArray("[" + info.month + "]").getJSONObject(0).optString("price_change")
            var year = JSONArray("[" + info.year + "]").getJSONObject(0).optString("price_change")
            var alltime =
                JSONArray("[" + info.alltime + "]").getJSONObject(0).optString("price_change")
            Log.d("oneday", oneday)

            oneday =
                "%.2f".format(((oneday.toFloat()) / (abs(info.price.toFloat()))) * 100).toDouble()
                    .toString();
            week =
                "%.2f".format((((week.toFloat()) / (abs(info.price.toFloat()))) * 100)).toDouble()
                    .toString();
            month =
                "%.2f".format((((month.toFloat()) / (abs(info.price.toFloat()))) * 100)).toDouble()
                    .toString();
            year =
                "%.2f".format((((year.toFloat()) / (abs(info.price.toFloat()))) * 100)).toDouble()
                    .toString();
            alltime = "%.2f".format((((alltime.toFloat()) / (abs(info.price.toFloat()))) * 100))
                .toDouble().toString();
//Convert price changes to percentages
            onedayTextView.text = oneday
                weekTextview.text = week

                monthTextView.text = month

                yearTextView.text = year

                ytdTextView.text = alltime
            //change color one day
            if (oneday.contains("-")){
                onedayTextView.setTextColor( Color.parseColor("#FF0000"))
            }else{
                onedayTextView.setTextColor( Color.parseColor("#37FF00"))
                onedayTextView.text = "+$oneday"

            }
            //change color week
            if (week.contains("-")){
                weekTextview.setTextColor( Color.parseColor("#FF0000"))
            }else{
                weekTextview.setTextColor( Color.parseColor("#37FF00"))
                weekTextview.text = "+$week"

            }
            //change color month
            if (month.contains("-")){
                monthTextView.setTextColor( Color.parseColor("#FF0000"))
            }else{
                monthTextView.setTextColor( Color.parseColor("#37FF00"))
                monthTextView.text = "+$month"

            }
            //change color one day
            if (year.contains("-")){
                yearTextView.setTextColor( Color.parseColor("#FF0000"))
            }else{
                yearTextView.setTextColor( Color.parseColor("#37FF00"))
                yearTextView.text = "+$year"

            }
            //change color alltime
            if (alltime.contains("-")){
                ytdTextView.setTextColor( Color.parseColor("#FF0000"))
            }else{
                ytdTextView.setTextColor( Color.parseColor("#37FF00"))
                ytdTextView.text = "+$alltime"

            }
            var price = "%.2f".format(info.price.toFloat()).toDouble().toString()
            Log.d("CoinPrice" , price)
            PriceTextView3.text = price

        } catch (ex: Exception) {
            Log.e("ErrorCoin", ex.message.toString())
        }
    }


}