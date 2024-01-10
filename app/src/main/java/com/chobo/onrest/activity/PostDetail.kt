package com.chobo.onrest.activity

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.chobo.onrest.databinding.PostDetailBinding


class PostDetail : AppCompatActivity() {
    private lateinit var binding: PostDetailBinding
    var inputText = "" // 초기 값을 빈 문자열로 설정
    var currentLength = 0
    var maxCharacters = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PostDetailBinding.inflate(layoutInflater)
        val view = binding.root
        binding.title.text = intent.getStringExtra("title") // 받은 값
        binding.detail.text = intent.getStringExtra("detail") // 받은 값
        binding.heartnum.text = intent.getStringExtra("heartnum") // 받은 값

        setContentView(view)

        binding.goback.setOnClickListener{
            super.onBackPressed()
        }
        binding.send.setOnClickListener{
            super.onBackPressed()
        }
        binding.postDetailInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                inputText = s.toString()
                currentLength = inputText.length
                binding.send.isVisible = false
                binding.textcounter.isVisible = false

                if (currentLength > 0){
                    binding.send.isVisible = true
                }
                if (currentLength > 30){
                    binding.textcounter.isVisible = true
                }
                val add = maxCharacters-currentLength
                binding.textcounter.text = add.toString()// 현재 글자 수를 보여줍니다

                if (currentLength > maxCharacters) {
                    binding.postDetailInput.setText(inputText.substring(0, maxCharacters))
                    binding.textcounter.setTextColor(Color.parseColor("#E92626")) // 텍스트 색상을 변경합니다
                } else {
                    // 글자 수 제한 안에 있을 때 텍스트 색상을 리셋합니다
                    binding.textcounter.setTextColor(Color.parseColor("#89857C"))
                }            }
        })
    }
}