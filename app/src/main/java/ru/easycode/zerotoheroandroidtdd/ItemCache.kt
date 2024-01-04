package ru.easycode.zerotoheroandroidtdd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemCache(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "text")
    val text: String
)
