package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.CalendarBinding

class QuestHistory : AppCompatActivity() {
    private lateinit var binding: CalendarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}