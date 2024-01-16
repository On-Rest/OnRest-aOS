package com.chobo.onrest.dataclass

data class CalendarData(
    val currentDay: String,
    val currentYear: String,
    val currentMonth: String,
    var stringValue: String,
    val booleanValue: Boolean,
    val editedDate: String,
    var retrievedValue: String,
    var todaysEmotion: String,
    var receivedList: List<String>,
    var selectedMission: String
)