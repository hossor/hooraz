package com.example.hooraz.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.hooraz.Model.CoinModel
import com.example.hooraz.R
import com.example.hooraz.Views.CoinView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.activity_coin.*
import org.json.JSONArray

class CoinInfo : AppCompatActivity(), CoinView {


    lateinit var coinModel: CoinModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)
        coinModel = CoinModel(CoinModel@ this)
        var extraCoin = intent.extras
        if (extraCoin != null) {
            var name =extraCoin.getString("coinName").toString()
            coinModel.CoinChanges(name , this)
            GlideToVectorYou.init()
                .with(CoinInfo@this)
                .load(Uri.parse("https://s3.us-east-2.amazonaws.com/nomics-api/static/images/currencies/btc.svg") ,CoinIMG)

        }
    }


    override fun CoinChangesSuccesfully(jsonCoin: JSONArray) {
        try {
            Log.d("CoinInfo", "Load ${jsonCoin} successfully")


        }catch (ex:Exception){
            Log.e("ErrorCoin" , ex.message.toString())
        }
    }



}