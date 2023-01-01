package com.example.quizzine

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    // endpoint a mettre
    @GET("getData")
    fun getData():Call<List<DataItem>>
}