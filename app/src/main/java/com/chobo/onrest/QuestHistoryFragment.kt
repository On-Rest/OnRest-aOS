package com.chobo.onrest

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.QuestHistoryBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Date

class   QuestHistoryFragment : Fragment() {

    private lateinit var binding: QuestHistoryBinding
    lateinit var questHistoryAdapter: QuestHistoryAdapter
    val datas = mutableListOf<QuestHistoryData>()
    val date = Date() // 현재 날짜와 시간 가져오기
    val year = SimpleDateFormat("yyyy").format(date) // 일만 가져오기
    val fileLines = mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QuestHistoryBinding.inflate(inflater, container, false)
        readFile(requireContext())
        initRecycler()
        return binding.root
    }
    private fun readFile(context: Context){
        val fileName = "${year}"

        try {
            val fileInputStream = context.openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                fileLines.add(line.orEmpty()) // 각 줄의 데이터를 리스트에 추가합니다.
            }

            fileInputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initRecycler() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        questHistoryAdapter = QuestHistoryAdapter(requireContext())
        binding.questList.adapter = questHistoryAdapter
        for(i in 0 until (fileLines.size - 2) step 3) {
            val day = dateFormat.format(date).substring(8)
            datas.add(QuestHistoryData("${day}일", fileLines[i + 1], fileLines[i + 2].toBoolean()))
        }
        questHistoryAdapter.datas = datas
        questHistoryAdapter.notifyDataSetChanged()
    }
}