package com.chobo.onrest.model

import android.content.Context
import java.io.File
import java.io.FileOutputStream

class CalendarModel(private val context: Context) {

    fun writefileTodo(
        currentYear: String,
        currentMonth: String,
        todaysEmotion: String,
        stringValue: String,
        booleanValue: Boolean
    ) {
        val filesDir = context.filesDir
        val fileName = "${currentYear}-${currentMonth}"
        val fileOutputStream: FileOutputStream
        val myFile = File(filesDir, fileName)
        fileOutputStream = if (myFile.exists()) {
            context.openFileOutput(fileName, Context.MODE_APPEND)
        } else {
            context.openFileOutput(fileName, Context.MODE_PRIVATE)
        }
        val filcontent = buildString {
            appendln(todaysEmotion)
            appendln(stringValue)
            appendln(booleanValue)
        }
        try {
            fileOutputStream.use {
                it.write(filcontent.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun writefile(
        currentYear: String,
        currentMonth: String,
        currentDay: String,
        todaysEmotion: String,
        editedDate: String,
        selectedMission: String,
        receivedList: List<String>,
        retrievedValue: String
    ) {
        val fileName = "${currentYear}-${currentMonth}-${currentDay}"
        val fileContent = buildString {
            appendln(todaysEmotion)
            appendln(editedDate)
            appendln("${currentDay}일")
            appendln(selectedMission)
            receivedList.take(3).forEach { appendln(it) }
            append(retrievedValue)
        }

        try {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(fileContent.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
