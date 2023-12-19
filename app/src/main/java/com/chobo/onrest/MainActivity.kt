package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.ActivityMainBinding
import com.chobo.onrest.databinding.CalendarBinding
import com.chobo.onrest.databinding.DiaryWriteBinding
import com.chobo.onrest.databinding.EmotionChoice1Binding
import com.chobo.onrest.databinding.EmotionChoice2AngryBinding
import com.chobo.onrest.databinding.EmotionChoice2HappyBinding
import com.chobo.onrest.databinding.HeaderBinding
import com.chobo.onrest.databinding.PostWriteBinding
import com.chobo.onrest.databinding.QuestChoiceBinding
import com.chobo.onrest.databinding.QuestHistoryBinding
import com.chobo.onrest.databinding.QuestListBinding
import com.chobo.onrest.databinding.YourEmotionBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

// MainActivity.java
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mGoogleSingInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSingInClient = GoogleSignIn.getClient(this, gso)

        binding.googlebutton.setOnClickListener() {
            SignIn()
        }

        binding.googlebutton.setOnClickListener() {
            startActivity(Intent(this, NaviActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}

    private fun SignIn() {
        val signInIntent = mGoogleSingInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == 1) {
            val data: Intent? = result.data
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.email.toString()
            val familyName = account?.familyName.toString()
        } catch(e: ApiException) {
            Log.w("failed", "signInResult:failed code = " + e.statusCode)
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
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            binding.stripe.setOnClickListener() {
                startActivity(Intent(this, QuestList::class.java))
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            binding.choice2.setOnClickListener() {
                val intent = Intent(this, EmotionChoice1::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            binding.angryface.setOnClickListener() {
                val intent = Intent(this, EmotionChoice2Angry::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            binding.sadface.setOnClickListener() {
                val intent = Intent(this, EmotionChoice2Sad::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            binding.choice2.setOnClickListener() {
                val intent = Intent(this, QuestList::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            binding.choice3.setOnClickListener() {
                val intent = Intent(this, QuestList::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            binding.choice2.setOnClickListener() {
                val intent = Intent(this, QuestList::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            binding.choice3.setOnClickListener() {
                val intent = Intent(this, QuestList::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            binding.choice2.setOnClickListener() {
                val intent = Intent(this, QuestList::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            binding.choice3.setOnClickListener() {
                val intent = Intent(this, QuestList::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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
                startActivity(Intent(this, Calender::class.java))
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
class Calender : AppCompatActivity() {
    private lateinit var binding: CalendarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.speechbubble.setOnClickListener() {
            startActivity(Intent(this, DiaryWrite::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.stripe.setOnClickListener() {
            startActivity(Intent(this, DiaryWrite::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}
class DiaryWrite : AppCompatActivity() {
    private lateinit var binding: DiaryWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DiaryWriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.write.setOnClickListener() {
            val intent = Intent(this, YourEmotion::class.java)
            startActivity(intent)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}
class YourEmotion : AppCompatActivity() {
    private lateinit var binding: YourEmotionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = YourEmotionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.choice1.setOnClickListener() {
            startActivity(Intent(this, QuestList::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.choice2.setOnClickListener() {
            startActivity(Intent(this, EmotionChoice1::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}
class EmotionChoice1 : AppCompatActivity() {
    private lateinit var binding: EmotionChoice1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.happyface.setOnClickListener() {
            startActivity(Intent(this, EmotionChoice2Happy::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.angryface.setOnClickListener() {
            startActivity(Intent(this, EmotionChoice2Angry::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.sadface.setOnClickListener() {
            startActivity(Intent(this, EmotionChoice2Sad::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}
class EmotionChoice2Happy : AppCompatActivity() {
    private lateinit var binding: EmotionChoice2HappyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice2HappyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.choice1.setOnClickListener() {
            startActivity(Intent(this, QuestChoice::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.choice2.setOnClickListener() {
            startActivity(Intent(this, QuestChoice::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.choice3.setOnClickListener() {
            startActivity(Intent(this, QuestChoice::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}
class EmotionChoice2Angry : AppCompatActivity() {
    private lateinit var binding: EmotionChoice2AngryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice2AngryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.choice1.setOnClickListener() {
            startActivity(Intent(this, QuestChoice::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.choice2.setOnClickListener() {
            startActivity(Intent(this, QuestChoice::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.choice3.setOnClickListener() {
            startActivity(Intent(this, QuestChoice::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}
class EmotionChoice2Sad : AppCompatActivity() {
    private lateinit var binding: EmotionChoice2HappyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmotionChoice2HappyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.choice1.setOnClickListener() {
            startActivity(Intent(this, QuestChoice::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.choice2.setOnClickListener() {
            startActivity(Intent(this, QuestChoice::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.choice3.setOnClickListener() {
            startActivity(Intent(this, QuestChoice::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}
class QuestList : AppCompatActivity() {
    private lateinit var binding: QuestListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.speechbubble.setOnClickListener() {
            startActivity(Intent(this, NaviActivity::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}
class Header : AppCompatActivity() {
    private lateinit var binding: HeaderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HeaderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.wholerest.setOnClickListener() {
            startActivity(Intent(this, NaviActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
class QuestChoice : AppCompatActivity() {
    private lateinit var binding: QuestChoiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestChoiceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.choice1.setOnClickListener() {
            startActivity(Intent(this, QuestList::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.choice2.setOnClickListener() {
            startActivity(Intent(this, QuestList::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        binding.choice3.setOnClickListener() {
            startActivity(Intent(this, NaviActivity::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}
class QuestHistory : AppCompatActivity() {
    private lateinit var binding: QuestHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.idDate.setOnClickListener() {
            startActivity(Intent(this, QuestList::class.java))
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
    }
}
class PostWrite : AppCompatActivity() {
    private lateinit var binding: PostWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PostWriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.send.setOnClickListener() {
            startActivity(Intent(this, QuestList::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}