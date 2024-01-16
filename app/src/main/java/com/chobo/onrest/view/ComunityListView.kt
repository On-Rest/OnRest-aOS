package com.chobo.onrest.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.ComunityListViewBinding


class ComunityListView : AppCompatActivity() {
    private lateinit var binding: ComunityListViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ComunityListViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.detail.setOnClickListener {
            startActivity(Intent(this, PostDetail::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}