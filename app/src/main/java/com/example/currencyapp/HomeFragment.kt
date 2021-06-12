package com.example.currencyapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.currencyapp.retrofit.ApiInterface
import com.example.currencyapp.adapter.RvAdapter
import com.example.currencyapp.databinding.FragmentHomeBinding
import com.example.currencyapp.model.CurrencyModemItem
import com.example.currencyapp.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var adapter: RvAdapter
    private lateinit var list: ArrayList<CurrencyModemItem>
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)
        LoadData()
    }

    fun LoadData() {
        val retrofitData = ApiClient.getRetrofit().getData()
        list = ArrayList()
        retrofitData.enqueue(object : Callback<List<CurrencyModemItem>> {
            override fun onResponse(
                call: Call<List<CurrencyModemItem>>,
                response: Response<List<CurrencyModemItem>>
            ) {
                var responseBody = response.body()!!
                for (modemItem in responseBody) {
                    list.add(modemItem)
                }
                adapter = RvAdapter(list, object : RvAdapter.OnmyInterface {
                    override fun onmyitemclick(modemItem: CurrencyModemItem, position: Int) {
                        var bundle = bundleOf("user" to modemItem)
                        Navigation.findNavController(binding.root)
                            .navigate(R.id.firstFragment, bundle)
                    }

                })
                binding.rv.adapter = adapter

            }

            override fun onFailure(call: Call<List<CurrencyModemItem>>, t: Throwable) {
                Log.d("HomeFragment", "onFailure: " + t.message)
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}