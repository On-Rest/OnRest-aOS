package com.chobo.onrest.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chobo.onrest.view.CalenderClick1
import com.chobo.onrest.view.DiaryWrite
import com.chobo.onrest.R
import com.chobo.onrest.databinding.CalendarBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStreamReader

class CalendarFragment : Fragment() {
    private lateinit var binding: CalendarBinding
    private val defaultImageResource = R.drawable.gray_face
    private var fileDate = ""
    private var emotionImage = defaultImageResource

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
        setUpCalendarImage()

        val onClickListener: (View) -> Unit = {
            startActivityWithAnimation(DiaryWrite::class.java)
        }

        binding.stripe.setOnClickListener(onClickListener)
        binding.speechbubble.setOnClickListener(onClickListener)
    }

    override fun onResume() {
        super.onResume()
        setUpCalendarImage()
    }

    private fun setUpCalendarImage() {
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
            val date = String.format("%02d", index + 1)
            fileDate = "2024-01-${date}"
            val myFile = File(filesDir, fileDate)

            if (myFile.exists()) {
                val fileLines = readFile(requireContext(), fileDate)
                button.setOnClickListener {
                    val intent = Intent(requireContext(), CalenderClick1::class.java)
                    intent.putExtra("BUTTON_ID", date) // 데이터 전달
                    startActivity(intent)
                    requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
                emotionImage = when {
                    fileLines.isEmpty() -> defaultImageResource // 파일을 읽지 못한 경우
                    fileLines[0] == "angry" -> R.drawable.angry_face
                    fileLines[0] == "happy" -> R.drawable.happy_face
                    fileLines[0] == "sad" -> R.drawable.sad_face
                    else -> defaultImageResource // 감정에 해당하는 이미지가 없을 경우 회색 이미지
                }
                button.setImageResource(emotionImage)
            }
        }
    }

    private fun readFile(context: Context, fileName: String): List<String> {
        val fileLines = mutableListOf<String>()
        try {
            context.openFileInput(fileName)?.use { fileInputStream ->
                val inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val firstLine = bufferedReader.readLine() // 첫 번째 줄을 읽습니다.
                if (firstLine != null) {
                    fileLines.add(firstLine) // 첫 번째 줄을 리스트에 추가합니다.
                }
            }
        } catch (e: FileNotFoundException) {
            Log.e("FileNotFound", "파일이나 디렉토리를 찾을 수 없습니다: $fileName")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return fileLines
    }

    private fun startActivityWithAnimation(clazz: Class<*>) {
        val intent = Intent(requireContext(), clazz)
        startActivity(intent)
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
