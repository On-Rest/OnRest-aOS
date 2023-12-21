package com.chobo.onrest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.CommunityBinding
import com.chobo.onrest.databinding.QuestChoiceBinding
import com.chobo.onrest.databinding.QuestHistoryBinding
import com.chobo.onrest.databinding.QuestListBinding

class   QuestFragment : Fragment() {

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
            add(QuestHistoryData("21일", "노래를 들어와요"))
        }
        questHistoryAdapter.datas = datas
        questHistoryAdapter.notifyDataSetChanged()
    }
}