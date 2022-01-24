package com.example.mvvmsampleappication.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsampleappication.R
import com.example.mvvmsampleappication.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.dialog_input.view.*

class InputDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = View.inflate(context, R.layout.dialog_input, null)
        val viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        val builder = AlertDialog.Builder(context)

        builder.setView(view)
            .setNegativeButton(android.R.string.cancel, null)
            .setPositiveButton(R.string.resist) { _, _ ->
                val step: String = view.edit_step.text.toString()
                viewModel.addStepCount(step.toInt())
            }

        return builder.create()
    }
}