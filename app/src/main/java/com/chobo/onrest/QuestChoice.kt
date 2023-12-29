package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.QuestChoiceBinding

class QuestChoice : AppCompatActivity() {
    private lateinit var binding: QuestChoiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestChoiceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val receivedValue = intent.getStringExtra("key") // 받은 값

        binding.choice1.setOnClickListener() {
            val intent =Intent(this, QuestList::class.java)
            intent.putExtra("key", receivedValue) // 데이터 전달
            startActivity(intent)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.choice2.setOnClickListener() {
            val intent =Intent(this, QuestList::class.java)
            intent.putExtra("key", receivedValue) // 데이터 전달
            startActivity(intent)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.choice3.setOnClickListener() {
            val intent =Intent(this, Calender::class.java)
            startActivity(intent)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}