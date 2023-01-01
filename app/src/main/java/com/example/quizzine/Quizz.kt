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
    private var quizzData = mutableListOf<DataItem>()
     val int quizzCount = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding=
            setContentView(
                this, R.layout.activity_quizz
            )
        setContentView(binding.root)
        getData()
    }

    fun nextQ(){
        val correctAnswer = quizzData[0].correctAnswer

        binding.question.text = quizzData[0].question
        binding.button1.text = quizzData[0].r1
        binding.button2.text = quizzData[0].r2
        binding.button3.text = quizzData[0].r3
        binding.button4.text = quizzData[0].r4

        quizzData.removeAt(0)


    }

    fun checkAnswer(){

    }

    fun checkQuizzCount(){

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
                val listQ=mutableListOf<DataItem>()

                for (myData in responseBody){
                    listQ+myData

                }
                quizzData = listQ

            }

            override fun onFailure(call: Call<List<DataItem>?>?, t: Throwable?) {
                Log.d("QuizzActivity","Failed request")
            }
        })
    }





}