package com.chobo.onrest

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.CommunityPopupViewBinding

class CommunityPopupView : AppCompatActivity() {
    private lateinit var binding: CommunityPopupViewBinding
    val taglist = mutableMapOf(
        "sad" to false,
        "helpless" to false,
        "shy" to false,
        "anoying" to false,
        "angry" to false,
        "joyful" to false,
        "tranquility" to false,
        "excited" to false,
        "happy" to false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CommunityPopupViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val toggleListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            taglist[buttonView.toString()] = isChecked
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