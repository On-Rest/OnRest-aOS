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
     val _calendarData = MutableLiveData<CalendarData>()
    val calendarData: LiveData<CalendarData>
        get() = _calendarData
    private val calendarModel: CalendarModel = CalendarModel(application)
    private val receivedList = intent.getSerializableExtra("myList") as? List<String> ?: emptyList()
    private val selectedMission = intent.getStringExtra("key") ?: ""
    private val sharedPrefs = application.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    init {
        _calendarData.value = CalendarData(
            currentDay = SimpleDateFormat("dd").format(Date()),
            currentYear = SimpleDateFormat("yyyy").format(Date()),
            currentMonth = SimpleDateFormat("MM").format(Date()),
            selectedMission = intent.getStringExtra("key") ?: "",
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
        _calendarData.value?.let { calendarData ->
            calendarModel.writefileTodo(
                calendarData.currentYear,
                calendarData.currentMonth,
                calendarData.todaysEmotion,
                calendarData.stringValue,
                calendarData.booleanValue,
            )
        }

        _calendarData.value?.let { calendarData ->
            calendarModel.writefile(
                calendarData.currentYear,
                calendarData.currentMonth,
                calendarData.currentDay,
                calendarData.todaysEmotion,
                calendarData.editedDate,
                calendarData.selectedMission,
                calendarData.receivedList,
                calendarData.retrievedValue,
            )
        }
    }
}

