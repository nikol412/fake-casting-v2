package ru.nikol.fakecasting.common.extension

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun Date.subtract(date: Date, timeUnit: TimeUnit): Long {
    val differenceInMillies: Long = this.time - date.time
    return timeUnit.convert(differenceInMillies, TimeUnit.MILLISECONDS)
}

fun Date.fromString(date: String, pattern: String = "yyyy-MM-dd HH:mm:ss"): Date {
    try {
        val format = SimpleDateFormat(pattern)
        return format.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        return Date()
    }
}

private const val MONTH = DateUtils.DAY_IN_MILLIS * 30

fun Date.timeAgo(): String? {
    val currentTime = System.currentTimeMillis()
    val timeDiff = (currentTime - this.time)
    return if (timeDiff < 1000) {
        null
    } else {
        DateUtils.getRelativeTimeSpanString(
            this.time, currentTime, when {
                timeDiff > DateUtils.YEAR_IN_MILLIS -> DateUtils.YEAR_IN_MILLIS
                timeDiff > MONTH -> MONTH
                timeDiff > DateUtils.WEEK_IN_MILLIS -> DateUtils.WEEK_IN_MILLIS
                timeDiff > DateUtils.DAY_IN_MILLIS -> DateUtils.DAY_IN_MILLIS
                timeDiff > DateUtils.HOUR_IN_MILLIS -> DateUtils.HOUR_IN_MILLIS
                timeDiff > DateUtils.MINUTE_IN_MILLIS -> DateUtils.MINUTE_IN_MILLIS
                else -> DateUtils.SECOND_IN_MILLIS
            }
        ).toString()
    }
}

fun Date.format(pattern: String, locale: Locale = Locale.US): String {
    return SimpleDateFormat(pattern, locale).format(this)
}

fun Date.getDayOfWeekShortName(locale: Locale = Locale.US): String {
    val dayName = this.format("EEE", locale)
    val dayNumber = getDayNumber().toInt()
    return "$dayName ${dayNumber.getNumberWithSuffix()}"
}

fun Date.getDayOfWeekName(locale: Locale = Locale.US): String {
    val dayName = this.format("EEEE", locale)
    val dayNumber = getDayNumber().toInt()
    return "$dayName, ${dayNumber.getNumberWithSuffix()}"
}

fun Int.getNumberWithSuffix(): String {
    return "$this${this.getSuffix()}"
}

fun Int.getSuffix(): String? {
    return if (this in 11..13) {
        "th"
    } else when (this % 10) {
        1 -> "st"
        2 -> "nd"
        3 -> "rd"
        else -> "th"
    }
}

fun Date.getDayName(locale: Locale = Locale.US): String {
    return this.format("EEE", locale)
}

fun Date.getDayNumber(locale: Locale = Locale.US): String {
    return this.format("d", locale)
}

fun Date.getMonthName(locale: Locale = Locale.US): String {

    return Calendar.getInstance().apply {
        time = this@getMonthName
    }.getDisplayName(Calendar.MONTH, Calendar.LONG_STANDALONE, locale) ?: "N/A"
}