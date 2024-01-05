package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    private lateinit var factory: ViewModelFactory

    private val clearViewModel: ClearViewModel = object : ClearViewModel {
        override fun clearViewModel(clasz: Class<out ViewModel>) {
            factory.clearViewModel(clasz)
        }
    }

    override fun onCreate() {
        super.onCreate()
        val dao = Core(applicationContext).itemsDao()
        factory = ViewModelFactory.Base(ProvideViewModel.Base(clearViewModel, dao))
    }

    override fun viewModel(clasz: Class<out ViewModel>): ViewModel =
        factory.viewModel(clasz)

}