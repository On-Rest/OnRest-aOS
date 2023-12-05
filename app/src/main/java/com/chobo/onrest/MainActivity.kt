package com.chobo.onrest

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


// MainActivity.java
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    var missionList = arrayListOf<Mission>(Mission (12,"미션","미션디테일",true))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val missionAdapter = MainListAdapter(this, missionList)
        mainListView.adapter = missionAdapter
    }
}