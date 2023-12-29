package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.EmotionChoice2SadBinding
class EmotionChoice2Sad : AppCompatActivity() {
    private lateinit var binding: EmotionChoice2SadBinding
    val value = "sadlist"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice2SadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.choice1.setOnClickListener() {
            val intent =  Intent(this, QuestChoice::class.java)
            intent.putExtra("key", value) // 데이터 전달
            startActivity(intent)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.choice2.setOnClickListener() {
            val intent =  Intent(this, QuestChoice::class.java)
            intent.putExtra("key", value) // 데이터 전달
            startActivity(intent)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.choice3.setOnClickListener() {
            val intent =  Intent(this, QuestChoice::class.java)
            intent.putExtra("key", value) // 데이터 전달
            startActivity(intent)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}