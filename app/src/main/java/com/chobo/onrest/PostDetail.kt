package com.chobo.onrest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.PostDetailBinding


class PostDetail : AppCompatActivity() {
    private lateinit var binding: PostDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PostDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.goback.setOnClickListener() {
            super.onBackPressed()
        }
    }
}