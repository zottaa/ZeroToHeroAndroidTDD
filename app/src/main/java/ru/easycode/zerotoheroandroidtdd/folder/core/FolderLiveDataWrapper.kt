package ru.easycode.zerotoheroandroidtdd.folder.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


interface FolderLiveDataWrapper {
    interface Increment {
        fun increment()
    }

    interface Decrement {
        fun decrement()
    }

    interface Update {
        fun update(folder: FolderUi)
    }

    interface ProvideLiveData {
        fun folderLiveData(): LiveData<FolderUi>
    }

    interface Rename : Update, ProvideLiveData {
        fun rename(newName: String)
    }

    interface Read {
        fun folderId(): Long
    }

    interface Mutable : Read, Update, ProvideLiveData

    interface All : Increment, Decrement, Rename, Mutable

    class Base(
        private val liveData: MutableLiveData<FolderUi> = MutableLiveData()
    ) : All {
        override fun increment() {
            liveData.value?.let {
                it.increment()
                update(it)
            }
        }

        override fun decrement() {
            liveData.value?.let {
                it.decrement()
                update(it)
            }
        }

        override fun update(folder: FolderUi) {
            liveData.value = folder
        }

        override fun folderLiveData(): LiveData<FolderUi> = liveData

        override fun rename(newName: String) {
            val folder = liveData.value
            folder?.let { liveData.value = it.copy(title = newName) }
        }

        override fun folderId(): Long =
            liveData.value?.id ?: -1

    }
}