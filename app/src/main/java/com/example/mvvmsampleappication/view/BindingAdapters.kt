package com.example.mvvmsampleappication.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsampleappication.R
import com.example.mvvmsampleappication.data.LEVEL
import com.example.mvvmsampleappication.data.StepCountLog
import com.example.mvvmsampleappication.data.WEATHER

@BindingAdapter("android:src")
fun setImageLevel(view: ImageView, level: LEVEL) {
    val res =
        when (level) {
            LEVEL.GOOD -> R.drawable.ic_sentiment_very_satisfied_pink_24dp
            LEVEL.BAD -> R.drawable.ic_sentiment_dissatisified_black_24dp
            else -> R.drawable.ic_sentiment_neutral_green_24dp
        }
    view.setImageResource(res)
}

@BindingAdapter("android:src")
fun setImageWeather(view: ImageView, weather: WEATHER) {
    val res =
        when (weather) {
            WEATHER.RAIN -> R.drawable.ic_iconmonstr_umbrella_1
            WEATHER.CLOUD -> R.drawable.ic_cloud_gley_24dp
            WEATHER.SNOW -> R.drawable.ic_grain_gley_24dp
            WEATHER.HOT -> R.drawable.ic_flare_red_24dp
            WEATHER.COLD -> R.drawable.ic_iconmonstr_weather_64
            else -> R.drawable.ic_wb_sunny_yellow_24dp
        }
    view.setImageResource(res)
}

@BindingAdapter("items")
fun setLogItems(view: RecyclerView, logs: List<StepCountLog>?) {
    val adapter = view.adapter as LogRecyclerAdapter? ?: return

    logs?.let {
        adapter.setList(it)
    }
}