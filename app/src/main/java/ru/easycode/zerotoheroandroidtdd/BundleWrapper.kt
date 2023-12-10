package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle


interface BundleWrapper {

    interface Save : BundleWrapper {
        fun save(state: UiState)
    }

    interface Restore : BundleWrapper {
        fun restore(): UiState
    }

    interface Mutable : Save, Restore

    class Base(private val bundle: Bundle) : BundleWrapper.Mutable {
        override fun save(state: UiState) {
            bundle.putSerializable(KEY, state)
        }

        override fun restore(): UiState {
            val state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(KEY, UiState::class.java) as UiState
            } else {
                bundle.getSerializable(KEY) as UiState
            }

            return state
        }

        companion object {
            private const val KEY = "uiStateKEY"
        }
    }

}