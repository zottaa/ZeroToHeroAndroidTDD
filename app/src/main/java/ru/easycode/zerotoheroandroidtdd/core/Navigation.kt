package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData

interface Navigation {
    interface Update {
        fun update(screen: Screen)
    }

    interface Read {
        fun liveData(): LiveData<Screen>
    }

    interface Mutable : Update, Read

    class Base(
        private val liveData: SingleLiveEvent<Screen> = SingleLiveEvent()
    ) : Mutable {
        override fun update(screen: Screen) {
            liveData.value = screen
        }

        override fun liveData(): LiveData<Screen> = liveData

    }
}