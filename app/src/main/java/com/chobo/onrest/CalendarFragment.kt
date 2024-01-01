package com.chobo.onrest

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chobo.onrest.databinding.CalendarBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStreamReader

class CalendarFragment : Fragment() {
    private lateinit var binding: CalendarBinding
    val grayImageDrawable = com.chobo.onrest.R.drawable.gray_face
    val fileLines = mutableListOf<String>()
    var fileName = ""
    var emotion = grayImageDrawable
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
            fileName = "2024-01-${date}"
            val myFile = File(filesDir, fileName)
            if (myFile.exists()) {
                readFile(requireContext())
                button.setOnClickListener {
                    val intent = Intent(requireContext(), CalenderClick1::class.java)
                    intent.putExtra("BUTTON_ID", date) // 데이터 전달
                    startActivity(intent)
                    requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                }
                emotion = when {
                    fileLines.isEmpty() -> grayImageDrawable // 파일을 읽지 못한 경우
                    fileLines[0] == "angry" -> com.chobo.onrest.R.drawable.angry_face
                    fileLines[0] == "happy" -> com.chobo.onrest.R.drawable.happy_face
                    fileLines[0] == "sad" -> com.chobo.onrest.R.drawable.sad_face
                    else -> grayImageDrawable // 감정에 해당하는 이미지가 없을 경우 회색 이미지
                }
                button.setImageResource(emotion)
            }
        }
    }
    private fun readFile(context: Context){
        fileLines.clear()

        try {
            val fileInputStream = context.openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            val firstLine = bufferedReader.readLine() // 첫 번째 줄을 읽습니다.
            if (firstLine != null) {
                fileLines.add(firstLine) // 첫 번째 줄을 리스트에 추가합니다.
            }

            fileInputStream.close()
        } catch (e: FileNotFoundException) {
            Log.e("FileNotFound", "파일이나 디렉토리를 찾을 수 없습니다: $fileName")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun startActivityWithAnimation(clazz: Class<*>) {
        val intent = Intent(requireContext(), clazz)
        startActivity(intent)
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
