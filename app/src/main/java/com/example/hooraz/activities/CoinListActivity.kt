package com.example.hooraz.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.hooraz.Fragments.Coinlist
import com.example.hooraz.Model.ListCoinModel
import com.example.hooraz.R
import com.example.hooraz.Fragments.news
import com.example.hooraz.ProfileFragment
import kotlinx.android.synthetic.main.activity_crypto.*
import kotlinx.android.synthetic.main.coinsoflist.*
import java.util.*

class cryptoActivity : AppCompatActivity(){
    lateinit var ListCoinModel: ListCoinModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto)
        ListCoinModel = ListCoinModel(cryptoActivity@ this)
        ListCoinModel.Coinfun()
        getSupportActionBar()!!.hide();

        //fragments
        var fm :FragmentManager=supportFragmentManager
        var ft = fm.beginTransaction()
        var fragCoin = Coinlist(this@cryptoActivity)
        //set coin list
        ft.replace(R.id.frag ,fragCoin )
        ft.commit()
        bottomBar.onItemSelected = {
            var postion = it.toString()
            Log.d("Postionbar" , it.toString())
            ft = fm.beginTransaction()

            //check position navigation
            if (postion == "0")
            {

                ft.replace(R.id.frag ,fragCoin )
                ft.commit()
            }
            else if(postion == "1")
            {
                var news=news()
                ft.replace(R.id.frag , news)
                ft.commit()

            }
            else if(postion == "2")
            {
                var profileFragment = ProfileFragment()
                ft.replace(R.id.frag , profileFragment)
                ft.commit()
            }
        }

    }





}