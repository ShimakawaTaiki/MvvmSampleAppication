package com.example.mvvmsampleappication

import java.text.SimpleDateFormat
import java.util.*

class Util {
    companion object {
        fun getVersionCode() = BuildConfig.VERSION_CODE
        fun getVersionName() = BuildConfig.VERSION_NAME
    }
}

fun Calendar.getDateStringYMD(): String {
    val fmt = SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN)
    return fmt.format(this.time)
}

fun Calendar.clearTime(): Calendar {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
    return this
}