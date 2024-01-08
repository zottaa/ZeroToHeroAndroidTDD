package ru.easycode.zerotoheroandroidtdd.folder.details

import android.widget.TextView

data class NoteUi(
    val id: Long,
    private val text: String,
    private val folderId: Long
) {
    fun areIdsSame(noteId: Long): Boolean = this.id == noteId

    fun showTitle(textView: TextView) {
        textView.text = text
    }

    fun edit(editNote: EditNote) {
        editNote.edit(this)
    }
}
