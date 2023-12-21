package com.chobo.onrest

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PostWriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var toggleButton1: ToggleButton = findViewById(R.id.sad)
        var toggleButton2: ToggleButton = findViewById(R.id.helpless)
        var toggleButton3: ToggleButton = findViewById(R.id.shy)
        var toggleButton4: ToggleButton = findViewById(R.id.anoying)
        var toggleButton5: ToggleButton = findViewById(R.id.angry)
        var toggleButton6: ToggleButton = findViewById(R.id.joyful)
        var toggleButton7: ToggleButton = findViewById(R.id.tranquility)
        var toggleButton8: ToggleButton = findViewById(R.id.excited)
        var toggleButton9: ToggleButton = findViewById(R.id.happy)

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

        toggleButton1.setOnCheckedChangeListener(toggleListener)
        toggleButton2.setOnCheckedChangeListener(toggleListener)
        toggleButton3.setOnCheckedChangeListener(toggleListener)
        toggleButton4.setOnCheckedChangeListener(toggleListener)
        toggleButton5.setOnCheckedChangeListener(toggleListener)
        toggleButton6.setOnCheckedChangeListener(toggleListener)
        toggleButton7.setOnCheckedChangeListener(toggleListener)
        toggleButton8.setOnCheckedChangeListener(toggleListener)
        toggleButton9.setOnCheckedChangeListener(toggleListener)

        editText = binding.detailinput // EditText 참조 가져오기
        captionTextView = binding.detailTextnum // TextView 참조 가져오기

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 텍스트 변경 전에 호출되는 메서드
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 첫 번째 EditText에 대한 코드 (maxCharacters = 300)
                val inputText = s.toString()
                val currentLength = inputText.length
                captionTextView.text = "$currentLength/$maxCharacters" // 현재 글자 수를 보여줍니다

                if (currentLength > maxCharacters) {
                    editText.setText(inputText.substring(0, maxCharacters))
                    editText.setSelection(maxCharacters)
                    captionTextView.setTextColor(Color.parseColor("#E92626")) // 텍스트 색상을 변경합니다
                } else {
                    // 글자 수 제한 안에 있을 때 텍스트 색상을 리셋합니다
                    captionTextView1.setTextColor(Color.parseColor("#89857C"))
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후에 호출되는 메서드
            }
        })

        editText1 = binding.titleinput // EditText 참조 가져오기
        captionTextView1 = binding.titleTextnum // TextView 참조 가져오기

        editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 텍스트 변경 전에 호출되는 메서드
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 두 번째 EditText에 대한 코드 (maxCharacters1 = 30)
                val inputText1 = s.toString()
                val currentLength1 = inputText1.length
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

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후에 호출되는 메서드
            }
        })

        binding.send.setOnClickListener() {
            startActivity(Intent(this, QuestList::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
        binding.gobackIcon.setOnClickListener() {
            super.onBackPressed()
        }
    }
}