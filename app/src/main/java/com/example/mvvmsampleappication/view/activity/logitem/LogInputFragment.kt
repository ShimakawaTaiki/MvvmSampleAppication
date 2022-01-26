package com.example.mvvmsampleappication.view.activity.logitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleappication.R
import com.example.mvvmsampleappication.data.LEVEL
import com.example.mvvmsampleappication.data.StepCountLog
import com.example.mvvmsampleappication.data.WEATHER
import com.example.mvvmsampleappication.getDateStringYMD
import com.example.mvvmsampleappication.viewmodel.LogItemViewModel
import kotlinx.android.synthetic.main.fragment_log_input.*
import kotlinx.android.synthetic.main.fragment_log_input.view.*
import java.util.*

/**
 * データ入力フラグメント
 */
class LogInputFragment : Fragment() {
    lateinit var viewModel: LogItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contentView = inflater.inflate(R.layout.fragment_log_input, container,false)

        // RadioButtonの初期状態のセット
        contentView.radio_group.check(R.id.radio_normal)

        // 日付の初期状態のセット
        contentView.date_text.text = Calendar.getInstance().getDateStringYMD()

        // 登録ボタンのクリックリスナー
        contentView.button_resist.setOnClickListener {
            val dateText = date_text.text.toString()
            val stepCount = edit_step.text.toString().toInt()
            val level = levelFromRadioId(radio_group.checkedRadioButtonId)
            val weather = weatherFromSpinner(spinner_weather.selectedItemPosition)
            val stepCountLog = StepCountLog(dateText, stepCount, level, weather)

            viewModel.changeLog(stepCountLog)
        }

        // 日付ボタンのクリックリスナー
        contentView.date_button.setOnClickListener {
            val manager = activity?.supportFragmentManager ?: return@setOnClickListener
            DateSelectDialogFragment().show(manager, DATE_SELECT_TAG)
        }

        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider.NewInstanceFactory().create(LogItemViewModel::class.java)

        // 日付の選択を監視
        viewModel.selectDate.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            date_text.text = it.getDateStringYMD()
        })
    }

    /**
     * RadioButtonの選択状態からenum値を取り出す。
     */
    private fun levelFromRadioId(checkedRadioButtonId: Int): LEVEL {
        return when(checkedRadioButtonId) {
            R.id.radio_good -> LEVEL.GOOD
            R.id.radio_bad -> LEVEL.BAD
            else -> LEVEL.NORMAL
        }
    }

    /**
     * Spinnerの選択状態からenum値を取り出す。
     */
    private fun weatherFromSpinner(selectedItemPosition: Int): WEATHER {
        return WEATHER.values()[selectedItemPosition]
    }

    companion object {
        const val DATE_SELECT_TAG = "date_select"
        fun newInstance(): LogInputFragment {
            val fragment = LogInputFragment()
            return fragment
        }
    }
}