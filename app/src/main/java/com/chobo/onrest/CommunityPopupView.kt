package com.chobo.onrest

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import androidx.fragment.app.DialogFragment
import com.chobo.onrest.databinding.CommunityBinding
import com.chobo.onrest.databinding.CommunityPopupViewBinding


class CommunityPopupView {
    fun showCustomPopup(context: Context, anchorView: View) {
        lateinit var binding: CommunityBinding
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.community_popup_view, null)
        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)


        // 팝업창 내부 요소들 참조
        binding.filter.setOnClickListener {
            // 토글 버튼 상태에 따라 팝업창 열고 닫기
            if (popupWindow?.isShowing == true) {
                popupWindow.dismiss()
            } else {
                PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
            }
        }

        // 팝업창이 표시될 위치 설정
        popupWindow.showAsDropDown(anchorView)

        // 외부 클릭으로 닫기 설정
        popupWindow.isOutsideTouchable = true
    }
}