package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle

interface BundleWrapper {

    interface Restore {
        fun restore(): List<CharSequence>
    }

    interface Save {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Mutable : Restore, Save

    class Base(
        private val bundle: Bundle
    ) : Mutable {
        override fun restore(): List<CharSequence> {
            return bundle.getCharSequenceArrayList(KEY)?.let {
                ArrayList(it)
            } ?: ArrayList()
        }

        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(KEY, list)
        }

        companion object {
            private const val KEY = "listKEY"
        }

    }
}