package com.chobo.onrest

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.PostWriteBinding


class PostWrite : AppCompatActivity() {
    private lateinit var binding: PostWriteBinding
    private lateinit var editText: EditText
    private lateinit var captionTextView: TextView
    private lateinit var editText1: EditText
    private lateinit var captionTextView1: TextView
    private val maxCharacters = 300 // 최대 글자 수
    private val maxCharacters1 = 30 // 최대 글자 수
    var maxToggleCount = 2 // 최대 토글 횟수
    var currentToggleCount = 0 // 현재 토글 횟수
    var inputText1 = "" // 초기 값을 빈 문자열로 설정
    var currentLength1 = 0
    var inputText = "" // 초기 값을 빈 문자열로 설정
    var currentLength = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PostWriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toggleListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (currentToggleCount >= maxToggleCount) {
                    // 최대 토글 횟수를 초과하면 다시 체크를 해제합니다.
                    buttonView.isChecked = false
                } else {
                    // 현재 토글 횟수가 최대 토글 횟수보다 작을 경우에만 토글 횟수 증가
                    currentToggleCount++
                }
            } else {
                // 토글 해제 시 토글 횟수 감소
                currentToggleCount--
            }
        }

        with(binding){
            sad.setOnCheckedChangeListener(toggleListener)
            shy.setOnCheckedChangeListener(toggleListener)
            anoying.setOnCheckedChangeListener(toggleListener)
            angry.setOnCheckedChangeListener(toggleListener)
            joyful.setOnCheckedChangeListener(toggleListener)
            tranquility.setOnCheckedChangeListener(toggleListener)
            excited.setOnCheckedChangeListener(toggleListener)
            helpless.setOnCheckedChangeListener(toggleListener)
            happy.setOnCheckedChangeListener(toggleListener)
        }

        editText = binding.detailinput // EditText 참조 가져오기
        captionTextView = binding.detailTextnum // TextView 참조 가져오기

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                inputText = s.toString()
                currentLength = inputText.length
                binding.send.isChecked = false
                if (currentLength1 > 0){
                    if (currentLength > 0){
                        binding.send.isChecked = true
                    }
                }
                captionTextView.text = "$currentLength/$maxCharacters" // 현재 글자 수를 보여줍니다

                if (currentLength > maxCharacters) {
                    editText.setText(inputText.substring(0, maxCharacters))
                    editText.setSelection(maxCharacters)
                    captionTextView.setTextColor(Color.parseColor("#E92626")) // 텍스트 색상을 변경합니다
                } else {
                    // 글자 수 제한 안에 있을 때 텍스트 색상을 리셋합니다
                    captionTextView.setTextColor(Color.parseColor("#89857C"))
                }            }
        })
        editText1 = binding.titleinput // EditText 참조 가져오기
        captionTextView1 = binding.titleTextnum // TextView 참조 가져오기

        editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후에 호출되는 메서드
                inputText1 = s.toString()
                currentLength1 = inputText1.length
                binding.send.isChecked = false
                if (currentLength1 > 0){
                    if (currentLength > 0){
                        binding.send.isChecked = true
                    }
                }
                captionTextView1.text = "$currentLength1/$maxCharacters1" // 현재 글자 수를 보여줍니다

                if (currentLength1 > maxCharacters1) {
                    editText1.setText(inputText1.substring(0, maxCharacters1))
                    editText1.setSelection(maxCharacters1)
                    captionTextView1.setTextColor(Color.parseColor("#E92626")) // 텍스트 색상을 변경합니다
                } else {
                    // 글자 수 제한 안에 있을 때 텍스트 색상을 리셋합니다
                    captionTextView1.setTextColor(Color.parseColor("#89857C"))
                }
            }
        })
        binding.send.setOnClickListener() {
            binding.send.isChecked = false
            if (currentLength1 > 0){
                if (currentLength > 0){
                    startActivity(Intent(this, QuestList::class.java))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            }
        }
        binding.gobackIcon.setOnClickListener() {
            super.onBackPressed()
        }
    }
}
