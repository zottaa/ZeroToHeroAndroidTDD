package ru.easycode.zerotoheroandroidtdd.note.core

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent

interface NoteLiveDataWrapper {

    interface Update {
        fun update(value: String)
    }

    interface Read {
        fun liveData(): LiveData<String>
    }

    interface Mutable : Read, Update
    class Base(
        private val liveData: SingleLiveEvent<String> = SingleLiveEvent()
    ) : Mutable {
        override fun liveData(): LiveData<String> = liveData

        override fun update(value: String) {
            liveData.value = value
        }

    }
}