package com.chobo.onrest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.CommunityBinding

class CommunityFragment : Fragment() {

    private var _binding: CommunityBinding? = null
    private val binding get() = _binding!!
    private lateinit var communityAdapter: CommunityAdapter
    private val datas = mutableListOf<CommunityData>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CommunityBinding.inflate(inflater, container, false)
        initRecycler()
        showCustomPopup(binding.filter, requireContext())
        return binding.root
    }

    private fun initRecycler() {
        communityAdapter = CommunityAdapter(requireContext())
        binding.list.adapter = communityAdapter

        datas.apply {
            add(CommunityData("b b", "b b b b b.", "#화난", "#짜증", 0, 0))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
        }
        communityAdapter.datas = datas
        communityAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (datas.isEmpty()){
            binding.list.setBackgroundColor(Color.parseColor("#00000000"))
        }
        else{
            binding.list.setBackgroundColor(Color.parseColor("#F8F5F1"))
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
        val popupView = inflater.inflate(R.layout.community_popup_view, null)
        val widthInDp = 340 // 원하는 가로 크기(dp)
        val heightInDp = 123 // 원하는 세로 크기(dp)
        val density = resources.displayMetrics.density
        val widthInPx = (widthInDp * density).toInt()
        val heightInPx = (heightInDp * density).toInt()

        val popupWindow = PopupWindow(popupView, widthInPx, heightInPx, true)
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
        }
        popupWindow.isOutsideTouchable = true
    }
}
