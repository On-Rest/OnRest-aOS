package com.chobo.onrest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.CalendarBinding

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
        val clickListener: View.OnClickListener = View.OnClickListener {
            startActivityWithAnimation(DiaryWrite::class.java)
        }

        val days = listOf(binding.day1, binding.day2, binding.day3, binding.day4 , binding.day5, binding.day6, binding.day7, binding.day8, binding.day9, binding.day10, binding.day11, binding.day12, binding.day13, binding.day14, binding.day15, binding.day16, binding.day18, binding.day19, binding.day20, binding.day21 , binding.day22 , binding.day23 , binding.day24, binding.day25, binding.day26, binding.day27, binding.day28 , binding.day29 , binding.day30 , binding.day31)
        for (day in days) {
            day.setOnClickListener(clickListener)
        }

    }
    private fun startActivityWithAnimation(clazz: Class<*>) {
        val intent = Intent(requireContext(), clazz)
        startActivity(intent)
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
