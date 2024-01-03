package com.chobo.onrest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.EmotionChoice2HappyBinding

class EmotionChoice2Happy : AppCompatActivity() {
    private lateinit var binding: EmotionChoice2HappyBinding
    private val value = "happylist"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice2HappyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.header.setOnClickListener {
            onBackPressed()
        }

        editor.putString("yourEmotion", "happy")
        editor.apply()

        setChoiceClickListener(binding.choice1)
        setChoiceClickListener(binding.choice2)
        setChoiceClickListener(binding.choice3)
    }

    private fun setChoiceClickListener(view: View) {
        view.setOnClickListener {
            val intent = Intent(this, QuestChoice::class.java)
            intent.putExtra("key", value)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}