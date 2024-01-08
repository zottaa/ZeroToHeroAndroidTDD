package ru.easycode.zerotoheroandroidtdd.folder.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderUi
import ru.easycode.zerotoheroandroidtdd.folder.core.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.list.FoldersListScreen
import ru.easycode.zerotoheroandroidtdd.main.Navigation

class CreateFolderViewModel(
    private val repository: FoldersRepository.Create,
    private val liveDataWrapper: FolderListLiveDataWrapper.Create,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : ViewModel() {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun createFolder(name: String) {
        viewModelScope.launch(dispatcher) {
            val id = repository.createFolder(name)
            withContext(dispatcherMain) {
                liveDataWrapper.create(FolderUi(id, name, DEFAULT_NOTES_COUNT))
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clear(this::class.java)
        navigation.update(FoldersListScreen)
    }

    companion object {
        private const val DEFAULT_NOTES_COUNT = 0
    }
}