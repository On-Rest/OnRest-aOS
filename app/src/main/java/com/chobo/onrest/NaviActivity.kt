package com.chobo.onrest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.chobo.onrest.databinding.ActivityNaviBinding
import com.chobo.onrest.fragment.CalendarFragment
import com.chobo.onrest.fragment.CommunityFragment
import com.chobo.onrest.fragment.QuestHistoryFragment
import com.chobo.onrest.view.MyPageFragment

private const val TAG_CALENDAR = "calendar_fragment"
private const val TAG_COMMUNITY = "community_fragment"
private const val TAG_QUEST_History = "quest_History_fragment"
private const val TAG_MY_PAGE = "my_page_fragment"
class NaviActivity : AppCompatActivity() {
    private val binding by lazy { ActivityNaviBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment(TAG_CALENDAR, CalendarFragment())

        binding.navigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.calender -> setFragment(TAG_CALENDAR, CalendarFragment())
                R.id.community -> setFragment(TAG_COMMUNITY, CommunityFragment())
                R.id.quest -> setFragment(TAG_QUEST_History, QuestHistoryFragment())
                R.id.my_page -> setFragment(TAG_MY_PAGE, MyPageFragment())
            }
            true
        }
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        val calendar = manager.findFragmentByTag(TAG_CALENDAR)
        val community = manager.findFragmentByTag(TAG_COMMUNITY)
        val quest = manager.findFragmentByTag(TAG_QUEST_History)
        val myPage = manager.findFragmentByTag(TAG_MY_PAGE)

        hideFragment(fragTransaction, calendar)
        hideFragment(fragTransaction, community)
        hideFragment(fragTransaction, quest)
        hideFragment(fragTransaction, myPage)

        if (manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        } else {
            fragTransaction.show(manager.findFragmentByTag(tag)!!)
        }

        fragTransaction.commitNow()
    }

    private fun hideFragment(transaction: FragmentTransaction, fragment: Fragment?) {
        fragment?.let {
            transaction.hide(it)
        }
    }
}
