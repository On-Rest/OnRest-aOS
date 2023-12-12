package com.chobo.onrest

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.chobo.onrest.databinding.ActivityMainBinding
import com.chobo.onrest.databinding.CommunityBinding

class Community : AppCompatActivity(){
    private lateinit var binding: CommunityBinding
    lateinit var communityAdapter: CommunityAdapter
    val datas = mutableListOf<CommunityData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CommunityBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(R.layout.community)

    }
    private fun initRecycler() {
        communityAdapter = CommunityAdapter(this)
        binding.list.adapter = communityAdapter

        datas.apply {
            add(CommunityData(title = "햄스터?", detail = "햄스터란...", tag1 = "#햄", tag2 = "#찌", comment = 19, heart = 27))
            // 임시 지정(수동)
            communityAdapter.datas = datas
            communityAdapter.notifyDataSetChanged()
        }
    }
}