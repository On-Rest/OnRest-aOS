package com.chobo.onrest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.CommunityBinding
import com.chobo.onrest.databinding.QuestListBinding

class QuestFragment : Fragment() {

    private lateinit var binding: QuestListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = QuestListBinding.inflate(inflater, container, false)
        return binding.root
    }
}