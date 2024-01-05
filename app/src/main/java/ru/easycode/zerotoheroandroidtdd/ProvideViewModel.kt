package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers

interface ProvideViewModel {

    fun viewModel(clasz: Class<out ViewModel>): ViewModel

    class Base(
        private val clearViewModel: ClearViewModel,
        dataSource: ItemsDao
    ) : ProvideViewModel {

        private val sharedLivedata: ListLiveDataWrapper.All = ListLiveDataWrapper.Base()
        private val sharedRepository = Repository.Base(dataSource, Now.Base())

        override fun viewModel(clasz: Class<out ViewModel>): ViewModel {
            return when (clasz) {
                MainViewModel::class.java -> MainViewModel(
                    sharedRepository,
                    sharedLivedata,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                DeleteViewModel::class.java -> DeleteViewModel(
                    sharedLivedata,
                    sharedRepository,
                    clearViewModel,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                AddViewModel::class.java -> AddViewModel(
                    sharedRepository,
                    sharedLivedata,
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