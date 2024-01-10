package com.chobo.onrest.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
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