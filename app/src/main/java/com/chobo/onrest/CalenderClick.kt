package com.chobo.onrest

import android.annotation.SuppressLint
import android.content.Context
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
        val sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val retrievedValue = sharedPrefs.getString("memoinput", "defaultValue") // "key"에 해당하는 데이터를 가져옵니다. 만약 데이터가 없으면 기본값인 "defaultValue"가 반환됩니다.
        val selectedmission = intent.getStringExtra("key") // 받은 값
        val receivedList = intent.getSerializableExtra("myList") as? List<String>

        when(selectedmission){
            "1" ->  binding.checkTV.isChecked = true
            "2" ->  binding.checkTV.isChecked = true
            "3" ->  binding.checkTV.isChecked = true
        }

        binding.memoinput.setText(retrievedValue)

        binding.checkTV.isEnabled = false
        binding.checkTV1.isEnabled = false
        binding.checkTV2.isEnabled = false

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