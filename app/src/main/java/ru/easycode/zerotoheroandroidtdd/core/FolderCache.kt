package ru.easycode.zerotoheroandroidtdd.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folders")
data class FolderCache(
    @PrimaryKey
    @ColumnInfo(name = "folder_id")
    val id: Long,
    @ColumnInfo(name = "folder_text")
    val text: String
)
