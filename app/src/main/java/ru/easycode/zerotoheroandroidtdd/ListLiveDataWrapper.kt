package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {
    interface Change {
        fun update(item: ItemUi)
        fun update(list: List<ItemUi>)

        fun delete(item: ItemUi)
    }

    interface Read {
        fun liveData(): LiveData<List<ItemUi>>
    }

    interface Add {
        fun add(value: ItemUi)
    }

    interface All : Read, Add, Change

    class Base(
        private val liveData: MutableLiveData<List<ItemUi>> = MutableLiveData()
    ) : All {
        override fun update(item: ItemUi) {
            val list = liveData.value?.toMutableList() ?: mutableListOf()
            list.find { it.areItemsSame(item) }?.let {
                list[list.indexOf(it)] = item
            }
            update(list)
        }

        override fun update(list: List<ItemUi>) {
            liveData.value = list
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