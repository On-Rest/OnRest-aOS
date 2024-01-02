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
    val angryImageDrawable = R.drawable.angry_face
    val happyImageDrawable = R.drawable.happy_face
    val sadImageDrawable = R.drawable.sad_face
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

        setView()
        writefileTodo()
        writefile()
        setContentView(view)


        binding.gobackIcon.setOnClickListener(){
            super.onBackPressed()
        }
    }

    private fun writefileTodo() {
        val filesDir = applicationContext.filesDir
        val fileName = "${year}-${month}"
        val fileOutputStream: FileOutputStream
        val myFile = File(filesDir, fileName)

        try {
            if (myFile.exists())
            {fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND) }
            else
            {fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE) }

            fileOutputStream.use {
                it.write(dayOfMonth.toByteArray())
                it.write("\n".toByteArray())
                it.write(stringValue.toByteArray())
                it.write("\n".toByteArray())
                it.write(booleanValue.toString().toByteArray())
                it.write("\n".toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun writefile() {
        val fileName = "${year}-${month}-${dayOfMonth}"
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)

            fileOutputStream.use {
                it.write(todaysEmotion.toByteArray())
                it.write("\n".toByteArray())
                it.write(editeddate.toByteArray())
                it.write("\n".toByteArray())
                it.write("${dayOfMonth}일".toByteArray())
                it.write("\n".toByteArray())
                it.write(selectedmission.toByteArray())
                it.write("\n".toByteArray())
                it.write(receivedList[0].toByteArray())
                it.write("\n".toByteArray())
                it.write(receivedList[1].toByteArray())
                it.write("\n".toByteArray())
                it.write(receivedList[2].toByteArray())
                it.write("\n".toByteArray())
                it.write(retrievedValue.toByteArray())
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
     @SuppressLint("SetTextI18n")
     private fun setView(){
        val sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
         retrievedValue = sharedPrefs.getString("memoinput", "defaultValue").toString()
         if (retrievedValue == ""){
             retrievedValue = " "
         }
         receivedList = intent.getSerializableExtra("myList") as? List<String> ?: emptyList()
         selectedmission = intent.getStringExtra("key") ?: ""
        todaysEmotion = sharedPrefs.getString("yourEmotion", "defaultValue").toString()
        when(selectedmission){
            "1" ->  {
                stringValue = receivedList[0]
            }
            "2" ->  {
                stringValue = receivedList[1]
            }
            "3" ->  {
                stringValue = receivedList[2]
            }
        }
         Log.d("tlqkf",todaysEmotion)

         when(todaysEmotion){
            "angry" -> binding.todaysemotion.setImageResource(angryImageDrawable)
            "happy" -> binding.todaysemotion.setImageResource(happyImageDrawable)
            "sad" -> binding.todaysemotion.setImageResource(sadImageDrawable)
        }

        when(selectedmission){
            "1" ->  {
                binding.checkTV.isChecked = true
            }
            "2" ->  {
                binding.checkTV1.isChecked = true
            }
            "3" ->  {
                binding.checkTV2.isChecked = true
            }
        }

        binding.memoinput.setText(retrievedValue)

        binding.checkTV.isEnabled = false
        binding.checkTV1.isEnabled = false
        binding.checkTV2.isEnabled = false

        binding.dateTV.text = "${dayOfMonth}일"
        binding.dateTV1.text = "${dayOfMonth}일"
        binding.dateTV2.text = "${dayOfMonth}일"

        binding.missionTV.text = receivedList[0]
        binding.missionTV1.text = receivedList[1]
        binding.missionTV2.text = receivedList[2]

        binding.dayText.text = editeddate
    }
}