package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.CalendarClickBinding

class CalenderClick : AppCompatActivity() {
    private lateinit var binding: CalendarClickBinding
    var detail1 : String = "오늘은 아이디어 페스티벌 프로젝트를 진행했다. 오류가 너무 많이나서 힘들었다. 또 협업에서 소통이 가장 중요한데, 소통이 잘 되지 않아 너무 화가 났다. \\n그래도 다들 힘들거라고 생각해서 다 같이 힘내야겠다고 생각했다."
    var dateTV : String = "21일"
    var dateTV1 : String = "21일"
    var dateTV2 : String = "21일"
    var missionTV : String = "명상을 해봐요"
    var missionTV1 : String = "스트래칭을 해봐요"
    var missionTV2 : String = "노래를 들어봐요"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarClickBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.dateTV.text = "21일"
        binding.dateTV1.text = "21일"
        binding.dateTV2.text = "21일"
        binding.missionTV.text = "명상을 해봐요"
        binding.missionTV1.text = "스트래칭을 해봐요"
        binding.missionTV2.text = "노래를 들어봐요"

        binding.dateTV.text = dateTV
        binding.dateTV1.text = dateTV1
        binding.dateTV2.text = dateTV2
        binding.missionTV.text = missionTV
        binding.missionTV1.text = missionTV1
        binding.missionTV2.text = missionTV2
    }
}