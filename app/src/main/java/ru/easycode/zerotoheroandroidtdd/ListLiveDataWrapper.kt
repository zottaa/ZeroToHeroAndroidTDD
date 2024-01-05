package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {
    interface Update {
        fun update(value: List<ItemUi>)
    }

    interface Read {
        fun liveData(): LiveData<List<ItemUi>>
    }

    interface Add {
        fun add(value: ItemUi)
    }

    interface Delete {
        fun delete(item: ItemUi)
    }

    interface All : Update, Read, Add, Delete

    class Base(
        private val liveData: MutableLiveData<List<ItemUi>> = MutableLiveData()
    ) : All {
        override fun update(value: List<ItemUi>) {
            liveData.value = value
        }

        override fun liveData(): LiveData<List<ItemUi>> = liveData

        override fun add(value: ItemUi) {
            val list = liveData.value?.toMutableList() ?: mutableListOf()
            list.add(value)
            update(list)
        }

        override fun delete(item: ItemUi) {
            val list = liveData.value?.toMutableList() ?: mutableListOf()
            list.remove(item)
            update(list)
        }

    }
}