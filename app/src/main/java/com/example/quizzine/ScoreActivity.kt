package com.example.quizzine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizzine.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {

    private lateinit var binding:ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_score)

        binding = ActivityScoreBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val scoreFinal = intent.getIntExtra("SCORE",0)
        binding.textScoreLabel.text="Score :"
        binding.textScore.text= scoreFinal.toString()

        binding.button.setOnClickListener {
            endGame()
        }
    }

    private fun endGame() {

        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
        finish()
    }
}