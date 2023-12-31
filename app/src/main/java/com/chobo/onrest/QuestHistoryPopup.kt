package com.chobo.onrest

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.chobo.onrest.databinding.QuestHistoryPopupBinding
import java.io.File

class QuestHistoryPopup : DialogFragment() {
    private lateinit var binding: QuestHistoryPopupBinding // 바인딩 클래스명으로 수정
    private var popupCallback: PopupCallback? = null // 인터페이스 변수 선언


    override fun onResume() {
        super.onResume()
        val width = (resources.displayMetrics.widthPixels)
        val height = (resources.displayMetrics.heightPixels)
        dialog?.window?.setLayout(width, height)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = QuestHistoryPopupBinding.inflate(inflater, container, false) // 바인딩 초기화
        dialogFragmentButton()
        setTitle()
        return binding.root // 레이아웃 파일의 루트 뷰 반환
    }
    private fun setTitle(){
        binding.title.text = QuestHistoryFragment.titleText
    }
    fun setCallback(callback: PopupCallback) {
        popupCallback = callback
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransparentDialog) // 투명한 스타일 적용
        isCancelable = true // 다이얼로그 취소 가능 여부 설정
    }
    private fun dialogFragmentButton(){
        binding.cancel.setOnClickListener(){
            popupCallback?.onPopupAction() // Callback 호출

            dismiss()
        }
        binding.complete.setOnClickListener(){
            modifyFile()
            popupCallback?.onPopupAction() // Fragment의 콜백 함수 호출

            dismiss()
        }
    }
    private fun modifyFile() {
        val filesDir = requireContext().applicationContext.filesDir
        val fileName = QuestHistoryFragment.watchMonth
        val myFile = File(filesDir, fileName)


        try {
            val lines = myFile.readLines().toMutableList() // 파일의 모든 줄을 읽어옴
            val lineIndexToModify = ((QuestHistoryFragment.`Boolean-position`)*3)+2// 수정하려는 줄의 인덱스 (0부터 시작하는 줄 번호)

            if (lineIndexToModify < lines.size) {
                lines[lineIndexToModify] = "true" // 선택한 줄을 새로운 문자열로 대체
            } else {
                // 선택한 줄이 파일의 범위를 벗어날 경우 예외처리
                throw IndexOutOfBoundsException("선택한 줄이 파일의 범위를 벗어났습니다.")
            }
            myFile.bufferedWriter().use { writer ->
                lines.forEach { line ->
                    writer.write("$line\n") // 수정된 내용을 파일에 씀
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}