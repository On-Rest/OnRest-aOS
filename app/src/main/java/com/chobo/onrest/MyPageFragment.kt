package com.chobo.onrest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.FragmentMyPageBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.BubbleDataSet
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPieChart()
    }

    private fun initPieChart() {
        // 데이터 추가는 Entry 클래스를 이용한다.
        val feelRatio = listOf(
            PieEntry(0f),
            PieEntry(100f),
            PieEntry(0f),
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
}