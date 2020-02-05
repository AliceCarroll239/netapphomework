package com.example.mobiletp.utils

import android.net.Uri
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class Utils {

    fun clearDateFormat(inputDateString: String): String {
        val outputDateString = inputDateString.replace("T", " ").replace("Z", "")
        val currentDateString = LocalDateTime
            .now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        var result:String

        result = if (outputDateString.day() == currentDateString.day()) {
            if (outputDateString.hour() == currentDateString.hour()) {
                (currentDateString.minute().toInt() - outputDateString.minute().toInt()).toString() + " minutes ago"
            } else (currentDateString.hour().toInt() - outputDateString.hour().toInt()).toString() + " hours ago"
        } else {
            outputDateString
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

    fun getRandomLocation(x0: Double, y0: Double, radius: Int): Uri? {
        val random = Random()
        val radiusInDegrees = (radius / 111000f).toDouble()
        val u = random.nextDouble()
        val v = random.nextDouble()
        val w = radiusInDegrees * Math.sqrt(u)
        val t = 2.0 * Math.PI * v
        val x = w * cos(t)
        val y = w * sin(t)
        val new_x = x / cos(Math.toRadians(y0))
        val foundLongitude = new_x + x0
        val foundLatitude = y + y0
        return Uri.parse("geo:$foundLongitude,$foundLatitude")
    }

    fun isMap(): Boolean {
        return Random().nextBoolean()
    }
}