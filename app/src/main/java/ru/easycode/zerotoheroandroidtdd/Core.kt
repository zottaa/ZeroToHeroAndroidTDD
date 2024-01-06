package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import androidx.room.Room

class Core(applicationContext: Context) {
    private val db =
        Room.databaseBuilder(
            applicationContext,
            ItemsDataBase::class.java,
            name = "Items"
        ).build()

    fun itemsDao(): ItemsDao = db.itemsDao()

}
