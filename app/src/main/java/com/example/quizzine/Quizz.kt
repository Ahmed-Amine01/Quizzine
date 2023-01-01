package com.example.quizzine

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView

import com.example.quizzine.databinding.ActivityQuizzBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Quizz : AppCompatActivity() {
    private lateinit var binding:ActivityQuizzBinding
    //private lateinit var bindingMain:ActivityMainBinding
    val BASE_URL = "https://jsonplaceholder.typicode.com/posts/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding=
            setContentView(
                this, R.layout.activity_quizz
            )

        setContentView(binding.root)
        lancerQuizz("QUESTION BLALALALAL")
        getData()
    }

    private fun getData() {
        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(ApiInterface::class.java)
        val data = retrofitBuilder.getData()
        data.enqueue(object : Callback<List<DataItem>?> {
            override fun onResponse(
                call: Call<List<DataItem>?>?,
                response: Response<List<DataItem>?>?
            ) {
                val responseBody = response?.body()!!
                val myStringBuilder = StringBuilder()

                for (myData in responseBody){
                    myStringBuilder.append(myData.title)
                    break
                }
                binding.question.text = myStringBuilder



            }

            override fun onFailure(call: Call<List<DataItem>?>?, t: Throwable?) {
                Log.d("QuizzActivity","Failed request")
            }
        })
    }


    fun lancerQuizz(question: String) {

        binding.question.text = question;
        binding.button1.text = "rep1";
        binding.button2.text = "rep2";
        binding.button3.text = "rep3";
        binding.button4.text = "rep4";

    }


}