package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.HeaderBinding

class Header : AppCompatActivity() {
    private lateinit var binding: HeaderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HeaderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.wholerest.setOnClickListener() {
            startActivity(Intent(this, NaviActivity::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}