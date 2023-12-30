package com.chobo.onrest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.CalendarClickBinding
import java.text.SimpleDateFormat
import java.util.Date

class CalenderClick : AppCompatActivity() {
    private lateinit var binding: CalendarClickBinding
    val date = Date() // 현재 날짜와 시간 가져오기
    val dayOfMonth = SimpleDateFormat("dd").format(date) // 일만 가져오기
    val fileName = "missionData.txt" // 파일 이름r
    var stringValue = "" // 저장할 데이터
    val booleanValue = false
    val angryImageDrawable = R.drawable.angry_face
    val happyImageDrawable = R.drawable.happy_face
    val sadImageDrawable = R.drawable.sad_face

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarClickBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val retrievedValue = sharedPrefs.getString("memoinput", "defaultValue") // "key"에 해당하는 데이터를 가져옵니다. 만약 데이터가 없으면 기본값인 "defaultValue"가 반환됩니다.
        val todaysEmotion = sharedPrefs.getString("yourEmotion", "defaultValue") // "key"에 해당하는 데이터를 가져옵니다. 만약 데이터가 없으면 기본값인 "defaultValue"가 반환됩니다.
        val selectedmission = intent.getStringExtra("key") // 받은 값
        val receivedList = intent.getSerializableExtra("myList") as? List<String>

        when(todaysEmotion){
            "angry" -> binding.todaysemotion.setImageResource(angryImageDrawable)
            "happy" -> binding.todaysemotion.setImageResource(happyImageDrawable)
            "sad" -> binding.todaysemotion.setImageResource(sadImageDrawable)
            else ->  {
                val intent =  Intent(this, EmotionChoice1::class.java)
                startActivity(intent)
                overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
                finish()
            }
        }

        try {
            val fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)

            // boolean 값 파일에 쓰기
            fileOutputStream.write(booleanValue.toString().toByteArray())
            // 줄 바꿈을 추가하여 String 값 파일에 쓰기
            fileOutputStream.write("\n".toByteArray())
            fileOutputStream.write(stringValue.toByteArray())
            fileOutputStream.write("\n".toByteArray())
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        when(selectedmission){
            "1" ->  {
                stringValue = receivedList!![0]
                binding.checkTV.isChecked = true
            }
            "2" ->  {
                stringValue = receivedList!![1]
                binding.checkTV.isChecked = true
            }
            "3" ->  {
                stringValue = receivedList!![2]
                binding.checkTV.isChecked = true
            }
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