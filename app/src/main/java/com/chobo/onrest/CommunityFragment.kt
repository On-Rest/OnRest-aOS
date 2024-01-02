package com.chobo.onrest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.CommunityBinding
import com.chobo.onrest.databinding.CommunityPopupViewBinding
import kotlin.math.log

class CommunityFragment : Fragment() {

    private var _binding: CommunityBinding? = null
    private val binding get() = _binding!!
    private lateinit var communityAdapter: CommunityAdapter
    private val datas = mutableListOf<CommunityData>()
    private val searchedList = mutableListOf<CommunityData>()
    var tagList = mutableListOf<String>()
    var List = mutableListOf<CommunityData>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CommunityBinding.inflate(inflater, container, false)
        search()
        initRecycler()
        showCustomPopup(binding.filter, requireContext())
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecycler() {
        datas.clear()
        List.clear()
        communityAdapter = CommunityAdapter(requireContext())
        binding.list.adapter = communityAdapter
        List.add(CommunityData("title", "this is 내용", "행복", "신난", 100, 23))
        List.add(CommunityData("title", "this is 내용", "행복", "신난", 100, 23))
        List.add(CommunityData("title", "this is 내용", "행복", "신난", 100, 23))
        List.add(CommunityData("title", "this is 내용", "행복", "신난", 100, 23))
        List.add(CommunityData("title", "this is 내용", "행복", "신난", 100, 23))
        List.add(CommunityData("title", "this is 내용", "행복", "신난", 100, 23))
        for (i in List) {
            if (tagList.contains("happy")) {
                if (i.tag1 == "행복" || i.tag2 == "행복") {
                    searchedList.add(i)
                    continue
                }
            }
            if (tagList.contains("excited")) {
                if (i.tag1 == "신난" || i.tag2 == "신난") {
                    searchedList.add(i)
                    continue
                }
            }
            if (tagList.contains("joyful")) {
                if (i.tag1 == "즐거운" || i.tag2 == "즐거운") {
                    searchedList.add(i)
                    continue
                }
            }
            if (tagList.contains("tranquility")) {
                if (i.tag1 == "평온" || i.tag2 == "평온") {
                    searchedList.add(i)
                    continue
                }
            }
            if (tagList.contains("shy")) {
                if (i.tag1 == "수줍은" || i.tag2 == "수줍은") {
                    searchedList.add(i)
                    continue
                }
            }
            if (tagList.contains("anoying")) {
                if (i.tag1 == "짜증" || i.tag2 == "짜증") {
                    searchedList.add(i)
                    continue
                }
            }
            if (tagList.contains("sad")) {
                if (i.tag1 == "슬픈" || i.tag2 == "슬픈") {
                    searchedList.add(i)
                    continue
                }
            }
            if (tagList.contains("helpless")) {
                if (i.tag1 == "무기력한" || i.tag2 == "무기력한") {
                    searchedList.add(i)
                    continue
                }
            }
            if (tagList.contains("angry")) {
                if (i.tag1 == "화난" || i.tag2 == "화난") {
                    searchedList.add(i)
                    continue
                }
            }
        }
        if (tagList.isEmpty()) {
            searchedList.addAll(List)
        }
        if (datas.isEmpty()) {
            binding.list.setBackgroundColor(Color.parseColor("#00000000"))
        } else {
            binding.list.setBackgroundColor(Color.parseColor("#F8F5F1"))
        }
        communityAdapter.datas = datas
        communityAdapter.notifyDataSetChanged()
    }
    private fun search() {
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().toLowerCase()
                if (searchText == ""){
                    datas.addAll(searchedList)
                }
                else{
                    val titleIndexes = searchedList
                        .mapIndexedNotNull { index, communityData ->
                            if (communityData.title.contains(searchText)) index else null
                        }

                    if (titleIndexes.isNotEmpty()) {
                        for (i in titleIndexes){
                            datas.add(searchedList[i])
                        }
                    }
                }
                initRecycler()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchIcon.setOnClickListener {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            binding.search.clearFocus()
        }
        binding.pen.setOnClickListener {
            startActivityWithAnimation(PostWrite::class.java)
        }

    }

    private fun startActivityWithAnimation(clazz: Class<*>) {
        val intent = Intent(requireContext(), clazz)
        startActivity(intent)
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    @SuppressLint("SuspiciousIndentation")
    fun showCustomPopup(anchorView: View, context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupViewBinding = CommunityPopupViewBinding.inflate(inflater)
        val widthInDp = 340 // 원하는 가로 크기(dp)
        val heightInDp = 123 // 원하는 세로 크기(dp)
        val density = resources.displayMetrics.density
        val widthInPx = (widthInDp * density).toInt()
        val heightInPx = (heightInDp * density).toInt()

        val popupWindow = PopupWindow(popupViewBinding.root, widthInPx, heightInPx, true)
        val toggleListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            val tag = when (buttonView.id) {
                R.id.sad -> "sad"
                R.id.helpless -> "helpless"
                R.id.shy -> "shy"
                R.id.anoying -> "anoying"
                R.id.angry -> "angry"
                R.id.joyful -> "joyful"
                R.id.tranquility -> "tranquility"
                R.id.excited -> "excited"
                R.id.happy -> "happy"
                else -> ""
            }

            if (isChecked) {
                if (!tagList.contains(tag)) {
                    tagList.add(tag)
                }
            } else {
                if (tagList.contains(tag)) {
                    tagList.remove(tag)
                }
            }
        }

        popupViewBinding.sad.setOnCheckedChangeListener(toggleListener)
        popupViewBinding.helpless.setOnCheckedChangeListener(toggleListener)
        popupViewBinding.shy.setOnCheckedChangeListener(toggleListener)
        popupViewBinding.anoying.setOnCheckedChangeListener(toggleListener)
        popupViewBinding.angry.setOnCheckedChangeListener(toggleListener)
        popupViewBinding.joyful.setOnCheckedChangeListener(toggleListener)
        popupViewBinding.tranquility.setOnCheckedChangeListener(toggleListener)
        popupViewBinding.excited.setOnCheckedChangeListener(toggleListener)
        popupViewBinding.happy.setOnCheckedChangeListener(toggleListener)

        binding.filter.setOnClickListener {
            if (popupWindow.isShowing) {
                popupWindow.dismiss()
                binding.filter.isChecked = false
            } else {
                popupWindow.showAsDropDown(anchorView, 0, 25)
                binding.filter.isChecked = true
            }
        }

        popupWindow.setOnDismissListener {
            binding.filter.isChecked = false
            initRecycler()
        }
        popupWindow.isOutsideTouchable = true
    }
}
