package com.chobo.onrest.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.R
import com.chobo.onrest.databinding.CalendarClickBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date

class CalenderClick : AppCompatActivity() {
    private lateinit var binding: CalendarClickBinding
    val currentDate = Date() // 현재 날짜와 시간 가져오기
    val currentDay = SimpleDateFormat("dd").format(currentDate) // 일만 가져오기
    val currentYear = SimpleDateFormat("yyyy").format(currentDate) // 일만 가져오기
    val currentMonth = SimpleDateFormat("MM").format(currentDate) // 일만 가져오기
    var stringValue = "" // 저장할 데이터
    val booleanValue = false
    val editedDate = "${currentYear}년 ${currentMonth}월 ${currentDay}일"
    var retrievedValue = ""
    var todaysEmotion = "" // "key"에 해당하는 데이터를 가져옵니다. 만약 데이터가 없으면 기본값인 "defaultValue"가 반환됩니다.
    private lateinit var receivedList: List<String>
    private lateinit var selectedMission: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarClickBinding.inflate(layoutInflater)
        val view = binding.root
        binding.header1.setOnClickListener {
            super.onBackPressed()
        }
        setView()
        writefileTodo()
        writefile()
        setContentView(view)


        binding.gobackIcon.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun writefileTodo() {
        val filesDir = applicationContext.filesDir
        val fileName = "${currentYear}-${currentMonth}"
        val fileOutputStream: FileOutputStream
        val myFile = File(filesDir, fileName)
        fileOutputStream = if (myFile.exists()) {
            openFileOutput(fileName, Context.MODE_APPEND)
        } else {
            openFileOutput(fileName, Context.MODE_PRIVATE)
        }
        val filcontent = buildString {
            appendln(todaysEmotion)
            appendln(stringValue)
            appendln(booleanValue)
        }
        try {
            fileOutputStream.use {
                it.write(filcontent.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun writefile() {
        val fileName = "${currentYear}-${currentMonth}-${currentDay}"
        val fileContent = buildString {
            appendln(todaysEmotion)
            appendln(editedDate)
            appendln("${currentDay}일")
            appendln(selectedMission)
            receivedList.take(3).forEach { appendln(it) }
            append(retrievedValue)
        }

        try {
            openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(fileContent.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    @SuppressLint("SetTextI18n")
    private fun setView() {
        val sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        retrievedValue = sharedPrefs.getString("memoinput", "defaultValue").toString()
        if (retrievedValue == "") {
            retrievedValue = " "
        }
        receivedList = intent.getSerializableExtra("myList") as? List<String> ?: emptyList()
        selectedMission = intent.getStringExtra("key") ?: ""
        todaysEmotion = sharedPrefs.getString("yourEmotion", "defaultValue").toString()

        when (selectedMission) {
            "1", "2", "3" -> {
                stringValue = receivedList.getOrNull(selectedMission.toInt() - 1) ?: ""
            }
        }

        binding.todaysemotion.setImageResource(
            when (todaysEmotion) {
                "angry" -> R.drawable.angry_face
                "happy" -> R.drawable.happy_face
                "sad" -> R.drawable.sad_face
                else -> {
                    R.drawable.angry_face
                }
            }
        )

        val checkTVList = listOf(binding.checkTV, binding.checkTV1, binding.checkTV2)
        checkTVList.forEachIndexed { index, checkBox ->
            checkBox.isChecked = (index + 1).toString() == selectedMission
            checkBox.isEnabled = false
        }

        val dateTextViews = listOf(binding.dateTV, binding.dateTV1, binding.dateTV2)
        dateTextViews.forEach { it.text = "${currentDay}일" }

        val missionTextViews = listOf(binding.missionTV, binding.missionTV1, binding.missionTV2)
        receivedList.forEachIndexed { index, value ->
            missionTextViews.getOrNull(index)?.text = value
        }

        binding.memoinput.setText(retrievedValue)
        binding.dayText.text = "${currentYear}년 ${currentMonth}월 ${currentDay}일"
    }
}