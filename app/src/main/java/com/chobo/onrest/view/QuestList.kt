package com.chobo.onrest.view

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.QuestListBinding
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date

class QuestList : AppCompatActivity() {
    private lateinit var selectedList: List<String>
    private lateinit var receivedValue: String
    private lateinit var binding: QuestListBinding

    private val happylist = listOf("다섯 번 크게 웃기", "행복했던 순간 적어보기", "1시간 산책하기")
    private val angrylist = listOf("크게 숨 3번 쉬어보기", "차가운 물에 씻어보기", "즐거웠던 순간 떠올리기")
    private val sadlist = listOf("행복했던 순간 적어보기", "1시간 산책하기", "명상을 통해 현재 순간에 집중하기")

    @SuppressLint("SimpleDateFormat")
    private val date = SimpleDateFormat("dd").format(Date())

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.setOnClickListener {
            super.onBackPressed()
        }

        receivedValue = intent.getStringExtra("key").toString()
        selectedList = when (receivedValue) {
            "happylist" -> happylist
            "angrylist" -> angrylist
            "sadlist" -> sadlist
            else -> emptyList()
        }

        binding.apply {
            dateTV.text = "${date}일"
            dateTV1.text = "${date}일"
            dateTV2.text = "${date}일"
            missionTV.text = selectedList[0]
            missionTV1.text = selectedList[1]
            missionTV2.text = selectedList[2]

            checkTV.setOnClickListener { onClickListener(1) }
            checkTV1.setOnClickListener { onClickListener(2) }
            checkTV2.setOnClickListener { onClickListener(3) }
        }
    }

    private fun onClickListener(data: Int) {
        val intent = Intent(this, CalenderClick::class.java)
        intent.putExtra("myList", selectedList as Serializable)
        intent.putExtra("key", data)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}
