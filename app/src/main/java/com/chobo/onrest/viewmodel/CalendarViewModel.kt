package com.chobo.onrest.viewmodel


import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chobo.onrest.dataclass.CalendarData
import com.chobo.onrest.model.CalendarModel
import java.text.SimpleDateFormat
import java.util.Date

class CalendarViewModel(application: Application,intent: Intent) : AndroidViewModel(application) {
    private val _calendarData = MutableLiveData<CalendarData>()
    val calendarData: LiveData<CalendarData>
        get() = _calendarData
    private val calendarModel: CalendarModel = CalendarModel(application)

    init {
        val receivedList =
            intent.getSerializableExtra("myList") as? List<String> ?: emptyList()
        val selectedMission = intent.getStringExtra("key") ?: ""
        _calendarData.value = CalendarData(
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
            retrievedValue = "",
            todaysEmotion = "",
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

        calendarModel.writefileTodo(
            _calendarData.value!!.currentYear,
            _calendarData.value!!.currentMonth,
            _calendarData.value!!.todaysEmotion,
            _calendarData.value!!.stringValue,
            _calendarData.value!!.booleanValue
        )

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

        calendarModel.writefile(
            _calendarData.value!!.currentYear,
            _calendarData.value!!.currentMonth,
            _calendarData.value!!.currentDay,
            _calendarData.value!!.todaysEmotion,
            _calendarData.value!!.editedDate,
            _calendarData.value!!.selectedMission,
            _calendarData.value!!.receivedList,
            _calendarData.value!!.retrievedValue,
        )
    }
}
