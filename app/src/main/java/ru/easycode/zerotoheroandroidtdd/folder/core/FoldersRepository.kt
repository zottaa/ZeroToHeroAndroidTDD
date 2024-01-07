package ru.easycode.zerotoheroandroidtdd.folder.core

import ru.easycode.zerotoheroandroidtdd.core.FolderCache
import ru.easycode.zerotoheroandroidtdd.core.FoldersDao
import ru.easycode.zerotoheroandroidtdd.core.NotesDao
import ru.easycode.zerotoheroandroidtdd.core.Now

interface FoldersRepository {

    interface ReadList {
        suspend fun folders(): List<Folder>
    }

    interface Create {
        suspend fun createFolder(name: String): Long
    }

    interface Edit {
        suspend fun delete(folderId: Long)

        suspend fun rename(folderId: Long, newName: String)
    }

    interface All : ReadList, Create, Edit

    class Base(
        private val now: Now,
        private val foldersDao: FoldersDao,
        private val notesDao: NotesDao
    ) : All {
        override suspend fun folders(): List<Folder> =
            foldersDao.folders().map { Folder(it.id, it.text, notesDao.notes(it.id).size) }


        override suspend fun createFolder(name: String): Long {
            val id = now.timeInMillis()
            foldersDao.insert(FolderCache(id, name))
            return id
        }

        override suspend fun delete(folderId: Long) {
            foldersDao.delete(folderId)
            notesDao.deleteByFolderId(folderId)
        }

        override suspend fun rename(folderId: Long, newName: String) {
            foldersDao.insert(FolderCache(folderId, newName))
        }

    }
}

data class Folder(val id: Long, val title: String, val notesCount: Int)