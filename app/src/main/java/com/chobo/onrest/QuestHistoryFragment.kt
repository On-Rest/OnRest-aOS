package com.chobo.onrest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.QuestHistoryBinding
import com.chobo.onrest.databinding.QuestHistoryPopupBinding

class   QuestHistoryFragment : Fragment() {

    private lateinit var binding: QuestHistoryBinding
    lateinit var questHistoryAdapter: QuestHistoryAdapter
    val datas = mutableListOf<QuestHistoryData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = QuestHistoryBinding.inflate(inflater, container, false)
        initRecycler()
        return binding.root
    }

    private fun initRecycler() {
        questHistoryAdapter = QuestHistoryAdapter(requireContext())
        binding.questList.adapter = questHistoryAdapter

        datas.apply {
            add(QuestHistoryData("21일", "명상을 해봐요"))
            add(QuestHistoryData("21일", "스트레칭을 해봐요"))
            add(QuestHistoryData("21일", "노래를 들어봐요"))
        }
        questHistoryAdapter.datas = datas
        questHistoryAdapter.notifyDataSetChanged()
    }
    fun showPopup(context: Context, anchorView: View) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.quest_history_popup, null)

        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        val binding = QuestHistoryPopupBinding.bind(popupView)
        var title = "다섯번 웃기"
        // 팝업창 내부의 버튼과 텍스트뷰에 대한 작업
        binding.title.text = "“$title“"

        // 버튼에 클릭 리스너 추가
        binding.cancel.setOnClickListener {
            popupWindow?.dismiss()
        }

        binding.complete.setOnClickListener {
            popupWindow?.dismiss()
        }

        // 팝업창이 나타날 위치 설정
        popupWindow.showAsDropDown(anchorView)
    }
}