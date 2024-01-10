package com.chobo.onrest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.chobo.onrest.activity.MyPageFragment
import com.chobo.onrest.databinding.ActivityNaviBinding
import com.chobo.onrest.fragment.CalendarFragment
import com.chobo.onrest.fragment.CommunityFragment
import com.chobo.onrest.fragment.QuestHistoryFragment

private const val TAG_CALENDAR = "calendar_fragment"
private const val TAG_COMMUNITY = "community_fragment"
private const val TAG_QUEST_History = "quest_History_fragment"
private const val TAG_MY_PAGE = "my_page_fragment"

class NaviActivity : AppCompatActivity() {
    val binding by lazy { ActivityNaviBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment(TAG_CALENDAR, CalendarFragment())

        binding.navigationView.setOnItemSelectedListener {item ->
            when(item.itemId){
                R.id.calender -> setFragment(TAG_CALENDAR, CalendarFragment())
                R.id.community -> setFragment(TAG_COMMUNITY, CommunityFragment())
                R.id.quest -> setFragment(TAG_QUEST_History, QuestHistoryFragment())
                R.id.my_page -> setFragment(TAG_MY_PAGE, MyPageFragment())
            }
            true
        }
    }

    private fun setFragment(tag : String, fragment : Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }
        val calendar = manager.findFragmentByTag(TAG_CALENDAR)
        val community = manager.findFragmentByTag(TAG_COMMUNITY)
        val quest = manager.findFragmentByTag(TAG_QUEST_History)
        val my_page = manager.findFragmentByTag(TAG_MY_PAGE)


        if (calendar != null) {
            fragTransaction.hide(calendar)
        }

        if (community != null) {
            fragTransaction.hide(community)
        }

        if (quest != null) {
            fragTransaction.hide(quest)
        }

        if (my_page != null) {
            fragTransaction.hide(my_page)
        }

        if (tag == TAG_CALENDAR) {
            if(calendar != null) {
                fragTransaction.show(calendar)
            }
        }

        if (tag == TAG_COMMUNITY) {
            if(community != null) {
                fragTransaction.show(community)
            }
        }

        if (tag == TAG_QUEST_History) {
            if(quest != null){
                fragTransaction.show(quest)
            }
        }

        if (tag == TAG_MY_PAGE) {
            if(my_page != null) {
                fragTransaction.show(my_page)
            }
        }

        fragTransaction.commitAllowingStateLoss()
     }
}