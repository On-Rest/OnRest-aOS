package com.chobo.onrest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.DiaryWriteBinding

class DiaryWrite : AppCompatActivity() {
    private lateinit var binding: DiaryWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DiaryWriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()


        binding.write.setOnClickListener() {
            val intent = Intent(this, YourEmotion::class.java)
            val memoinputText = binding.memoinput.text.toString()
            editor.putString("memoinput",memoinputText)
            editor.apply() // 변경 사항을 저장합니다.
            startActivity(intent)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}