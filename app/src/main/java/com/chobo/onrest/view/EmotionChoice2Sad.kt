package com.chobo.onrest.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.EmotionChoice2SadBinding

class EmotionChoice2Sad : AppCompatActivity() {
    private lateinit var binding: EmotionChoice2SadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice2SadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.header.setOnClickListener {
            onBackPressed()
        }

        editor.putString("yourEmotion", "sad").apply()

        setChoiceClickListener(binding.choice1)
        setChoiceClickListener(binding.choice2)
        setChoiceClickListener(binding.choice3)
    }

    private fun setChoiceClickListener(view: View) {
        view.setOnClickListener {
            val intent = Intent(this, QuestChoice::class.java).apply{putExtra("key", "sadlist")}
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
