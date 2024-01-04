package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel

interface ViewModelFactory : ClearViewModel, ProvideViewModel {

    class Base(
        private val provideViewModel: ProvideViewModel
    ) : ViewModelFactory {

        private val cache = mutableMapOf<Class<out ViewModel>, ViewModel>()

        override fun viewModel(clasz: Class<out ViewModel>): ViewModel {
            return if (cache.keys.contains(clasz)) {
                cache[clasz]!!
            } else {
                val viewModel = provideViewModel.viewModel(clasz)
                cache[clasz] = viewModel
                viewModel
            }
        }

        override fun clearViewModel(clasz: Class<out ViewModel>) {
            cache.remove(clasz)
        }


    }
}