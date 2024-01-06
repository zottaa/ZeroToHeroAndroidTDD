package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers

interface ProvideViewModel {

    fun viewModel(clasz: Class<out ViewModel>): ViewModel

    class Base(
        private val clearViewModel: ClearViewModel,
        dataSource: ItemsDao
    ) : ProvideViewModel {

        private val repository = Repository.Base(dataSource, Now.Base())
        private val sharedLiveData = ListLiveDataWrapper.Base()

        override fun viewModel(clasz: Class<out ViewModel>): ViewModel {
            return when (clasz) {
                MainViewModel::class.java -> MainViewModel(
                    repository,
                    sharedLiveData,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                AddViewModel::class.java -> AddViewModel(
                    repository,
                    sharedLiveData,
                    clearViewModel,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                DetailsViewModel::class.java -> DetailsViewModel(
                    sharedLiveData,
                    repository,
                    clearViewModel,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                else -> {
                    throw IllegalStateException("unknown view model $clasz")
                }
            }
        }
    }
}