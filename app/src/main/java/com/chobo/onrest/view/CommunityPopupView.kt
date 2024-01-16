package com.chobo.onrest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.CommunityPopupViewBinding

class CommunityPopupView : AppCompatActivity() {
    private lateinit var binding: CommunityPopupViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CommunityPopupViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}