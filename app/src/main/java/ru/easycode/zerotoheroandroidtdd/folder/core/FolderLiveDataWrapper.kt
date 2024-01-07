package ru.easycode.zerotoheroandroidtdd.folder.core

import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent


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

    interface Rename {
        fun rename(newName: String)
    }

    interface Read {
        fun folderId(): Long
    }

    interface Mutable : Read, Update

    interface All : Increment, Decrement, Rename, Mutable

    class Base(
        private val liveData: SingleLiveEvent<FolderUi> = SingleLiveEvent()
    ) : All {
        override fun increment() {
            liveData.value?.increment()
        }

        override fun decrement() {
            liveData.value?.decrement()
        }

        override fun update(folder: FolderUi) {
            liveData.value = folder
        }

        override fun rename(newName: String) {
            val folder = liveData.value
            folder?.let { liveData.value = it.copy(title = newName) }
        }

        override fun folderId(): Long =
            liveData.value?.id ?: -1

    }
}