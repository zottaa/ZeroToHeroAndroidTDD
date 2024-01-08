package ru.easycode.zerotoheroandroidtdd.folder.core

import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.R

data class FolderUi(
    val id: Long,
    private val title: String,
    private var notesCount: Int
) {
    fun showTitle(textView: TextView) {
        textView.text = title
    }

    fun showNotesCount(textView: TextView) {
        val formattedString = textView.context.getString(R.string.notes_count, notesCount)
        textView.text = formattedString
    }

    fun decrement() {
        notesCount--
    }

    fun increment() {
        notesCount++
    }
}
