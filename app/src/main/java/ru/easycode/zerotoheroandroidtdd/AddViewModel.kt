package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddViewModel(
    private val navigation: Navigation.Update,
    private val repository: Repository.Add,
    private val liveDataWrapper: ListLiveDataWrapper.Add,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : ViewModel() {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun add(value: String) {
        viewModelScope.launch(dispatcherMain) {
            repository.add(value)
            withContext(dispatcher) {
                liveDataWrapper.add(value)
            }
        }
        comeback()
    }

    fun comeback() {
        clear.clearViewModel(this::class.java)
        navigation.update(Screen.Pop)
    }
}