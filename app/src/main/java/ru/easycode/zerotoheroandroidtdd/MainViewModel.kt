package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData

class MainViewModel(
    private val listLiveDataWrapper: ListLiveDataWrapper
) : ProvideLiveData {


    override fun liveData(): LiveData<List<CharSequence>> {
        return listLiveDataWrapper.liveData()
    }

    fun add(text: String) {
        listLiveDataWrapper.add(text)
    }

    fun save(bundle: BundleWrapper.Save) {
        listLiveDataWrapper.save(bundle)
    }

    fun restore(bundle: BundleWrapper.Restore) {
        listLiveDataWrapper.update(bundle.restore())
    }
}