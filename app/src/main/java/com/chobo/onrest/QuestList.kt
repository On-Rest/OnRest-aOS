package com.chobo.onrest

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.QuestListBinding
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date

class QuestList : AppCompatActivity() {
    private lateinit var binding: QuestListBinding
    val happylist = listOf<String>("기분좋아","신난다","행복해")
    val angrylist = listOf<String>("진짜 화나","개화나","아 화나")
    val sadlist = listOf<String>("아슬퍼","진짜슬퍼","너무 슬퍼")
    val date = Date() // 현재 날짜와 시간 가져오기
    val dayOfMonth = SimpleDateFormat("dd").format(date) // 일만 가져오기
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestListBinding.inflate(layoutInflater)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        val tlqkf = intent.getStringExtra("aiemotion")
        val view = binding.root
        setContentView(view)


        var receivedValue = intent.getStringExtra("key") // 받은 값
            Log.d("das", tlqkf.toString())
            when(intent.getStringExtra("aiemotion")){
                "1" ->{
                    editor.putString("yourEmotion","happy")
                    receivedValue = "happylist"
                }
                "2" -> {
                    editor.putString("yourEmotion","angry")
                    receivedValue = "angrylist"
                }
                "3" -> {
                    editor.putString("yourEmotion","sad")
                    receivedValue = "sadlist"
                }
                else -> {"receivedValue = s"}
            }

        val selectedList = when(receivedValue) {
            "happylist" -> happylist
            "angrylist" -> angrylist
            "sadlist" -> sadlist
            else -> emptyList() // 옵션이 없을 때 기본값 설정
        }

        binding.dateTV.text = "${dayOfMonth}일"
        binding.dateTV1.text = "${dayOfMonth}일"
        binding.dateTV2.text = "${dayOfMonth}일"
        binding.missionTV.text = selectedList[0]
        binding.missionTV1.text = selectedList[1]
        binding.missionTV2.text = selectedList[2]


        binding.checkTV.setOnClickListener(){
            val intent =  Intent(this, CalenderClick::class.java)
            intent.putExtra("myList", selectedList as Serializable)
            intent.putExtra("key", "1") // 데이터 전달
            startActivity(intent)
            overridePendingTransition( R.anim.fade_in, R.anim.fade_out )
            finish()
        }
        binding.checkTV1.setOnClickListener(){
            val intent =  Intent(this, CalenderClick::class.java)
            intent.putExtra("myList", selectedList as Serializable)
            intent.putExtra("key", "2") // 데이터 전달
            startActivity(intent)
            overridePendingTransition( R.anim.fade_in, R.anim.fade_out )
            finish()
        }
        binding.checkTV2.setOnClickListener(){
            val intent =  Intent(this, CalenderClick::class.java)
            intent.putExtra("myList", selectedList as Serializable)
            intent.putExtra("key", "3") // 데이터 전달
            startActivity(intent)
            overridePendingTransition( R.anim.fade_in, R.anim.fade_out )
            finish()
        }
    }
}