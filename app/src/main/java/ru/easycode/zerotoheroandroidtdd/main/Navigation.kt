package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.core.Screen
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent

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