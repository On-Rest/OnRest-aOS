package com.chobo.onrest

import android.R
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View.INVISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.YourEmotionBinding

class YourEmotion : AppCompatActivity() {
    private lateinit var binding: YourEmotionBinding
    private lateinit var aiemotion : String
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var emotion: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = YourEmotionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.header.setOnClickListener(){
            super.onBackPressed()
        }
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        emotion = sharedPreferences.getString("yourEmotion", "defaultValue").toString()
        aiemotion = intent.getStringExtra("emotion1321").toString()
        val emotionsrc = when(aiemotion){
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
        emotion = when(aiemotion){
            "1" -> "happy"
            "3" -> "angry"
            "2" -> "sad"
            else -> {"sad"}
        }
        aiemotion = when(aiemotion){
            "1" -> "happylist"
            "2" -> "angrylist"
            "3" -> "sadlist"
            else -> {"sad"}
        }
        editor.putString("yourEmotion", emotion)
        editor.apply()
        if (emotionText == "분석 실패!"){
            binding.choice1.visibility = INVISIBLE
        }
        binding.hamsteremotion.text = emotionText
        binding.hamsteremotionsrc.setImageResource(emotionsrc)
        binding.choice1.setOnClickListener() {
            val intent =  Intent(this, QuestList::class.java)
            intent.putExtra("key", aiemotion) // 데이터 전달
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