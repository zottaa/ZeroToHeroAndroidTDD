package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(clasz: Class<T>): T

    class Factory(
        private val provideViewModel: ProvideViewModel
    ) : ClearViewModels, ProvideViewModel {

        private val cache = mutableMapOf<Class<out ViewModel>, ViewModel>()
        override fun clear(vararg viewModelClasses: Class<out ViewModel>) {
            viewModelClasses.forEach {
                cache.remove(it)
            }
        }

        override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
            if (!cache.containsKey(clasz)) {
                cache[clasz] = provideViewModel.viewModel(clasz)

            }
            return cache[clasz] as T
        }
    }

    class Base : ProvideViewModel {

        private val navigation = Navigation.Base()
        override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
            return when (clasz) {
                MainViewModel::class.java -> MainViewModel(
                    navigation
                ) as T


                else -> {
                    throw IllegalStateException("unknown view model $clasz")
                }
            }
        }

    }
}