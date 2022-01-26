package com.example.mvvmsampleappication.data

import com.example.mvvmsampleappication.R
import java.io.Serializable

data class StepCountLog(
    val date: String,
    val step: Int,
    val level: LEVEL = LEVEL.NORMAL,
    val weather: WEATHER = WEATHER.FINE
): Serializable

enum class LEVEL {
    NORMAL,
    GOOD,
    BAD
}

enum class WEATHER {
    FINE,
    RAIN,
    CLOUD,
    SNOW,
    COLD,
    HOT
}
