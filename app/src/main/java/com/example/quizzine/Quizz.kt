package com.example.quizzine

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView

import com.example.quizzine.databinding.ActivityQuizzBinding

class Quizz : AppCompatActivity() {
    private lateinit var binding:ActivityQuizzBinding
    //private lateinit var bindingMain:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityQuizzBinding =
            setContentView(
                this, R.layout.activity_quizz
            )

        setContentView(binding.root)
        lancerQuizz("QUESTION BLALALALAL")
    }


    fun lancerQuizz(question: String) {


    }


}