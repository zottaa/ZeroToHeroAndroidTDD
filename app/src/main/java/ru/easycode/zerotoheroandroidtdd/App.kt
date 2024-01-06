package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    private lateinit var factory: VIewModelFactory

    private val clearViewModel = object : ClearViewModel {
        override fun clearViewModel(clazz: Class<out ViewModel>) {
            factory.clearViewModel(clazz)
        }
    }

    override fun onCreate() {
        super.onCreate()
        val itemsDao = Core(applicationContext).itemsDao()
        factory = VIewModelFactory.Base(ProvideViewModel.Base(clearViewModel, itemsDao))
    }

    override fun viewModel(clasz: Class<out ViewModel>): ViewModel =
        factory.viewModel(clasz)

}