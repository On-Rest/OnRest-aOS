package com.chobo.onrest

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.PostWriteBinding
import com.chobo.onrest.dto.PostSubmitRequest
import com.chobo.onrest.dto.PostSubmitResponse
import com.chobo.onrest.retrofit.PostService
import com.chobo.onrest.retrofit.RetrofitClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostWrite : AppCompatActivity() {
    private lateinit var binding: PostWriteBinding
    private lateinit var editText: EditText
    private lateinit var captionTextView: TextView
    private lateinit var editText1: EditText
    private lateinit var captionTextView1: TextView
    private val maxCharacters = 300 // 최대 글자 수
    private val maxCharacters1 = 30 // 최대 글자 수
    private var maxToggleCount = 2 // 최대 토글 횟수
    private var currentToggleCount = 0 // 현재 토글 횟수
    private var inputText1 = "" // 초기 값을 빈 문자열로 설정
    private var currentLength1 = 0
    private var inputText = "" // 초기 값을 빈 문자열로 설정
    private var currentLength = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PostWriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.header1.setOnClickListener { super.onBackPressed() }
        setupToggleListeners()
        setupEditTextWatchers()
        setupSendButtonListener()
        binding.gobackIcon.setOnClickListener { super.onBackPressed() }
    }

    private fun setupToggleListeners() {
        val toggleListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (currentToggleCount >= maxToggleCount) {
                    buttonView.isChecked = false
                } else {
                    currentToggleCount++
                }
            } else {
                currentToggleCount--
            }
            updateSendButtonState()
        }

        arrayOf(
            binding.sad, binding.shy, binding.anoying, binding.angry,
            binding.joyful, binding.tranquility, binding.excited,
            binding.helpless, binding.happy
        ).forEach { it.setOnCheckedChangeListener(toggleListener) }
    }

    private fun setupEditTextWatchers() {
        editText = binding.detailinput
        captionTextView = binding.detailTextnum

        editText.addTextChangedListener(createTextWatcher(maxCharacters) {
            inputText = it
            currentLength = inputText.length
            updateSendButtonState()
            updateTextViewState(captionTextView, currentLength, maxCharacters)
        })

        editText1 = binding.titleinput
        captionTextView1 = binding.titleTextnum

        editText1.addTextChangedListener(createTextWatcher(maxCharacters1) {
            inputText1 = it
            currentLength1 = inputText1.length
            updateSendButtonState()
            updateTextViewState(captionTextView1, currentLength1, maxCharacters1)
        })
    }

    private fun setupSendButtonListener() {
        binding.send.setOnClickListener {
            if (currentLength1 > 0 && currentLength > 0 && currentToggleCount == 2) {
                val retrofit = RetrofitClass()
                retrofit.postService.submitPost(
                    PostSubmitRequest(
                        subject = inputText1,
                        doc = inputText,
                        type = "board",
                        clientId = "fuck-fuck-fuck-fuck",
                        emotion = 1
                    )
                ).enqueue(object : Callback<PostSubmitResponse> {
                    override fun onResponse(
                        call: Call<PostSubmitResponse>,
                        response: Response<PostSubmitResponse>
                    ) {
                        this@PostWrite.finish()
                    }

                    override fun onFailure(call: Call<PostSubmitResponse>, t: Throwable) {
                        // Handle failure
                    }
                })
                // rest of your code
                super.onBackPressed()
            }
        }
    }

    private fun createTextWatcher(maxLength: Int, callback: (String) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    val text = it.toString()
                    if (text.length > maxLength) {
                        val truncatedText = text.substring(0, maxLength)
                        editText.setText(truncatedText)
                        editText.setSelection(maxLength)
                    }
                    callback(text)
                }
            }
        }
    }

    private fun updateSendButtonState() {
        binding.send.isChecked = currentLength1 > 0 && currentLength > 0 && currentToggleCount == 2
    }

    private fun updateTextViewState(textView: TextView, currentLength: Int, maxLength: Int) {
        textView.text = "$currentLength/$maxLength"
        if (currentLength > maxLength) {
            textView.setTextColor(Color.parseColor("#E92626"))
        } else {
            textView.setTextColor(Color.parseColor("#89857C"))
        }
    }
}
