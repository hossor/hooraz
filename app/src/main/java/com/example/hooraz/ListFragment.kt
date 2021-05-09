package com.example.hooraz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hooraz.Model.ListCoinModel
import com.example.hooraz.Views.ListCoinView

class ListFragment : Fragment(), ListCoinView {
    lateinit var ListCoinModel: ListCoinModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_list, container, false)
        //adapte the recyclerView
        /* view.reclerviewForCoins.apply {
             layoutManager = LinearLayoutManager(activity)
             itemAnimator = DefaultItemAnimator()
             adapter =
         }*/



        return view
    }

    override fun adapterListCoin() {
        Log.d("adapterListCoin", "adapterListCoin")
    }


}