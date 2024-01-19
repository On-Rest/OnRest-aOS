package com.chobo.onrest.viewmodel


import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chobo.onrest.dataclass.CalendarData
import com.chobo.onrest.model.CalendarModel
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
class CalendarViewModel(application: Application, intent: Intent) : AndroidViewModel(application) {
    private val calendarModel: CalendarModel = CalendarModel(application)
    private val receivedList = intent.getSerializableExtra("myList") as? List<String> ?: emptyList()
    private val selectedMission = intent.getStringExtra("key") ?: ""
    private val sharedPrefs = application.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    private fun writeToFileTodo(calendarData: CalendarData) {
        with(calendarData) {
            calendarModel.writefileTodo(
                currentYear,
                currentMonth,
                todaysEmotion,
                stringValue,
                booleanValue
            )
        }
    }

    private fun writeToFile(calendarData: CalendarData) {
        with(calendarData) {
            calendarModel.writefile(
                currentYear,
                currentMonth,
                currentDay,
                todaysEmotion,
                editedDate,
                selectedMission,
                receivedList,
                retrievedValue
            )
        }
    }

    val calendarData: LiveData<CalendarData> = MutableLiveData<CalendarData>().apply {
        value = CalendarData(
            currentDay = SimpleDateFormat("dd").format(Date()),
            currentYear = SimpleDateFormat("yyyy").format(Date()),
            currentMonth = SimpleDateFormat("MM").format(Date()),
            selectedMission = selectedMission,
            stringValue = when (selectedMission) {
                "1", "2", "3" -> receivedList.getOrNull(selectedMission.toInt() - 1) ?: ""
                else -> ""
            },
            booleanValue = false,
            editedDate = "",
            retrievedValue = sharedPrefs.getString("memoinput", "defaultValue").toString(),
            todaysEmotion = sharedPrefs.getString("yourEmotion", "defaultValue").toString(),
            receivedList = emptyList(),
        )

        value?.let { writeToFileTodo(it) }

        value?.let { writeToFile(it) }
    }
}
