package com.example.mvvmsampleappication.viewmodel

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleappication.data.StepCountLog
import java.util.*

class LogItemViewModel : ViewModel() {
    // StepCountLogデータ（Activityに戻す用）
    private val _stepCountLog = MutableLiveData<StepCountLog>()
    val stepCountLog = _stepCountLog as LiveData<StepCountLog>

    // 選択日付
    private val _selectDate = MutableLiveData<Calendar>()
    val selectDate = _selectDate as LiveData<Calendar>

    /**
     * StepCountLogデータのセット（すべてのデータの登録完了）
     */
    @UiThread
    fun changeLog(data: StepCountLog) {
        _stepCountLog.value = data
    }

    /**
     * 選択した日付のセット
     */
    @UiThread
    fun dataSelected(selectDate: Calendar) {
        _selectDate.value = selectDate
    }
}