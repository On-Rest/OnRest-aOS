package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.CalendarClickBinding

class CalenderClick : AppCompatActivity() {
    private lateinit var binding: CalendarClickBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarClickBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.dateTV.text = "21일"
        binding.dateTV1.text = "21일"
        binding.dateTV2.text = "21일"
        binding.missionTV.text = "명상을 해봐요"
        binding.missionTV1.text = "스트래칭을 해봐요"
        binding.missionTV2.text = "노래를 들어봐요"
    }
}