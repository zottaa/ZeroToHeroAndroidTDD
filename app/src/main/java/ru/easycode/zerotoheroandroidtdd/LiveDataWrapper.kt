package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData

interface LiveDataWrapper : ProvideLiveData {

    fun save(bundleWrapper: BundleWrapper.Save)

    fun update(value: UiState)



    class Base(private val liveData: SingleLiveEvent<UiState> = SingleLiveEvent()) : LiveDataWrapper {
        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(it) }
        }

        override fun update(value: UiState) {
            liveData.value = value
        }

        override fun liveData(): LiveData<UiState> {
            return liveData
        }

    }
}

interface ProvideLiveData {

    fun liveData() : LiveData<UiState>

}