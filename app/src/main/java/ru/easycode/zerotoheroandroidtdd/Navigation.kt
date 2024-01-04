package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData

interface Navigation {
    interface Read {
        fun liveData() : LiveData<Screen>
    }

    interface Update {
        fun update(value: Screen)
    }

    interface Mutable : Read, Update

    class Base(
        private val liveData: SingleLiveEvent<Screen> = SingleLiveEvent()
    ) : Navigation.Mutable {
        override fun liveData(): LiveData<Screen> = liveData

        override fun update(value: Screen) {
            liveData.value = value
        }

    }
}