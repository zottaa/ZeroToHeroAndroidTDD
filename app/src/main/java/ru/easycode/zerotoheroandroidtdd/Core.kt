package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import androidx.room.Room

class Core(
    context: Context
) {
    private val db = Room.databaseBuilder(
        context,
        ItemsDataBase::class.java,
        name = "items_database"
    ).build()

    fun itemsDao(): ItemsDao = db.itemsDao()

}