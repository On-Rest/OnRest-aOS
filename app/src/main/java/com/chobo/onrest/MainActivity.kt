package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.ActivityMainBinding
import com.chobo.onrest.databinding.CalendarBinding
import com.chobo.onrest.databinding.DiaryWriteBinding
import com.chobo.onrest.databinding.EmotionChoice1Binding
import com.chobo.onrest.databinding.EmotionChoice2AngryBinding
import com.chobo.onrest.databinding.EmotionChoice2HappyBinding
import com.chobo.onrest.databinding.HeaderBinding
import com.chobo.onrest.databinding.PostWriteBinding
import com.chobo.onrest.databinding.QuestChoiceBinding
import com.chobo.onrest.databinding.QuestHistoryBinding
import com.chobo.onrest.databinding.QuestListBinding
import com.chobo.onrest.databinding.YourEmotionBinding

// MainActivity.java
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.login.setOnClickListener() {
            startActivity(Intent(this, NaviActivity::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}










