package com.chobo.onrest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.CalendarClickBinding
import java.text.SimpleDateFormat
import java.util.Date

class CalenderClick : AppCompatActivity() {
    private lateinit var binding: CalendarClickBinding
    val date = Date() // 현재 날짜와 시간 가져오기
    val dayOfMonth = SimpleDateFormat("dd").format(date) // 일만 가져오기
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarClickBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val selectedmission = intent.getStringExtra("key") // 받은 값
        val receivedList = intent.getSerializableExtra("myList") as? List<String>

        when(selectedmission){
            "1" ->  binding.checkTV.isChecked = true
            "2" ->  binding.checkTV.isChecked = true
            "3" ->  binding.checkTV.isChecked = true
        }
        binding.gobackIcon.setOnClickListener(){
            super.onBackPressed()
        }
        binding.dateTV.text = "${dayOfMonth}일"
        binding.dateTV1.text = "${dayOfMonth}일"
        binding.dateTV2.text = "${dayOfMonth}일"
        binding.missionTV.text = receivedList!![0]
        binding.missionTV1.text = receivedList[1]
        binding.missionTV2.text = receivedList[2]

    }
}