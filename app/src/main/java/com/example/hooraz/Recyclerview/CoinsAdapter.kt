package com.example.hooraz.Recyclerview

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hooraz.R
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


class CoinsAdapter(
    private var CoinList: ArrayList<CoinModel>,
    var clickListner: OnCoinClickListener,
    var ctx:Context
    ) :
    RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {
    class ViewHolder(view: View, onCoinClickListener: OnCoinClickListener) : RecyclerView.ViewHolder(view) {
        var IDCoin = view.findViewById(R.id.IdCoin) as TextView
        var NameCoin = view.findViewById(R.id.NameCoin) as TextView
        var Price = view.findViewById(R.id.price) as TextView
        var PriceChange = view.findViewById(R.id.PriceChange) as TextView
        var coinView = view.findViewById(R.id.coinView) as ImageView
        fun initialize(Coin: CoinModel, action: OnCoinClickListener ,ctx :Context) {
            NameCoin.text = Coin.NameCoin
            IDCoin.text = Coin.IDName
            Price.text = Coin.Price
            PriceChange.text = Coin.PriceChange

            if (Coin.logo_url.contains("svg")) {
                GlideToVectorYou.init()
                    .with(ctx)
                    .load(Uri.parse(Coin.logo_url), coinView)


            } else {
                Glide.with(ctx).load(Coin.logo_url).into(coinView)
            }
            itemView.setOnClickListener {
                action.onCoinClick(Coin, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemVeiw = LayoutInflater.from(parent.context)
            .inflate(R.layout.coinsoflist, parent, false)
        return ViewHolder(itemVeiw, clickListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Coin: CoinModel = CoinList[position]
//        holder.NameCoin.text = Coin.NameCoin
//        holder.IDCoin.text = Coin.IDName
//        holder.Price.text = Coin.Price
//        holder.PriceChange.text = Coin.PriceChange
        holder.initialize(CoinList.get(position), clickListner , ctx)
        setFadeAnimation(holder.itemView);


    }
    private val FADE_DURATION = 1000
    private fun setFadeAnimation(view: View) {


        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = FADE_DURATION.toLong()
        view.startAnimation(anim)
    }
    override fun getItemCount(): Int {
        return CoinList.size

    }


    public interface OnCoinClickListener {
        fun onCoinClick(item: CoinModel, position: Int)

    }
}