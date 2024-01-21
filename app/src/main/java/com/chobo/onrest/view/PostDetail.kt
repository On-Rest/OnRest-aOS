package com.chobo.onrest.view

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.chobo.onrest.databinding.PostDetailBinding

class PostDetail : AppCompatActivity() {
    private lateinit var binding: PostDetailBinding
    private var inputText = ""
    private var currentLength = 0
    private val maxCharacters = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.title.text = intent.getStringExtra("title") // 받은 값
        binding.detail.text = intent.getStringExtra("detail") // 받은 값
        binding.heartnum.text = intent.getStringExtra("heartnum") // 받은 값

        with(binding) {
            goback.setOnClickListener { super.onBackPressed() }
            send.setOnClickListener { super.onBackPressed() }

            postDetailInput.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    inputText = s.toString()
                    currentLength = inputText.length
                    send.isVisible = currentLength > 0
                    textcounter.isVisible = currentLength > 30

                    val add = maxCharacters - currentLength
                    textcounter.text = add.toString()

                    if (currentLength > maxCharacters) {
                        postDetailInput.setText(inputText.substring(0, maxCharacters))
                        textcounter.setTextColor(Color.parseColor("#E92626"))
                    } else {
                        textcounter.setTextColor(Color.parseColor("#89857C"))
                    }
                }
            })
        }
    }
}
