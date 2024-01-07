package ru.easycode.zerotoheroandroidtdd.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes"
)
data class NoteCache(
    @PrimaryKey
    @ColumnInfo(name = "note_id")
    val id: Long,
    @ColumnInfo(name = "note_text")
    val text: String,
    @ColumnInfo(name = "folder_id")
    val folderId: Long
)
