package com.example.quizzine

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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
    val BASE_URL = "http://192.168.44.128:2000/"
    private var quizzData = mutableListOf<DataItem>()
     private var  quizzCount = 0

    var correctAnswer = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding=
            setContentView(
                this, R.layout.activity_quizz
            )
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            // vérifier l'identifiant du bouton qui a été cliqué


                checkAnswer(binding.button1.text as String)

        }
        binding.button2.setOnClickListener {
            // vérifier l'identifiant du bouton qui a été cliqué


                checkAnswer(binding.button2.text as String)

        }
        binding.button3.setOnClickListener {
            // vérifier l'identifiant du bouton qui a été cliqué


                checkAnswer(binding.button3.text as String)

        }
        binding.button4.setOnClickListener {
            // vérifier l'identifiant du bouton qui a été cliqué
                checkAnswer(binding.button4.text as String)

        }

        getData()





    }

    fun nextQ(){
        val correct_Answer = quizzData[0].correctAnswer
        correctAnswer = correct_Answer

        binding.question.text = quizzData[0].question
        binding.button1.text = quizzData[0].r1
        binding.button2.text = quizzData[0].r2
        binding.button3.text = quizzData[0].r3
        binding.button4.text = quizzData[0].r4

        quizzData.removeAt(0)



    }




    fun checkAnswer(answer:String){
        val alertTitle:String
        quizzCount++

        if(answer == correctAnswer){
            alertTitle = "Correct !"

        } else {
            alertTitle = "Wrong !"
        }
        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Answer : $correctAnswer")
            .setPositiveButton("Next") { dialog, which ->
                dialog.dismiss()
                checkQuizzCount()
            }.setCancelable(false)
            .show()
    }

    fun checkQuizzCount(){
        if(quizzCount == 2){

            AlertDialog.Builder(this)
                .setTitle("Fin ")
                .setMessage("Bravo vous avez fini le quiz")
                .setPositiveButton("Next") { dialog, which ->
                    dialog.dismiss()
                    endGame()
                }.setCancelable(false)
                .show()

        } else {
            nextQ()
        }

    }

    private fun endGame() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
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
                Log.d("QuizzActivity2", responseBody.toString())


                for (myData in responseBody){
                    listQ+=myData

                }
                quizzData+=listQ

                nextQ()



            }


            override fun onFailure(call: Call<List<DataItem>?>?, t: Throwable?) {
                Log.d("QuizzActivity","Failed request")
                t?.message?.let { Log.d("QuizzActivity", it) }
            }
        })
    }





}