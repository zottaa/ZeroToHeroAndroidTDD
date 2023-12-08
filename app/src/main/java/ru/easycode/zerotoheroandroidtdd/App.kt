package ru.easycode.zerotoheroandroidtdd

import android.app.Application

class App : Application() {

    val bundleWrapper = BundleWrapper.Base()
    val viewModel = MainViewModel(LiveDataWrapper.Base(), Repository.Base())

}