package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData

interface LiveDataWrapper : ProvideLiveData {

    fun update(value: UiState)

    fun save(bundleWrapper: BundleWrapper.Save)

    class Base(private val liveData: SingleLiveEvent<UiState> = SingleLiveEvent()) : LiveDataWrapper {
        override fun liveData(): LiveData<UiState> {
            return liveData
        }

        override fun update(value: UiState) {
            liveData.value = value
        }

        override fun save(bundleWrapper: BundleWrapper.Save) {
            bundleWrapper.save(liveData.value!!)
        }

    }
}

interface ProvideLiveData {
    fun liveData() : LiveData<UiState>
}