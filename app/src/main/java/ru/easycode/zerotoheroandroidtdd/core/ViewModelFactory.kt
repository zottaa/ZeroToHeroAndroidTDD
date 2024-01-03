package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ViewModelFactory : ClearViewModel, ProvideViewModel {

    class Base(
        private val provideViewModel: ProvideViewModel
    ) : ViewModelFactory {

        private val cache = mutableMapOf<Class<out ViewModel>, ViewModel>()

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return if (cache.keys.contains(viewModelClass)) {
                cache[viewModelClass] as T
            } else {
                val viewModel = provideViewModel.viewModel(viewModelClass)
                cache[viewModelClass] = viewModel
                viewModel
            }
        }

        override fun clear(viewModelClass: Class<out ViewModel>) {
            cache.remove(viewModelClass)
        }
    }
}