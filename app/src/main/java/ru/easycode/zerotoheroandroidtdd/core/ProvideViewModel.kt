package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.core.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.folder.create.CreateFolderViewModel
import ru.easycode.zerotoheroandroidtdd.folder.details.FolderDetailsViewModel
import ru.easycode.zerotoheroandroidtdd.folder.edit.EditFolderViewModel
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.note.core.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.note.core.NoteLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.note.core.NotesRepository
import ru.easycode.zerotoheroandroidtdd.note.create.CreateNoteViewModel
import ru.easycode.zerotoheroandroidtdd.note.edit.EditNoteViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(clasz: Class<T>): T

    class Factory(
        private val provideViewModel: ProvideViewModel
    ) : ClearViewModels, ProvideViewModel {

        private val cache = mutableMapOf<Class<out ViewModel>, ViewModel>()
        override fun clear(vararg viewModelClasses: Class<out ViewModel>) {
            viewModelClasses.forEach {
                cache.remove(it)
            }
        }

        override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
            if (!cache.containsKey(clasz)) {
                cache[clasz] = provideViewModel.viewModel(clasz)

            }
            return cache[clasz] as T
        }
    }

    class Base(
        private val clear: ClearViewModels,
        notesDao: NotesDao,
        foldersDao: FoldersDao
    ) : ProvideViewModel {

        private val now = Now.Base()

        private val navigation = Navigation.Base()
        private val sharedFolderLiveDataWrapper = FolderLiveDataWrapper.Base()
        private val sharedNoteLiveDataWrapper = NoteLiveDataWrapper.Base()
        private val sharedNoteListLiveDataWrapper = NoteListLiveDataWrapper.Base()
        private val sharedFolderListLiveDataWrapper = FolderListLiveDataWrapper.Base()
        private val notesRepository = NotesRepository.Base(now, notesDao)
        private val foldersRepository = FoldersRepository.Base(now, foldersDao, notesDao)

        override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
            return when (clasz) {
                MainViewModel::class.java -> MainViewModel(
                    navigation
                )

                EditNoteViewModel::class.java -> EditNoteViewModel(
                    sharedFolderLiveDataWrapper,
                    sharedNoteLiveDataWrapper,
                    sharedNoteListLiveDataWrapper,
                    notesRepository,
                    navigation,
                    clear,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                CreateNoteViewModel::class.java -> CreateNoteViewModel(
                    sharedFolderLiveDataWrapper,
                    sharedNoteListLiveDataWrapper,
                    notesRepository,
                    navigation,
                    clear,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                FolderListViewModel::class.java -> FolderListViewModel(
                    foldersRepository,
                    sharedFolderListLiveDataWrapper,
                    sharedFolderLiveDataWrapper,
                    navigation,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                EditFolderViewModel::class.java -> EditFolderViewModel(
                    sharedFolderLiveDataWrapper,
                    foldersRepository,
                    navigation,
                    clear,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                FolderDetailsViewModel::class.java -> FolderDetailsViewModel(
                    notesRepository,
                    sharedNoteListLiveDataWrapper,
                    sharedFolderLiveDataWrapper,
                    navigation,
                    clear,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                CreateFolderViewModel::class.java -> CreateFolderViewModel(
                    foldersRepository,
                    sharedFolderListLiveDataWrapper,
                    navigation,
                    clear,
                    Dispatchers.IO,
                    Dispatchers.Main
                )

                else -> {
                    throw IllegalStateException("unknown view model $clasz")
                }
            } as T
        }

    }
}