package com.example.mobiletp.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Utils {

    fun clearDateFormat(inputDateString: String): String {
        val outputDateString = inputDateString.replace("T", " ").replace("Z", "")
        val currentDateString = LocalDateTime
            .now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        var result:String

        if (outputDateString.day() == currentDateString.day()) {
            if (outputDateString.hour() == currentDateString.hour()) {
                result = (currentDateString.minute().toInt() - outputDateString.minute().toInt()).toString() + " minutes ago"
            } else result = (currentDateString.hour().toInt() - outputDateString.hour().toInt()).toString() + " hours ago"
        } else {
            result = outputDateString
        }
        return "Posted at $result"
    }

    fun String.day(): String {
        return this.take(10)
    }

    fun String.hour(): String {
        return this.subSequence(startIndex = 11, endIndex = 13).toString()
    }

    fun String.minute(): String {
        return this.subSequence(startIndex = 14, endIndex = 16).toString()
    }
}