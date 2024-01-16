package com.chobo.onrest.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.EmotionChoice1Binding

class EmotionChoice1 : AppCompatActivity() {
    private lateinit var binding: EmotionChoice1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.setOnClickListener {
            onBackPressed()
        }

        setEmotionClickListener(binding.happyface, EmotionChoice2Happy::class.java)
        setEmotionClickListener(binding.angryface, EmotionChoice2Angry::class.java)
        setEmotionClickListener(binding.sadface, EmotionChoice2Sad::class.java)
    }

    private fun setEmotionClickListener(view: View, targetClass: Class<*>) {
        view.setOnClickListener {
            val intent = Intent(this, targetClass)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
