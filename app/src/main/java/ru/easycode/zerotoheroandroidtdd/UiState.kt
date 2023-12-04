package ru.easycode.zerotoheroandroidtdd

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    fun apply(button: Button, progressBar: ProgressBar, textView: TextView)

    object ShowProgress : UiState {
        override fun apply(button: Button, progressBar: ProgressBar, textView: TextView) {
            progressBar.visibility = View.VISIBLE
            button.isEnabled = false
        }
    }

    object ShowData : UiState {
        override fun apply(button: Button, progressBar: ProgressBar, textView: TextView) {
            button.isEnabled = true
            progressBar.visibility = View.GONE
            textView.visibility = View.VISIBLE
        }
    }
}