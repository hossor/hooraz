package com.example.hooraz.Recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hooraz.R

class CoinsAdapter(private var CoinList: ArrayList<CoinModel>, var clickListner:OnCoinClickListener) :
    RecyclerView.Adapter<CoinsAdapter.ViewHolder>(){
    class ViewHolder(
        view: View, onCoinClickListener: OnCoinClickListener
    ) : RecyclerView.ViewHolder(view) {
        var IDCoin = view.findViewById(R.id.IdCoin) as TextView
        var NameCoin = view.findViewById(R.id.NameCoin) as TextView
        var Price = view.findViewById(R.id.price) as TextView
        var PriceChange = view.findViewById(R.id.PriceChange) as TextView
        fun initialize(Coin: CoinModel , action:OnCoinClickListener){
            NameCoin.text = Coin.NameCoin
            IDCoin.text = Coin.IDName
            Price.text = Coin.Price
            PriceChange.text = Coin.PriceChange
            itemView.setOnClickListener {
action.onCoinClick(Coin , adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemVeiw = LayoutInflater.from(parent.context)
            .inflate(R.layout.coinsoflist, parent, false)
        return ViewHolder(itemVeiw,clickListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Coin: CoinModel = CoinList[position]
//        holder.NameCoin.text = Coin.NameCoin
//        holder.IDCoin.text = Coin.IDName
//        holder.Price.text = Coin.Price
//        holder.PriceChange.text = Coin.PriceChange
        holder.initialize(CoinList.get(position) , clickListner )

    }

    override fun getItemCount(): Int {
        return CoinList.size

    }



    public interface OnCoinClickListener {
        fun onCoinClick(item: CoinModel, position: Int)

    }
}