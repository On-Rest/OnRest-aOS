package com.chobo.onrest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.CommunityBinding
import com.chobo.onrest.databinding.QuestChoiceBinding
import com.chobo.onrest.databinding.QuestListBinding

class QuestFragment : Fragment() {

    private lateinit var binding: QuestChoiceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = QuestChoiceBinding.inflate(inflater, container, false)
        return binding.root
    }
}