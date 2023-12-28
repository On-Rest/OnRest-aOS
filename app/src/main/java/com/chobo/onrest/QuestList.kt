package com.chobo.onrest

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.QuestListBinding

class QuestList : AppCompatActivity() {
    private lateinit var binding: QuestListBinding
    var dateTV : String = "21일"
    var dateTV1 : String = "21일"
    var dateTV2 : String = "21일"
    var missionTV : String = "명상을 해봐요"
    var missionTV1 : String = "스트래칭을 해봐요"
    var missionTV2 : String = "노래를 들어봐요"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.dateTV.text = dateTV
        binding.dateTV1.text = dateTV1
        binding.dateTV2.text = dateTV2
        binding.missionTV.text = missionTV
        binding.missionTV1.text = missionTV1
        binding.missionTV2.text = missionTV2

        binding.speechbubble.setOnClickListener() {
            startActivity(Intent(this, NaviActivity::class.java))
            overridePendingTransition( R.anim.fade_in, R.anim.fade_out )
            finish()
        }
    }
}