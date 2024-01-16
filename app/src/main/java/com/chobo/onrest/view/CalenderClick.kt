package com.chobo.onrest.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chobo.onrest.R
import com.chobo.onrest.databinding.CalendarClickBinding
import com.chobo.onrest.viewmodel.CalendarViewModel

class CalendarClick : AppCompatActivity() {
    private lateinit var binding: CalendarClickBinding
    private val viewModel: CalendarViewModel by lazy {
        ViewModelProvider(this).get(CalendarViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarClickBinding.inflate(layoutInflater)
        val view = binding.root
        binding.header1.setOnClickListener {
            super.onBackPressed()
        }
        setView()
        setContentView(view)

        binding.gobackIcon.setOnClickListener {
            super.onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setView() {
        viewModel.calendarData.observe(this, Observer { calendarData ->
            val sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val retrievedValue =
                sharedPrefs.getString("memoinput", "defaultValue").toString()
            val selectedMission = intent.getStringExtra("key") ?: ""
            val todaysEmotion =
                sharedPrefs.getString("yourEmotion", "defaultValue").toString()


            binding.todaysemotion.setImageResource(
                when (todaysEmotion) {
                    "angry" -> R.drawable.angry_face
                    "happy" -> R.drawable.happy_face
                    "sad" -> R.drawable.sad_face
                    else -> R.drawable.angry_face
                }
            )

            val checkTVList = listOf(binding.checkTV, binding.checkTV1, binding.checkTV2)
            checkTVList.forEachIndexed { index, checkBox ->
                checkBox.isChecked = (index + 1).toString() == selectedMission
                checkBox.isEnabled = false
            }

            val dateTextViews = listOf(binding.dateTV, binding.dateTV1, binding.dateTV2)
            dateTextViews.forEach { it.text = "${calendarData.currentDay}일" }

            val missionTextViews =
                listOf(binding.missionTV, binding.missionTV1, binding.missionTV2)
            calendarData.receivedList.forEachIndexed { index, value ->
                missionTextViews.getOrNull(index)?.text = value
            }

            binding.memoinput.setText(retrievedValue)
            binding.dayText.text =
                "${calendarData.currentYear}년 ${calendarData.currentMonth}월 ${calendarData.currentDay}일"
        })
    }
}
