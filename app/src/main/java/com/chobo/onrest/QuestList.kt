package com.chobo.onrest

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.QuestListBinding
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date

class QuestList : AppCompatActivity() {
    lateinit var selectedList : List<String>
    lateinit var receivedValue : String
    private lateinit var binding: QuestListBinding
    val happylist = listOf<String>("다섯 번 크게 웃기","행복했던 순간 적어보기","1시간 산책하기")
    val angrylist = listOf<String>("크게 숨 3번 쉬어보기","차가운 물에 씻어보기","즐거웠던 순간 떠올리기")
    val sadlist = listOf<String>("행복했던 순간 적어보기","1시간 산책하기","명상을 통해 현재 순간에 집중하기")
    val date = Date() // 현재 날짜와 시간 가져오기
    val dayOfMonth = SimpleDateFormat("dd").format(date) // 일만 가져오기

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.header.setOnClickListener{
            super.onBackPressed()
        }

        receivedValue = intent.getStringExtra("key").toString() // 받은 값

        selectedList = when(receivedValue) {
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


        binding.checkTV.setOnClickListener{
            onClickListener(1)
        }
        binding.checkTV1.setOnClickListener{
            onClickListener(2)
        }
        binding.checkTV2.setOnClickListener{
            onClickListener(3)
        }
    }
    fun onClickListener(Data:Int){
        val intent =  Intent(this, CalenderClick::class.java)
        intent.putExtra("myList", selectedList as Serializable)
        intent.putExtra("key", Data) // 데이터 전달
        startActivity(intent)
        overridePendingTransition( R.anim.fade_in, R.anim.fade_out )
        finish()
    }
}