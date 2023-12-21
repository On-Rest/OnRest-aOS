package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.CalendarBinding

class Calender : AppCompatActivity() {
    private lateinit var binding: CalendarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.speechbubble.setOnClickListener() {
            startActivity(Intent(this, DiaryWrite::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.stripe.setOnClickListener() {
            startActivity(Intent(this, DiaryWrite::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.calender.setOnClickListener() {
            startActivity(Intent(this, CalenderClick::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}