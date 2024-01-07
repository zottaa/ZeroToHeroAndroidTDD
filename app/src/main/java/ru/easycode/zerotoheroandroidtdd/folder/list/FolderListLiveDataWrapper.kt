package ru.easycode.zerotoheroandroidtdd.folder.list

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderUi


interface FolderListLiveDataWrapper {
    interface UpdateListAndRead {
        fun update(list: List<FolderUi>)

        fun liveData(): LiveData<List<FolderUi>>
    }

    interface Create {
        fun create(folderUi: FolderUi)
    }

    interface All : UpdateListAndRead, Create

    class Base(
        private val liveData: SingleLiveEvent<List<FolderUi>> = SingleLiveEvent()
    ) : All {
        override fun update(list: List<FolderUi>) {
            liveData.value = list
        }

        override fun liveData(): LiveData<List<FolderUi>> = liveData


        override fun create(folderUi: FolderUi) {
            val list = liveData.value?.toMutableList() ?: mutableListOf()
            list.add(folderUi)
            update(list)
        }

    }
}