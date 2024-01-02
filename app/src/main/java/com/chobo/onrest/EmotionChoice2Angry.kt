package com.chobo.onrest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.EmotionChoice2AngryBinding

class EmotionChoice2Angry : AppCompatActivity() {
    private lateinit var binding: EmotionChoice2AngryBinding
    private val value = "angrylist"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice2AngryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.header.setOnClickListener {
            super.onBackPressed()
        }

        editor.putString("yourEmotion", "angry")
        editor.apply()

        setChoiceClickListener(binding.choice1)
        setChoiceClickListener(binding.choice2)
        setChoiceClickListener(binding.choice3)
    }

    private fun setChoiceClickListener(view: View) {
        view.setOnClickListener {
            val intent = Intent(this, QuestChoice::class.java)
            intent.putExtra("key", value) // 데이터 전달
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
