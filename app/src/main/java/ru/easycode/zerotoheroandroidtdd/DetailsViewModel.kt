package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(
    private val changeLiveDataWrapper: ListLiveDataWrapper.Change,
    private val repository: Repository.Change,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : ViewModel() {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    val liveData: LiveData<String>
        get() = _liveData
    private val _liveData: MutableLiveData<String> = MutableLiveData()

    fun init(itemId: Long) {
        viewModelScope.launch(dispatcher) {
            val itemText = repository.item(itemId).text
            withContext(dispatcherMain) {
                _liveData.value = itemText
            }
        }
    }

    fun delete(itemId: Long) {
        viewModelScope.launch(dispatcher) {
            val itemUi = repository.item(itemId).let { ItemUi(it.id, it.text) }
            repository.delete(itemId)
            withContext(dispatcherMain) {
                changeLiveDataWrapper.delete(itemUi)
                comeback()
            }
        }
    }

    fun update(itemId: Long, newText: String) {
        viewModelScope.launch(dispatcher) {
            repository.update(itemId, newText)
            withContext(dispatcherMain) {
                changeLiveDataWrapper.update(ItemUi(itemId, newText))
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clearViewModel(this.javaClass)
    }
}