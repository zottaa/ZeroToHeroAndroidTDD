package ru.easycode.zerotoheroandroidtdd.folder.details

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent

interface NoteListLiveDataWrapper {
    interface Create {
        fun create(noteUi: NoteUi)
    }

    interface Read {
        fun liveData(): LiveData<List<NoteUi>>
    }

    interface UpdateListAndRead : Read {
        fun update(notes: List<NoteUi>)
    }

    interface Update {
        fun update(noteId: Long, newText: String)
    }


    interface All : Create, Update, UpdateListAndRead

    class Base(
        private val liveData: SingleLiveEvent<List<NoteUi>> = SingleLiveEvent()
    ) : All {
        override fun create(noteUi: NoteUi) {
            val list = liveData().value?.toMutableList() ?: mutableListOf()
            list.add(noteUi)
            update(list)
        }

        override fun update(noteId: Long, newText: String) {
            val list = liveData().value?.toMutableList() ?: mutableListOf()
            list.find { it.areIdsSame(noteId) }?.let {
                list[list.indexOf(it)] = it.copy(text = newText)
            }
            update(list)
        }

        override fun update(notes: List<NoteUi>) {
            liveData.value = notes
        }

        override fun liveData(): LiveData<List<NoteUi>> = liveData
    }
}