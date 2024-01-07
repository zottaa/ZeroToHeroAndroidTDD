package ru.easycode.zerotoheroandroidtdd.core

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteCache)

    @Query("SELECT * FROM notes where folder_id = :folderId")
    suspend fun notes(folderId: Long): List<NoteCache>

    @Query("SELECT * FROM notes where note_id = :noteId")
    suspend fun note(noteId: Long): NoteCache

    @Query("DELETE FROM notes WHERE note_id = :noteId")
    suspend fun delete(noteId: Long)

    @Query("DELETE FROM notes WHERE folder_id = :folderId")
    suspend fun deleteByFolderId(folderId: Long)
}