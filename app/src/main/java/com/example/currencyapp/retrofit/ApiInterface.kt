package com.example.currencyapp.retrofit

import com.example.currencyapp.model.CurrencyModemItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("json")
    fun getData():Call<List<CurrencyModemItem>>
}