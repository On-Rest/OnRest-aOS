package com.chobo.onrest

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.ActivityMainBinding
import com.chobo.onrest.databinding.MyPageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


// MainActivity.java
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        // 바인딩한 뷰에서 bottomNavigationView 가져오기
        val bottomNavigationView: BottomNavigationView = binding!!.bottomNavigation

        // 아이템 선택 이벤트 리스너 설정
        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.home -> item.setIcon(R.drawable.calender1_on)
                R.id.star -> item.setIcon(R.drawable.calender2_on)
                R.id.group -> item.setIcon(R.drawable.calender3_on)
                R.id.hotel -> item.setIcon(R.drawable.calender4_on)
            }

            // 선택한 아이템 외의 다른 아이템들의 색상 원래대로 변경
            val menu = bottomNavigationView.menu
            for (i in 0 until menu.size()) {
                val menuItem = menu.getItem(i)
                if (menuItem.itemId != item.itemId) {
                    menuItem.setIcon(getOriginalIcon(menuItem.itemId))
                }
            }
            true
        }
    }

    // 원래 아이템의 색상을 가져오는 함수
    private fun getOriginalIcon(itemId: Int): Int {
        return when (itemId) {
            R.id.home -> R.drawable.calender1_off
            R.id.star -> R.drawable.calender2_off
            R.id.group -> R.drawable.calender3_off
            R.id.hotel -> R.drawable.calender4_off
            else -> 0
        }
    }
}