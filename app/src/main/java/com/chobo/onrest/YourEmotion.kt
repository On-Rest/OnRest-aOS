package com.chobo.onrest

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.YourEmotionBinding

class YourEmotion : AppCompatActivity() {
    private lateinit var binding: YourEmotionBinding
    private lateinit var aiemotion : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = YourEmotionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        aiemotion = intent.getStringExtra("emotion1321").toString()
        val emotion = when(aiemotion){
            "1" -> com.chobo.onrest.R.drawable.happy_face
            "2" -> com.chobo.onrest.R.drawable.angry_face
            "3" -> com.chobo.onrest.R.drawable.sad_face
            else ->  com.chobo.onrest.R.drawable.grayyouremotion
        }
        val emotionText = when(aiemotion){
            "1" -> "행복한 햄스터"
            "2" -> "화난 햄스터"
            "3" -> "슬픈 햄스터"
            else -> "분석 실패!"
        }
        if (emotionText == "분석 실패!"){
            binding.choice1.visibility = INVISIBLE
        }
        binding.hamsteremotion.text = emotionText
        binding.hamsteremotionsrc.setImageResource(emotion)
        binding.choice1.setOnClickListener() {
            val intent =  Intent(this, QuestList::class.java)
            intent.putExtra("aiemotion", aiemotion) // 데이터 전달
            startActivity(intent)
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