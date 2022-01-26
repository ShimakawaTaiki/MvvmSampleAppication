package com.example.mvvmsampleappication.viewmodel

import androidx.annotation.UiThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleappication.data.StepCountLog

class MainViewModel: ViewModel() {
    val stepCountList = MutableLiveData<MutableList<StepCountLog>>()

    init {
        stepCountList.value = mutableListOf()
    }

    @UiThread
    fun addStepCount(stepLog: StepCountLog) {
        val list = stepCountList.value ?: return
        list.add(stepLog)
        stepCountList.value = list
    }
}