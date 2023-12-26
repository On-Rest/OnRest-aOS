package com.chobo.onrest

import android.content.Context
import android.content.Intent
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
        binding..requestFocus()
        return binding.root
    }

    private fun initRecycler() {
        communityAdapter = CommunityAdapter(requireContext())
        binding.list.adapter = communityAdapter

        datas.apply {

            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            add(CommunityData("오늘의 화난썰", "오늘은 아이디어 페스티벌 프로젝트를 진행했다.", "#화난", "#짜증", 19, 27))
            // 임시 저장 임의 데이터
        }
        communityAdapter.datas = datas
        communityAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pen.setOnClickListener {
            startActivityWithAnimation(PostWrite::class.java)
        }
    }

    private fun startActivityWithAnimation(clazz: Class<*>) {
        val intent = Intent(requireContext(), clazz)
        startActivity(intent)
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
    fun showCustomPopup(anchorView: View, context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.community_popup_view, null)
        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

            binding.filter.setOnClickListener {
            if (popupWindow.isShowing) {
                popupWindow.dismiss()
            } else {
                popupWindow.showAsDropDown(anchorView)
            }
        }

        // 외부 클릭으로 닫기 설정
        popupWindow.isOutsideTouchable = false
    }

}
