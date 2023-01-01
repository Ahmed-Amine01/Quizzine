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
import android.net.Uri

import com.example.quizzine.databinding.ActivityQuizzBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Quizz : AppCompatActivity() {
    private lateinit var binding:ActivityQuizzBinding
    val BASE_URL = "http://192.168.44.128:2000/"
    private var quizzData = mutableListOf<DataItem>()
    private var  quizzCount = 0
    var correctCount = 0
    var correctAnswer = ""
    var route =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding=
            setContentView(
                this, R.layout.activity_quizz
            )
        setContentView(binding.root)
        val intent = intent
        if (intent.hasExtra("route")) {
            val value = intent.extras?.get("route") // Utiliser la valeur
            route = value as String


        }

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

    fun shareViaGmail(text: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_SUBJECT, "Rejoint moi sur Quizz In")
            putExtra(Intent.EXTRA_TEXT, text)
        }
        startActivity(Intent.createChooser(emailIntent, null))
    }


    fun checkAnswer(answer:String){
        val alertTitle:String
        quizzCount++

        if(answer == correctAnswer){
            correctCount++
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
                .setMessage("Bravo vous avez fini le quiz! Souhaitez vous partager par mail votre score a un amis ?")
               .setPositiveButton("Oui") { dialog, id ->
                    shareViaGmail("Hey J'ai fait un score de $correctCount !!!")
                   finish()

            }
                .setNegativeButton("Non et Passer") { dialog, id ->
                    dialog.dismiss()
                    endGame()
                }
                .setCancelable(false)
                .show()

        } else {
            nextQ()
        }

    }

    private fun endGame() {
       // shareViaGmail("J'ai fais un SUPER SCORE de $correctCount. Vient essayer de battre mon score")

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getData() {
        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(ApiInterface::class.java)
        var data = retrofitBuilder.getDataFemme()
        
        if(route=="cuisine"){
             data = retrofitBuilder.getDataCuisine()
        }

        data.enqueue(object : Callback<List<DataItem>?> {
            override fun onResponse(
                call: Call<List<DataItem>?>?,
                response: Response<List<DataItem>?>?
            ) {
                val responseBody = response?.body()!!
                val listQ=mutableListOf<DataItem>()
                for (myData in responseBody) {
                    listQ += myData
                }
                quizzData += listQ
                nextQ()
            }


            override fun onFailure(call: Call<List<DataItem>?>?, t: Throwable?) {
                Log.d("QuizzActivity","Failed request")
                t?.message?.let { Log.d("QuizzActivity", it) }
            }
        })
    }





}