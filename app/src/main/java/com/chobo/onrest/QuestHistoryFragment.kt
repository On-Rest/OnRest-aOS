package com.chobo.onrest

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.QuestHistoryBinding
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Date

class QuestHistoryFragment : Fragment(), ToggleStateChangeListener,PopupCallback {

    private lateinit var binding: QuestHistoryBinding
    lateinit var questHistoryAdapter: QuestHistoryAdapter
    private lateinit var toggleStateChangeListener: ToggleStateChangeListener // 변수 선언
    val datas = mutableListOf<QuestHistoryData>()
    val date = Date() // 현재 날짜와 시간 가져오기
    val year = SimpleDateFormat("yyyy").format(date) // 일만 가져오기
    val month = SimpleDateFormat("MM").format(date) // 일만 가져오기
    val fileLines = mutableListOf<String>()
    val yearMonthList = mutableListOf<String>()
    var index = 0
    val dialogFragment = QuestHistoryPopup()
    companion object {
        var titleText : String = ""
        var `Boolean-position`: Int = 0
        var watchMonth : String = ""
    }
    override fun onStart(){
        super.onStart()
        readFile(requireContext())
        initRecycler()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogFragment.setCallback(this)

        toggleStateChangeListener = this // 또는 원하는 대상으로 설정
        // QuestHistoryAdapter 인스턴스를 생성할 때 ToggleStateChangeListener를 전달합니다.
        questHistoryAdapter = QuestHistoryAdapter(requireContext(), toggleStateChangeListener)
        // 리사이클러뷰 설정 등의 작업 수행


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QuestHistoryBinding.inflate(inflater, container, false)
        initDateFilter()
        dateFilter()

        return binding.root
    }
    override fun onPopupAction() {
        // QuestHistoryPopup에서 호출될 콜백 메서드의 로직 작성
        // 원하는 동작 수행
        readFile(requireContext())
        initRecycler()
    }
    override fun onToggleStateChanged(mission: String, isChecked: Boolean, position: Int) {
        // 상태가 변경된 아이템의 위치(position)와 상태(isChecked)를 여기서 사용할 수 있습니다
        titleText = mission
        `Boolean-position` = position
        dialogFragment.show(requireActivity().supportFragmentManager, "QuestHistoryPopup")
    }
    private fun initDateFilter(){
        for (year in 2023..2025) {
            for (month in 1..12) {
                val formattedMonth = String.format("%02d", month)
                yearMonthList.add("${year}년 ${formattedMonth }월")
            }
        }
        index = yearMonthList.indexOf("${year}년 ${month}월")
        initWatchMonth()
    }
    private fun dateFilter(){
        binding.goback.setOnClickListener(){
            index--
            initWatchMonth()
            readFile(requireContext())
            initRecycler()
        }
        binding.gonext.setOnClickListener(){
            index++
            initWatchMonth()
            readFile(requireContext())
            initRecycler()
        }
    }
    private fun initWatchMonth(){
        binding.todayMonthText.text = yearMonthList[index]
        watchMonth = "${yearMonthList[index].substring(0,4)}-${yearMonthList[index].substring(6,8)}"
        Log.d("tea",watchMonth)
    }
    public fun readFile(context: Context){
        val fileName = watchMonth
        // 기존 데이터를 지웁니다.
        fileLines.clear()

        try {
            val fileInputStream = context.openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                fileLines.add(line.orEmpty()) // 각 줄의 데이터를 리스트에 추가합니다.
            }

            fileInputStream.close()
        } catch (e: FileNotFoundException) {
            Log.e("FileNotFound", "파일이나 디렉토리를 찾을 수 없습니다: $fileName")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    public fun initRecycler() {
        binding.questList.adapter = questHistoryAdapter
        datas.clear()

        for(i in 0 until (fileLines.size - 2) step 3) {
                val day = fileLines[i].substring(0,2)
                datas.add(QuestHistoryData("${day}일", fileLines[i + 1], fileLines[i + 2].toBoolean()))
        }
        questHistoryAdapter.datas = datas
        questHistoryAdapter.notifyDataSetChanged()
    }
}