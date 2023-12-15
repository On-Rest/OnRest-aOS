package com.chobo.onrest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.CommunityBinding

class CommunityFragment : Fragment() {

    private lateinit var binding: CommunityBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CommunityBinding.inflate(inflater, container, false)
        return binding.root
    }
}
