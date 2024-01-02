package com.chobo.onrest

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColor
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.chobo.onrest.databinding.FragmentMyPageBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.BubbleDataSet
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Date

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    val date = Date() // 현재 날짜와 시간 가져오기
    val year = SimpleDateFormat("yyyy").format(date) // 일만 가져오 기
    val month = SimpleDateFormat("MM").format(date) // 일만 가져오
    val fileLines = mutableListOf<String>()
    var fileName = ""
    var fileDate = "" // String으로 변경
    var angryf = 0f
    var sadf = 0f
    var happyf = 0f
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)

        val sharedPreferences = activity?.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val username = sharedPreferences?.getString("username", "") ?: ""
        val userPhotoUrl = sharedPreferences?.getString("userPhotoUrl", "") ?: ""

        binding.nickname.text = username

        val profileImageView = binding.profile
        Glide.with(this).load(userPhotoUrl).into(profileImageView)

        binding.logoutButton.setOnClickListener {
            val mainActivity = activity as MainActivity
            mainActivity.logout()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPieChart()
    }
    @SuppressLint("SetTextI18n")
    private fun initPieChart() {
        dataSeting()
        binding.happyPercent.text = "${(happyf*100).toInt()}%"
        binding.angryPercent.text = "${(angryf*100).toInt()}%"
        binding.sadPercent.text = "${(sadf*100).toInt()}%"

        val feelRatio = listOf(
            PieEntry(happyf),
            PieEntry(angryf),
            PieEntry(sadf)
        )

        val pieColors = listOf(
            resources.getColor(R.color.brown1, null),
            resources.getColor(R.color.brown2, null),
            resources.getColor(R.color.brown3, null),
        )

        val dataSet = PieDataSet(feelRatio, "")

        dataSet.setSliceSpace(3f)

        dataSet.colors = pieColors

        dataSet.setDrawValues(false)

        binding.feelPieChart.apply {
            data = PieData(dataSet)

            description.isEnabled = false
            legend.isEnabled = false
            isRotationEnabled = true
            holeRadius = 62f
            setTouchEnabled(false)
            animateY(1200, Easing.EaseInOutCubic)
            animate()
        }
    }
    class PieDataSet {
        var sliceSpace: Float = 0f
        fun setSliceSpace() {
            sliceSpace = 5f
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun dataSeting(){
        fileLines.clear()
        for (i in 1..31){
            fileDate = String.format("%02d", i) // fileDate를 String 형식으로 변경하여 할당
            readFile(requireContext())
        }
        for (i in fileLines){
            when(i){
                "angry" -> angryf++
                "sad" -> sadf++
                "happy" -> happyf++
            }
        }
        val ans = angryf.toInt() + sadf.toInt() + happyf.toInt()
        angryf /= ans
        sadf /= ans
        happyf /= ans
    }
    private fun readFile(context: Context) {
        fileName = "${year}-${month}-${fileDate}"

        try {
            val fileInputStream = context.openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            val line: String? = bufferedReader.readLine() // 첫 번째 줄만 읽기

            line?.let { fileLines.add(it) } // 첫 번째 줄 데이터만 리스트에 추가

            fileInputStream.close()
        } catch (e: FileNotFoundException) {
            Log.e("FileNotFound", "파일이나 디렉토리를 찾을 수 없습니다: $fileName")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}