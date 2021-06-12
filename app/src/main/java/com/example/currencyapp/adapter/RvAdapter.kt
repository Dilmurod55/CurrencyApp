package com.example.currencyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapp.databinding.ItemBinding
import com.example.currencyapp.model.CurrencyModemItem

class RvAdapter(var list :List<CurrencyModemItem>,var myitemclicklistener:OnmyInterface):RecyclerView.Adapter<RvAdapter.myholder>() {
    inner class myholder(var binding: ItemBinding):RecyclerView.ViewHolder(binding.root){
        fun obBind(modemItem: CurrencyModemItem,position: Int){
            binding.titlecurrency.text = modemItem.title
            binding.cbPrice.text = modemItem.cb_price
            binding.code2.text = modemItem.code
            binding.date.text = modemItem.date
            binding.it.setOnClickListener {
                myitemclicklistener.onmyitemclick(modemItem,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myholder {
        return myholder(ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: myholder, position: Int) {
        val currencyModemItem = list[position]
        holder.obBind(currencyModemItem,position)
    }

    override fun getItemCount(): Int = list.size

    interface OnmyInterface{
        fun onmyitemclick(modemItem: CurrencyModemItem,position: Int)
    }
}