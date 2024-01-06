package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel

interface VIewModelFactory : ProvideViewModel, ClearViewModel {
    class Base(
        private val provideViewModel: ProvideViewModel
    ) : VIewModelFactory {

        private val cache = mutableMapOf<Class<out ViewModel>, ViewModel>()
        override fun viewModel(clasz: Class<out ViewModel>): ViewModel {
            return if (cache.containsKey(clasz)) {
                cache[clasz]!!
            } else {
                val viewModel = provideViewModel.viewModel(clasz)
                cache[clasz] = viewModel
                viewModel
            }
        }

        override fun clearViewModel(clazz: Class<out ViewModel>) {
            cache.remove(clazz)
        }

    }
}