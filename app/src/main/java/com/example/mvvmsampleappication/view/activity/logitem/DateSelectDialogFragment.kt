package com.example.mvvmsampleappication.view.activity.logitem

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.CalendarView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsampleappication.viewmodel.LogItemViewModel
import java.util.*

/**
 * 日付選択ダイアログ
 */
class DateSelectDialogFragment : DialogFragment() {
    // CalendarViewで選択している日付の保存
    private val selectDate = Calendar.getInstance()

    // CalenderView
    lateinit var calendarView: CalendarView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(LogItemViewModel::class.java)

        // AlertDialogで作成する。
        val builder = AlertDialog.Builder(requireContext())

        // CalendarViewのインスタンス生成
        calendarView = CalendarView(requireContext())
        // 初期値（今日）をセット
        calendarView.date = selectDate.timeInMillis

        // 選択している日付が変更されたときのイベントリスナー
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectDate.set(year, month, dayOfMonth)
        }

        builder.setView(calendarView)
            .setNegativeButton(android.R.string.cancel, null)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                // ポジティブボタンクリックで最後に選択した日付をviewModelにセット
                viewModel.dataSelected(selectDate)
            }

        return builder.create()
    }
}