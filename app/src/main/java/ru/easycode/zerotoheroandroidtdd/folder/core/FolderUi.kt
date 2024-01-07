package ru.easycode.zerotoheroandroidtdd.folder.core

data class FolderUi(
    val id: Long,
    private val title: String,
    private var notesCount: Int
) {
    fun decrement() {
        notesCount--
    }

    fun increment() {
        notesCount++
    }
}
