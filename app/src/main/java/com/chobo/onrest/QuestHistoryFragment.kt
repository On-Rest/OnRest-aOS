package com.chobo.onrest

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.chobo.onrest.QuestHistory
import com.chobo.onrest.databinding.QuestHistoryBinding
import com.chobo.onrest.databinding.QuestHistoryPopupBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import com.chobo.onrest.QuestHistory as QuestHistory1

class   QuestHistoryFragment : Fragment() {

    private lateinit var binding: QuestHistoryBinding
    lateinit var questHistoryAdapter: QuestHistoryAdapter
    val datas = mutableListOf<QuestHistoryData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QuestHistoryBinding.inflate(inflater, container, false)
        initRecycler()
        readFile(requireContext())
        return binding.root
    }
    private fun readFile(context: Context){
        val fileName = "myData.txt" // 파일 이름
        try {
            val fileInputStream = context.openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            // 여러 줄에 걸쳐 저장된 값을 읽어옴
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                // 각 줄에서 읽어온 값을 처리
                // 예: Log.d("TAG", "Read line: $line")
            }

            fileInputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initRecycler() {
        questHistoryAdapter = QuestHistoryAdapter(requireContext())
        binding.questList.adapter = questHistoryAdapter

        datas.apply {
            add(QuestHistoryData("21일", "명상을 해봐요"))
            add(QuestHistoryData("21일", "스트레칭을 해봐요"))
            add(QuestHistoryData("21일", "노래를 들어봐요"))
        }
        questHistoryAdapter.datas = datas
        questHistoryAdapter.notifyDataSetChanged()

    }
    fun showPopup(context: Context, anchorView: View) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.quest_history_popup, null)

        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        val binding = QuestHistoryPopupBinding.bind(popupView)
        var title = "다섯번 웃기"
        // 팝업창 내부의 버튼과 텍스트뷰에 대한 작업
        binding.title.text = "“$title“"

        // 버튼에 클릭 리스너 추가
        binding.cancel.setOnClickListener {
            popupWindow.dismiss()
        }

        binding.complete.setOnClickListener {
            popupWindow.dismiss()
        }

        // 팝업창이 나타날 위치 설정
        popupWindow.showAsDropDown(anchorView)
    }
}