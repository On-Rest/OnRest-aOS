package com.chobo.onrest

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.CalendarBinding
import java.io.File
import java.io.Serializable

class CalendarFragment : Fragment() {

    private lateinit var binding: CalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.stripe.setOnClickListener {
            startActivityWithAnimation(DiaryWrite::class.java)
        }
        binding.speechbubble.setOnClickListener {
            startActivityWithAnimation(DiaryWrite::class.java)
        }
        val dayButtons = listOf(
            binding.day1, binding.day2, binding.day3, binding.day4, binding.day5,
            binding.day6, binding.day7, binding.day8, binding.day9, binding.day10,
            binding.day11, binding.day12, binding.day13, binding.day14, binding.day15,
            binding.day16, binding.day17, binding.day18, binding.day19, binding.day20,
            binding.day21 , binding.day22 , binding.day23 , binding.day24, binding.day25,
            binding.day26, binding.day27, binding.day28 , binding.day29 , binding.day30 ,
            binding.day31
        )

        dayButtons.forEachIndexed { index, button ->
            val filesDir = requireContext().filesDir
            val date = String.format("%02d", index+1)
            val myFile = File(filesDir, "2024-01-${date}")
            if (myFile.exists()){
                button.setOnClickListener {
                    val intent = Intent(requireContext(), CalenderClick1::class.java)
                    intent.putExtra("BUTTON_ID", date) // 데이터 전달
                    startActivity(intent)
                    requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                }
            }
        }

    }
    private fun startActivityWithAnimation(clazz: Class<*>) {
        val intent = Intent(requireContext(), clazz)
        startActivity(intent)
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
