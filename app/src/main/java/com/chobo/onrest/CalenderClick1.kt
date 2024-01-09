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
        readFile(this)
        setView()
        val view = binding.root
        binding.header1.setOnClickListener{
            super.onBackPressed()
        }
        setContentView(view)

        binding.gobackIcon.setOnClickListener{
            super.onBackPressed()
        }
    }
    private fun readFile(context: Context){
        val fileDate = intent.getStringExtra("BUTTON_ID")
        fileName = "${year}-${month}-${fileDate}"
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
    private fun setView() {
        val emotionsMap = mapOf(
            "angry" to angryImageDrawable,
            "happy" to happyImageDrawable,
            "sad" to sadImageDrawable
        )
        val checkBoxMap = mapOf(
            "1" to binding.checkTV,
            "2" to binding.checkTV1,
            "3" to binding.checkTV2
        )
        val fileLinesMap = mapOf(
            "emotion" to 0,
            "date" to 1,
            "selectedMission" to 3,
            "mission1" to 4,
            "mission2" to 5,
            "mission3" to 6,
            "memo" to 7
        )

        fileLinesMap.forEach { (key, index) ->
            when (key) {
                "emotion" -> fileLines.getOrNull(index)?.let { emotion ->
                    emotionsMap[emotion]?.let { drawable -> binding.todaysemotion.setImageResource(drawable) }
                }
                "selectedMission" -> fileLines.getOrNull(index)?.let { selected ->
                    checkBoxMap[selected]?.isChecked = true
                }
                "memo" -> fileLines.getOrNull(index)?.let { binding.memoinput.setText(it) }
                else -> {
                    listOf(binding.dateTV, binding.dateTV1, binding.dateTV2, binding.missionTV, binding.missionTV1, binding.missionTV2)
                        .getOrNull(index)?.text = fileLines.getOrNull(index)
                }
            }
        }

        listOf(binding.checkTV, binding.checkTV1, binding.checkTV2).forEach { it.isEnabled = false }
    }

}