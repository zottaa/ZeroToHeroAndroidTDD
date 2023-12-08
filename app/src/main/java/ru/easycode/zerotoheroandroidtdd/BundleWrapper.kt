package ru.easycode.zerotoheroandroidtdd

interface BundleWrapper : Save<UiState>, Restore<UiState> {

    interface Save : BundleWrapper

    interface Restore : BundleWrapper

    interface Mutable :
        Save,
        Restore

    class Base : Mutable {

        private lateinit var uiState: UiState

        override fun save(uiState: UiState) {
            this.uiState = uiState
        }

        override fun restore(): UiState {
            return uiState
        }

    }
}

interface Save<T> {
    fun save(uiState: T)
}

interface Restore<T> {
    fun restore(): T
}