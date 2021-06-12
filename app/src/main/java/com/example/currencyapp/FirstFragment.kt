package com.example.currencyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.currencyapp.databinding.FragmentFirstBinding
import com.example.currencyapp.model.CurrencyModemItem

class FirstFragment : Fragment(R.layout.fragment_first) {

    private var param1: String? = null
    private var _binding:FragmentFirstBinding?=null
    private val binding get()=_binding!!
    private var param2: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentFirstBinding.bind(view)


        val currencyModemItem = arguments?.getSerializable("user") as CurrencyModemItem

        binding.tv.text = currencyModemItem.title

        binding.btn.setOnClickListener {
            val toString = binding.edit.text.toString().toDouble()
            var sum = currencyModemItem.cb_price.toDouble() * toString
            binding.sum.text = sum.toString()
        }

        if (currencyModemItem.nbu_buy_price!=""&&currencyModemItem.nbu_cell_price!=""){
            binding.buyPrice.text = currencyModemItem.nbu_buy_price
            binding.cellPrice.text = currencyModemItem.nbu_cell_price
        }else{
            binding.buyPrice.text = "Sotish va olishda hozirda yoq"
            binding.cellPrice.text = ""

        }


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}