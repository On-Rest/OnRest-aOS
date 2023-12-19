package com.chobo.onrest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.CommunityBinding

class CommunityFragment : Fragment() {

    private lateinit var binding: CommunityBinding
    lateinit var communityAdapter: CommunityAdapter
    val datas = mutableListOf<CommunityData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CommunityBinding.inflate(inflater, container, false)
        initRecycler()
        return binding.root
    }
    private fun initRecycler() {
        communityAdapter = CommunityAdapter(requireContext())
        binding.list.adapter = communityAdapter

        datas.apply {
            add(CommunityData("햄스터?", "햄스터란dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd...", "#햄", "#찌", 19, 27))
            add(CommunityData("햄스터?", "햄스터란아니 명훈아 좀더 긴걸 넣었어애지...", "#햄", "#찌", 19, 27))
            add(CommunityData("햄스터?", "햄스터란...", "#햄", "#찌", 19, 27))
            add(CommunityData("햄스터?", "햄스터란...", "#햄", "#찌", 19, 27))
            add(CommunityData("햄스터?", "햄스터란...", "#햄", "#찌", 19, 27))
            add(CommunityData("햄스터?", "햄스터란...", "#햄", "#찌", 19, 27))
            // 임시 지정(수동)
            communityAdapter.datas = datas
            communityAdapter.notifyDataSetChanged()
        }
    }
}
