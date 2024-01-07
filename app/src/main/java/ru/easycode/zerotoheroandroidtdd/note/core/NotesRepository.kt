package ru.easycode.zerotoheroandroidtdd.note.core

import ru.easycode.zerotoheroandroidtdd.core.NoteCache
import ru.easycode.zerotoheroandroidtdd.core.NotesDao
import ru.easycode.zerotoheroandroidtdd.core.Now

interface NotesRepository {

    interface Create {
        suspend fun createNote(folderId: Long, text: String): Long
    }

    interface ReadList {
        suspend fun noteList(folderId: Long): List<MyNote>
    }

    interface Edit {
        suspend fun deleteNote(noteId: Long)

        suspend fun note(noteId: Long): MyNote

        suspend fun renameNote(noteId: Long, newText: String)
    }

    interface All : Create, ReadList, Edit

    class Base(
        private val now: Now,
        private val dao: NotesDao
    ) : All {
        override suspend fun createNote(folderId: Long, text: String): Long {
            val id = now.timeInMillis()
            dao.insert(NoteCache(id, text, folderId))
            return id
        }

        override suspend fun noteList(folderId: Long): List<MyNote> =
            dao.notes(folderId).map { MyNote(it.id, it.text, it.folderId) }

        override suspend fun deleteNote(noteId: Long) {
            dao.delete(noteId)
        }

        override suspend fun note(noteId: Long): MyNote =
            dao.note(noteId).let { MyNote(it.id, it.text, it.folderId) }


        override suspend fun renameNote(noteId: Long, newText: String) {
            val note = dao.note(noteId)
            val newNote = note.copy(text = newText)
            dao.insert(newNote)
        }
    }
}

data class MyNote(
    val id: Long,
    val title: String,
    val folderId: Long
)