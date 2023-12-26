package com.chobo.onrest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.chobo.onrest.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

// MainActivity.java
class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        binding.googleLoginButton.setOnClickListener() {
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            finish()
        }
        if (account != null) {
            Toast.makeText(this, "로그인", Toast.LENGTH_SHORT).show()
        } else {
            Log.d("SignIn", "로그인된 계정을 찾을 수 없습니다.")
            Toast.makeText(this, "아직 로그인되지 않았습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setResultSignUp()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        with(binding) {
            googleLoginButton.setOnClickListener {
                signIn()
            }
        }
    }

    private fun setResultSignUp() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleSignInResult(task)
            }
        }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.email.toString()
            val displayName = account?.displayName.toString()
            val photoUrl = account?.photoUrl.toString()
            val idToken = account?.idToken.toString()

            val serverUrl = "http://46.250.250.34:5000"

            val nextPage = Intent(this, NaviActivity::class.java)
            startActivity(nextPage)

            Log.d("로그인한 유저의 이메일", email)
            Log.d("로그인한 유저의 전체이름", displayName)
            Log.d("로그인한 유저의 프로필 사진의 주소", photoUrl)
            Log.d("로그인한 유저의 아이디 토큰", idToken)

        } catch (e: ApiException) {
            Log.w("failed", "signInResultfailed = " + e.statusCode)
        }
    }

    private fun sendIdTokenToServer(id_token: String?, serverUrl: String) {
    }
}