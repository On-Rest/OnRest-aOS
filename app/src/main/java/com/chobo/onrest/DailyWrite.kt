package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.DiaryWriteBinding

class DiaryWrite : AppCompatActivity() {
    private lateinit var binding: DiaryWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DiaryWriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.write.setOnClickListener() {
            val intent = Intent(this, YourEmotion::class.java)
            startActivity(intent)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}