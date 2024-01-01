package com.chobo.onrest

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.CalenderClick1Binding
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Date

class CalenderClick1 : AppCompatActivity() {
    private lateinit var binding: CalenderClick1Binding
    val date = Date() // 현재 날짜와 시간 가져오기
    val year = SimpleDateFormat("yyyy").format(date) // 일만 가져오기
    val month = SimpleDateFormat("MM").format(date) // 일만 가져오
    val angryImageDrawable = R.drawable.angry_face
    val happyImageDrawable = R.drawable.happy_face
    val sadImageDrawable = R.drawable.sad_face
    val fileLines = mutableListOf<String>()
    var fileName = ""


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalenderClick1Binding.inflate(layoutInflater)
        initFileName()
        readFile(this)
        setView()
        val view = binding.root

        setContentView(view)

        binding.gobackIcon.setOnClickListener(){
            super.onBackPressed()
        }
    }
    private fun readFile(context: Context){
        fileLines.clear()

        try {
            val fileInputStream = context.openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                fileLines.add(line.orEmpty()) // 각 줄의 데이터를 리스트에 추가합니다.
            }

            fileInputStream.close()
        } catch (e: FileNotFoundException) {
            Log.e("FileNotFound", "파일이나 디렉토리를 찾을 수 없습니다: $fileName")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun initFileName(){
        val fileDate = intent.getStringExtra("BUTTON_ID")
        fileName = "${year}-${month}-${fileDate}"
    }
    private fun setView(){
        when(fileLines[0]){
            "angry" -> binding.todaysemotion.setImageResource(angryImageDrawable)
            "happy" -> binding.todaysemotion.setImageResource(happyImageDrawable)
            "sad" -> binding.todaysemotion.setImageResource(sadImageDrawable)
        }
        // TODO:  통신하면 일기데이터 여기로 넣어주셈
        //binding.memoinput.setText()
        when(fileLines[3]){
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

        binding.checkTV.isEnabled = false
        binding.checkTV1.isEnabled = false
        binding.checkTV2.isEnabled = false

        binding.dateTV.text = fileLines[2]
        binding.dateTV1.text = fileLines[2]
        binding.dateTV2.text = fileLines[2]

        binding.missionTV.text = fileLines[4]
        binding.missionTV1.text = fileLines[5]
        binding.missionTV2.text = fileLines[6]

        binding.dayText.text = fileLines[1]
    }
}