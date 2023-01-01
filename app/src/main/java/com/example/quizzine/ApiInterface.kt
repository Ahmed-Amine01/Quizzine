package com.example.quizzine

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    // endpoint a mettre
    @GET("cuisine")
    fun getDataCuisine():Call<List<DataItem>>

    @GET("femme")
    fun getDataFemme():Call<List<DataItem>>
}