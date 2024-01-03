package ru.easycode.zerotoheroandroidtdd.list

import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper

interface ListLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<List<CharSequence>>
    interface Add {
        fun add(source: CharSequence)
    }

    interface Update : LiveDataWrapper.Update<List<CharSequence>>
    interface Mutable : Update, Read, Save

    interface All : Mutable, Add

    class Base : LiveDataWrapper.Abstract<List<CharSequence>>(), All {
        override fun add(source: CharSequence) {
            val newList = liveData.value?.toMutableList() ?: mutableListOf()
            newList.add(source)
            update(newList)
        }

        override fun save(bundleWrapper: BundleWrapper.Save) {
            bundleWrapper.save(liveData.value?.let {
                ArrayList(it)
            } ?: ArrayList())
        }
    }
}

interface Save {
    fun save(bundleWrapper: BundleWrapper.Save)
}

