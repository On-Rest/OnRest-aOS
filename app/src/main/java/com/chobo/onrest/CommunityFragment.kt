package com.chobo.onrest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.CommunityBinding
import com.chobo.onrest.dto.GetPostResponse
import com.chobo.onrest.retrofit.RetrofitClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityFragment : Fragment() {

    private var _binding: CommunityBinding? = null
    private val binding get() = _binding!!
    private lateinit var communityAdapter: CommunityAdapter
    private val datas = mutableListOf<GetPostResponse>()
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

        communityAdapter.notifyDataSetChanged()

        loadData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (datas.isEmpty()){
            binding.list.setBackgroundColor(Color.parseColor("#00000000"))
        }
        else{
            binding.list.setBackgroundColor(Color.parseColor("#F8F5F1"))
        }
        binding.searchIcon.setOnClickListener{
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            binding.search.clearFocus()
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
    private fun loadData() {
        val retrofit = RetrofitClass()
        retrofit.postService.getPost()
            .enqueue(object : Callback<GetPostResponse> {
                override fun onResponse(
                    call: Call<GetPostResponse>,
                    response: Response<GetPostResponse>
                ) {
                    Log.d("community", response.body().toString())
                }

                override fun onFailure(call: Call<GetPostResponse>, t: Throwable) {
                    Log.d("this is error","에러에요")
                }
            })
    }
}
