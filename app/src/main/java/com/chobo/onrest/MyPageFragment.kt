package com.chobo.onrest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.CommunityBinding
import com.chobo.onrest.databinding.MyPageBinding

class MyPageFragment : Fragment() {

    private lateinit var binding: MyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyPageBinding.inflate(inflater, container, false)
        return binding.root
    }
}
