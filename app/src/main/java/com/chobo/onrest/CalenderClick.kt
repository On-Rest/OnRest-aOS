package com.chobo.onrest

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.CalendarClickBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date

class CalenderClick : AppCompatActivity() {
    private lateinit var binding: CalendarClickBinding
    val date = Date() // 현재 날짜와 시간 가져오기
    val dayOfMonth = SimpleDateFormat("dd").format(date) // 일만 가져오기
    val year = SimpleDateFormat("yyyy").format(date) // 일만 가져오기
    val month = SimpleDateFormat("MM").format(date) // 일만 가져오기
    var stringValue = "" // 저장할 데이터
    val booleanValue = false
    val editeddate = "${year}년 ${month}월 ${dayOfMonth}일"
    var retrievedValue = ""
    var todaysEmotion = "" // "key"에 해당하는 데이터를 가져옵니다. 만약 데이터가 없으면 기본값인 "defaultValue"가 반환됩니다.
    private lateinit var receivedList: List<String>
    private lateinit var selectedmission: String

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
        val fileName = "${year}-${month}"
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
        val fileName = "${year}-${month}-${dayOfMonth}"
        val fileContent = buildString {
            appendln(todaysEmotion)
            appendln(editeddate)
            appendln("${dayOfMonth}일")
            appendln(selectedmission)
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
        selectedmission = intent.getStringExtra("key") ?: ""
        todaysEmotion = sharedPrefs.getString("yourEmotion", "defaultValue").toString()

        when (selectedmission) {
            "1", "2", "3" -> {
                stringValue = receivedList.getOrNull(selectedmission.toInt() - 1) ?: ""
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
            checkBox.isChecked = (index + 1).toString() == selectedmission
            checkBox.isEnabled = false
        }

        val dateTextViews = listOf(binding.dateTV, binding.dateTV1, binding.dateTV2)
        dateTextViews.forEach { it.text = "${dayOfMonth}일" }

        val missionTextViews = listOf(binding.missionTV, binding.missionTV1, binding.missionTV2)
        receivedList.forEachIndexed { index, value ->
            missionTextViews.getOrNull(index)?.text = value
        }

        binding.memoinput.setText(retrievedValue)
        binding.dayText.text = "${year}년 ${month}월 ${dayOfMonth}일"
    }
}