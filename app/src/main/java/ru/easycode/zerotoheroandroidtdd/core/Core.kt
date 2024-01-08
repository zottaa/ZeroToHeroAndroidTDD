package ru.easycode.zerotoheroandroidtdd.core

import android.content.Context
import androidx.room.Room

interface Core {

    fun notesDao(): NotesDao

    fun foldersDao(): FoldersDao

    class Base(context: Context) : Core {
        private val db = Room.databaseBuilder(context, AppDataBase::class.java, "folders")
            .build()

        override fun notesDao(): NotesDao = db.notesDao()

        override fun foldersDao(): FoldersDao = db.foldersDao()

    }
}