package com.chobo.onrest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.FragmentPieChartBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class MyPageFragment : Fragment() {
    private var _binding: FragmentPieChartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPieChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPieChart()
    }

    private fun initPieChart() {
        // 데이터 추가는 Entry 클래스를 이용한다.
        val feelRatio = listOf(
            PieEntry(33f),
            PieEntry(33f),
            PieEntry(33f),
        )

        val pieColors = listOf(
            resources.getColor(R.color.brown1, null),
            resources.getColor(R.color.brown2, null),
            resources.getColor(R.color.brown3, null),
        )

        val dataSet = PieDataSet(feelRatio, "")

        dataSet.colors = pieColors

        dataSet.setDrawValues(false)

        // legend.isEnabled : 범례 유무 설정
        // isRotationEnabled : 차트 회전 활성화 여부 설정
        // animateY(1200, Easing.EaseInOutCubic) : 애니메이션 시간, 효과 설정
        binding.feelPieChart.apply {


            data = PieData(dataSet)

            description.isEnabled = false
            legend.isEnabled = false
            isRotationEnabled = true
            holeRadius = 62f
            setTouchEnabled(false)
            animateY(1200, Easing.EaseInOutCubic)

            // animate()를 호출하면 차트를 새로 고치기 위해 invalidate()를 호출할 필요가 없다.
            animate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}