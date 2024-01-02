package com.chobo.onrest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onStart(){
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)

        if(account != null) {
            Log.d("SignIn", "이름 : ${account.displayName}, 이메일 : ${account.email}, 아이디 토큰 : ${account.idToken}")
            Toast.makeText(this, "로그인이 되어있습니다", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "로그인이 되지않았습니다", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setResultSignUp() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleSignInResult(task)
            }
        }
    }

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

         setResultSignUp()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            //.requestIdToken(getString(R.string.sign_in_client_id))
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

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.getSignInIntent()
        resultLauncher.launch(signInIntent)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.email.toString()
            val displayName = account?.displayName.toString()
            val photoUrl = account?.photoUrl.toString()
            val idToken = account?.idToken.toString()

            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("username", displayName)
            editor.putString("userPhotoUrl", photoUrl)
            editor.apply()

            val serverUrl = "http://46.250.250.34:5000"

            sendIdTokenToServer(idToken, serverUrl)

            val nextPage = Intent(this, NaviActivity::class.java)
            startActivity(nextPage)

            val acount = GoogleSignIn.getLastSignedInAccount(this)

            if (acount != null) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
            }

            Log.d("로그인한 유저의 이메일", email)
            Log.d("로그인한 유저의 전체이름", displayName)
            Log.d("로그인한 유저의 프로필 사진의 주소", photoUrl)
            Log.d("로그인한 유저의 아이디 토큰", idToken)

        } catch (e: ApiException) {
            Log.e("failed", "signInResultfailed = " + e.statusCode)
            Toast.makeText(this, "구글 로그인에 실패했습니다. 상태 코드: ${e.statusCode}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendIdTokenToServer(idToken: String?, serverUrl: String) {
    }
}