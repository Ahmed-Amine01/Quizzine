package com.example.quizzine

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.TextView
import com.example.quizzine.databinding.ActivityMainBinding

class Quizz : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



        lancerQuizz("QUESTION BLALALALAL")
    }


    fun lancerQuizz(question: String) {
       binding.question_id.text = question;






    }


}