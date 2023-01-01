package com.example.quizzine

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.textview)
        val imageView = findViewById<ImageView>(R.id.img_logo_quizzine)
        textView.text = "Bienvenue sur notre jeu de quizz :"
        imageView.setImageResource(R.drawable.logo_quizzin)

    }
    fun startQuizz(view: View) {
        val intent = Intent(this, Quizz::class.java)
        startActivity(intent)
        finish()
    }


}