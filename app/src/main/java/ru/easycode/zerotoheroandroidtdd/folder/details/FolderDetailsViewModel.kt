package ru.easycode.zerotoheroandroidtdd.folder.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.Navigation
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.edit.EditFolderScreen
import ru.easycode.zerotoheroandroidtdd.folder.list.FoldersListScreen
import ru.easycode.zerotoheroandroidtdd.note.core.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.note.core.NoteUi
import ru.easycode.zerotoheroandroidtdd.note.core.NotesRepository
import ru.easycode.zerotoheroandroidtdd.note.create.CreateNoteScreen
import ru.easycode.zerotoheroandroidtdd.note.edit.EditNoteScreen

class FolderDetailsViewModel(
    private val noteListRepository: NotesRepository.ReadList,
    private val liveDataWrapper: NoteListLiveDataWrapper.UpdateListAndRead,
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Mutable,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : ViewModel() {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun init() {
        viewModelScope.launch(dispatcher) {
            val notes = noteListRepository.noteList(folderLiveDataWrapper.folderId())
                .map { NoteUi(it.id, it.title, it.folderId) }
            withContext(dispatcherMain) {
                liveDataWrapper.update(notes)
            }
        }
    }

    fun createNote() {
        navigation.update(CreateNoteScreen(folderLiveDataWrapper.folderId()))
    }

    fun editNote(noteUi: NoteUi) {
        navigation.update(EditNoteScreen(noteUi.id))
    }

    fun editFolder() {
        navigation.update(EditFolderScreen(folderLiveDataWrapper.folderId()))
    }

    fun comeback() {
        clear.clear(this::class.java)
        navigation.update(FoldersListScreen)
    }
}