package ru.easycode.zerotoheroandroidtdd

import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import java.io.Serializable

interface UiState {

    fun apply(textView: TextView, editText: TextInputEditText)

    object ShowText : UiState {
        override fun apply(textView: TextView, editText: TextInputEditText) {
            textView.text = editText.text
            editText.text?.clear()
        }
    }
}