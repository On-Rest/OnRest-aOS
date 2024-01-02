package com.chobo.onrest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.DiaryWriteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiaryWrite : AppCompatActivity() {
    private lateinit var binding: DiaryWriteBinding
    private lateinit var emotion: String
    private lateinit var memoinputText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DiaryWriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.write.setOnClickListener() {
            memoinputText = binding.memoinput.text.toString()

            // 백그라운드 스레드에서 chatgpt() 함수 호출
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    chatgpt()
                    val intent = Intent(this@DiaryWrite, YourEmotion::class.java)
                    intent.putExtra("emotion1321", emotion)
                    editor.putString("memoinput", memoinputText)
                    editor.apply()
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                } catch (e: Exception) {
                    // 네트워크 요청 실패 시 예외 처리
                    Log.e("DiaryWrite", "Network request failed: ${e.message}")
                    // 사용자에게 알림을 표시하거나 다른 오류 처리를 수행할 수 있습니다.
                }
            }
        }
    }
    private suspend fun chatgpt() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ChatApiService::class.java)

        val requestBody = ChatRequestBody(
            "gpt-3.5-turbo",
            listOf(
                ChatMessage("system", "You are a helpful assistant."),
                ChatMessage(
                    "user",
                    "일기 속 감정의 비중에서 기쁨이 많으면 1, 감정의 비중에서  슬픔이 많으면 2,감정의 비중에서 화남이 많으면 3을 숫자만 반환해줘 문장 없이 숫자만,만약 일기가 없으면 공백을 출력해줘, 그리고 욕만있으면 1을 출력해줘 일기: $memoinputText"
                )
            )
        )
        try {
            val response = service.getChatCompletion(requestBody)
            val result = response.choices[0].message.content
            emotion = result
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}