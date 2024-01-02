package com.chobo.onrest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.CommunityPopupViewBinding

class CommunityPopupView : AppCompatActivity() {
    private lateinit var binding: CommunityPopupViewBinding
    val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val cleckedList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CommunityPopupViewBinding.inflate(layoutInflater)
        Log.d("tlqkf", "tkqwfk")

        val view = binding.root
        setContentView(view)
        val toggleListener = CompoundButton.OnCheckedChangeListener { buttonView, _ ->
            cleckedList.add(buttonView.toString().substring(0,7))
            editor.putString("toggle-state", cleckedList.toString())
            editor.apply()
        }

        binding.sad.setOnCheckedChangeListener(toggleListener)
        binding.helpless.setOnCheckedChangeListener(toggleListener)
        binding.shy.setOnCheckedChangeListener(toggleListener)
        binding.anoying.setOnCheckedChangeListener(toggleListener)
        binding.angry.setOnCheckedChangeListener(toggleListener)
        binding.joyful.setOnCheckedChangeListener(toggleListener)
        binding.tranquility.setOnCheckedChangeListener(toggleListener)
        binding.excited.setOnCheckedChangeListener(toggleListener)
        binding.happy.setOnCheckedChangeListener(toggleListener)
    }
}