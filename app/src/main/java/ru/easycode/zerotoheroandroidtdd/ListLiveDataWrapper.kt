package ru.easycode.zerotoheroandroidtdd

import android.util.Log
import androidx.lifecycle.LiveData

interface ListLiveDataWrapper : ProvideLiveData {

    fun add(new: CharSequence)

    fun save(bundle: BundleWrapper.Save)

    fun update(list: List<CharSequence>)

    class Base(private val liveData: SingleLiveEvent<List<CharSequence>> = SingleLiveEvent()) :
        ListLiveDataWrapper {

        override fun add(new: CharSequence) {
            val list = ArrayList(liveData.value ?: emptyList())
            list.add(new)
            liveData.value = list
        }

        override fun save(bundle: BundleWrapper.Save) {
            val arrayList = ArrayList<CharSequence>(liveData.value ?: emptyList())
            bundle.save(arrayList)
        }

        override fun update(list: List<CharSequence>) {
            liveData.value = list
        }

        override fun liveData(): LiveData<List<CharSequence>> {
            return liveData
        }

    }
}

interface ProvideLiveData {
    fun liveData(): LiveData<List<CharSequence>>
}
