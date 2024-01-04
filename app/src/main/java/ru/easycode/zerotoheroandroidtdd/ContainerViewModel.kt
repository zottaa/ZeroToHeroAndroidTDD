package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ContainerViewModel(
    private val navigation: Navigation.Mutable
) : ViewModel(), Navigation.Read {

    fun init(firstRun: Boolean) {
        if (firstRun) {
            navigation.update(MainScreen)
        }
    }
    override fun liveData(): LiveData<Screen> = navigation.liveData()


}