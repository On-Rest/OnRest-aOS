package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.ActivityMainBinding
import com.chobo.onrest.databinding.CalendarBinding
import com.chobo.onrest.databinding.DiaryWriteBinding
import com.chobo.onrest.databinding.EmotionChoice1Binding
import com.chobo.onrest.databinding.EmotionChoice2AngryBinding
import com.chobo.onrest.databinding.EmotionChoice2HappyBinding
import com.chobo.onrest.databinding.HeaderBinding
import com.chobo.onrest.databinding.YourEmotionBinding

// MainActivity.java
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        binding.login.setOnClickListener() {
            startActivity(Intent(this, NaviActivity::class.java))
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }
}

class Calender : AppCompatActivity() {
    private lateinit var binding: CalendarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        binding.speechbubble.setOnClickListener() {
            startActivity(Intent(this, QuestList::class.java))
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
        binding.stripe.setOnClickListener() {
            startActivity(Intent(this, QuestList::class.java))
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }
}

class DiaryWrite : AppCompatActivity() {
    private lateinit var binding: DiaryWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DiaryWriteBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        binding.write.setOnClickListener() {
            val intent = Intent(this, EmotionChoice1::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }
}

class YourEmotion : AppCompatActivity() {
    private lateinit var binding: YourEmotionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = YourEmotionBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        binding.choice1.setOnClickListener() {
            val intent = Intent(this, EmotionChoice1::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
        binding.choice2.setOnClickListener() {
            val intent = Intent(this, EmotionChoice1::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }
}
class EmotionChoice1 : AppCompatActivity() {
    private lateinit var binding: EmotionChoice1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice1Binding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        binding.happyface.setOnClickListener() {
            val intent = Intent(this, EmotionChoice2Happy::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
        binding.angryface.setOnClickListener() {
            val intent = Intent(this, EmotionChoice2Angry::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
        binding.sadface.setOnClickListener() {
            val intent = Intent(this, EmotionChoice2Sad::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }
}
class EmotionChoice2Happy : AppCompatActivity() {
    private lateinit var binding: EmotionChoice2HappyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice2HappyBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        binding.choice1.setOnClickListener() {
            val intent = Intent(this, QuestList::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
        binding.choice2.setOnClickListener() {
            val intent = Intent(this, QuestList::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
        binding.choice3.setOnClickListener() {
            val intent = Intent(this, QuestList::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }
}
class EmotionChoice2Angry : AppCompatActivity() {
    private lateinit var binding: EmotionChoice2AngryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice2AngryBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        binding.choice1.setOnClickListener() {
            val intent = Intent(this, QuestList::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
        binding.choice2.setOnClickListener() {
            val intent = Intent(this, QuestList::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
        binding.choice3.setOnClickListener() {
            val intent = Intent(this, QuestList::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }
}
class EmotionChoice2Sad : AppCompatActivity() {
    private lateinit var binding: EmotionChoice2HappyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice2HappyBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        binding.choice1.setOnClickListener() {
            val intent = Intent(this, QuestList::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
        binding.choice2.setOnClickListener() {
            val intent = Intent(this, QuestList::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
        binding.choice3.setOnClickListener() {
            val intent = Intent(this, QuestList::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }
}
class QuestList : AppCompatActivity() {
    private lateinit var binding: EmotionChoice2HappyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice2HappyBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)
    }
}
class Header : AppCompatActivity() {
    private lateinit var binding: HeaderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HeaderBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        binding.wholerest.setOnClickListener() {
            val intent = Intent(this, Calender::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }
}
