package ru.easycode.zerotoheroandroidtdd.folder.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderUi
import ru.easycode.zerotoheroandroidtdd.folder.core.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.folder.details.FolderDetailsScreen
import ru.easycode.zerotoheroandroidtdd.folder.details.FolderDetailsViewModel
import ru.easycode.zerotoheroandroidtdd.folder.list.FoldersListScreen
import ru.easycode.zerotoheroandroidtdd.main.Navigation

class EditFolderViewModel(
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Rename,
    private val repository: FoldersRepository.Edit,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : ViewModel(), FolderLiveDataWrapper.ProvideLiveData {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun init(folderId: Long) {
        viewModelScope.launch(dispatcher) {
            val folder = repository.folder(folderId)
            withContext(dispatcherMain) {
                folderLiveDataWrapper.update(FolderUi(folder.id, folder.title, folder.notesCount))
            }
        }
    }

    fun renameFolder(folderId: Long, newName: String) {
        viewModelScope.launch(dispatcher) {
            repository.rename(folderId, newName)
            withContext(dispatcherMain) {
                folderLiveDataWrapper.rename(newName)
                comeback()
            }
        }
    }

    fun deleteFolder(folderId: Long) {
        viewModelScope.launch(dispatcher) {
            repository.delete(folderId)
            withContext(dispatcherMain) {
                comeback(isDelete = true)
            }
        }
    }

    fun comeback(isDelete: Boolean = false) {
        if (isDelete) {
            clear.clear(this::class.java, FolderDetailsViewModel::class.java)
            navigation.update(FoldersListScreen)
        } else {
            clear.clear(this::class.java)
            navigation.update(FolderDetailsScreen)
        }
    }

    override fun folderLiveData(): LiveData<FolderUi> = folderLiveDataWrapper.folderLiveData()
}