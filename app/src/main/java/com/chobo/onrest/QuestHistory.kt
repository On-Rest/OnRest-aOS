package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.QuestHistoryBinding

class QuestHistory : AppCompatActivity() {
    private lateinit var binding: QuestHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.idDate.setOnClickListener() {
            startActivity(Intent(this, QuestList::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}