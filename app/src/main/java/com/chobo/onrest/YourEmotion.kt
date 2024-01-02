package com.chobo.onrest

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.YourEmotionBinding

class YourEmotion : AppCompatActivity() {
    private lateinit var binding: YourEmotionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = YourEmotionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val emotion = when(intent.getStringExtra("emotion1321")){
            "1" -> com.chobo.onrest.R.drawable.happy_face
            "2" -> com.chobo.onrest.R.drawable.angry_face
            "3" -> com.chobo.onrest.R.drawable.sad_face
            else ->  com.chobo.onrest.R.drawable.grayyouremotion
        }
        val emotionText = when(intent.getStringExtra("emotion1321")){
            "1" -> "행복한 햄스터"
            "2" -> "화난 햄스터"
            "3" -> "슬픈 햄스터"
            else -> "분석 실패!"
        }
        binding.hamsteremotion.text = emotionText
        binding.hamsteremotionsrc.setImageResource(emotion)
        binding.choice1.setOnClickListener() {
            startActivity(Intent(this, CalendarFragment ::class.java))
            overridePendingTransition( R.anim.fade_in, R.anim.fade_out )
            finish()
        }
        binding.choice2.setOnClickListener() {
            startActivity(Intent(this, EmotionChoice1::class.java))
            overridePendingTransition( R.anim.fade_in, R.anim.fade_out )
            finish()
        }
    }
}