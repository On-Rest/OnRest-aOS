package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.EmotionChoice1Binding

class EmotionChoice1 : AppCompatActivity() {
    private lateinit var binding: EmotionChoice1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.happyface.setOnClickListener() {
            startActivity(Intent(this, EmotionChoice2Happy::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.angryface.setOnClickListener() {
            startActivity(Intent(this, EmotionChoice2Angry::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.sadface.setOnClickListener() {
            startActivity(Intent(this, EmotionChoice2Sad::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}