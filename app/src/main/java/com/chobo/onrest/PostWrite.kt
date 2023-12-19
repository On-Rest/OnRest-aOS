package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.PostWriteBinding

class PostWrite : AppCompatActivity() {
    private lateinit var binding: PostWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PostWriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.send.setOnClickListener() {
            startActivity(Intent(this, QuestList::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}