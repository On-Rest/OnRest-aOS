package com.chobo.onrest.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.QuestChoiceBinding

class QuestChoice : AppCompatActivity() {
    private lateinit var binding: QuestChoiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.setOnClickListener {
            onBackPressed()
        }

        val receivedValue = intent.getStringExtra("key") ?: ""

        val intent = Intent(this, QuestList::class.java).apply {
            putExtra("key", receivedValue)
        }

        val onClickListener: (View) -> Unit = {
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
        binding.choice1.setOnClickListener(onClickListener)
        binding.choice2.setOnClickListener(onClickListener)
        binding.choice3.setOnClickListener {
            onBackPressed()
        }
    }
}
