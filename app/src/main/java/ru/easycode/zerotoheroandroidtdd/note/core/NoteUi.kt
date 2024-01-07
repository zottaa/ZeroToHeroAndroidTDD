package ru.easycode.zerotoheroandroidtdd.note.core

data class NoteUi(
    val id: Long,
    private val text: String,
    private val folderId: Long
) {
    fun areIdsSame(noteId: Long): Boolean = this.id == noteId
}
