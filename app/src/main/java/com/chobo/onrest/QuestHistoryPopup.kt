package com.chobo.onrest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.chobo.onrest.databinding.QuestHistoryPopupBinding

class QuestHistoryPopup : DialogFragment() {
    private lateinit var binding: QuestHistoryPopupBinding // 바인딩 클래스명으로 수정

    override fun onResume() {
        super.onResume()
        val width = (resources.displayMetrics.widthPixels)
        val height = (resources.displayMetrics.heightPixels)
        dialog?.window?.setLayout(width, height)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = QuestHistoryPopupBinding.inflate(inflater, container, false) // 바인딩 초기화
        return binding.root // 레이아웃 파일의 루트 뷰 반환
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransparentDialog) // 투명한 스타일 적용
        isCancelable = true // 다이얼로그 취소 가능 여부 설정
    }
}

