package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.room.Room

class App : Application(), ProvideViewModel {

    private lateinit var factory: ViewModelFactory
    private lateinit var db: ItemsDataBase

    private val clearViewModel: ClearViewModel = object : ClearViewModel {
        override fun clearViewModel(clasz: Class<out ViewModel>) {
            factory.clearViewModel(clasz)
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.inMemoryDatabaseBuilder(applicationContext, ItemsDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        factory = ViewModelFactory.Base(ProvideViewModel.Base(clearViewModel, db.itemsDao()))
    }

    override fun viewModel(clasz: Class<out ViewModel>): ViewModel =
        factory.viewModel(clasz)
}