package com.example.mvvmsampleappication.view.alert

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.mvvmsampleappication.R

class ErrorDialog: DialogFragment() {
    class Builder() {
        private var message: String? = null
        private var messageResId: Int = R.string.error

        fun message(message: String): Builder {
            this.message = message
            return this
        }

        fun message(resId: Int): Builder {
            this.messageResId = resId
            return this
        }

        fun create(): ErrorDialog {
            val dialog = ErrorDialog()
            dialog.arguments = Bundle().apply {
                if (message != null) {
                    putString(KEY_MESSAGE, message)
                } else {
                    putInt(KEY_RESOURCE_ID, messageResId)
                }
            }
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // AlertDialogで作成する
        val builder = AlertDialog.Builder(requireContext())

        // メッセージの決定
        val message =
            when {
                requireArguments().containsKey(KEY_MESSAGE) -> requireArguments().getString(
                    KEY_MESSAGE
                )
                else -> requireContext().getString(
                    requireArguments().getInt(KEY_RESOURCE_ID)
                )
            }

        // AlertDialogのセットアップ
        builder.setMessage(message)
            .setNeutralButton(R.string.close, null)
        return builder.create()
    }

    companion object {
        const val KEY_MESSAGE = "message"
        const val KEY_RESOURCE_ID = "res_id"
    }
}