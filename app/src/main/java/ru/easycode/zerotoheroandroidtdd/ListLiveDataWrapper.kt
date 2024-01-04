package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {
    interface Update {
        fun update(value: List<String>)
    }

    interface Read {
        fun liveData(): LiveData<List<String>>
    }

    interface Add {
        fun add(value: String)
    }

    interface Mutable : Update, Read

    interface All : Mutable, Add

    class Base(
        private val liveData: MutableLiveData<List<String>> = MutableLiveData()
    ) : All {
        override fun update(value: List<String>) {
            liveData.value = value
        }

        override fun liveData(): LiveData<List<String>> = liveData

        override fun add(value: String) {
            val list = liveData.value?.toMutableList() ?: mutableListOf()
            list.add(value)
            update(list)
        }
    }
}