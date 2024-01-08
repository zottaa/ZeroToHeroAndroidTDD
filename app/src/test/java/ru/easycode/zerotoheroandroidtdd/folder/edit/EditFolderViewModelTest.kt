package ru.easycode.zerotoheroandroidtdd.folder.edit

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.core.FakeClear
import ru.easycode.zerotoheroandroidtdd.core.FakeClear.Companion.CLEAR
import ru.easycode.zerotoheroandroidtdd.core.FakeNavigation
import ru.easycode.zerotoheroandroidtdd.core.FakeNavigation.Companion.NAVIGATE
import ru.easycode.zerotoheroandroidtdd.core.Order
import ru.easycode.zerotoheroandroidtdd.folder.core.Folder
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderUi
import ru.easycode.zerotoheroandroidtdd.folder.core.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.folder.details.FolderDetailsScreen
import ru.easycode.zerotoheroandroidtdd.folder.details.FolderDetailsViewModel
import ru.easycode.zerotoheroandroidtdd.folder.list.FoldersListScreen

class EditFolderViewModelTest {

    private lateinit var order: Order
    private lateinit var folderLiveDataWrapper: FakeRenameFolderLiveDataWrapper
    private lateinit var repository: FakeEditFolderRepository
    private lateinit var navigation: FakeNavigation.Update
    private lateinit var clear: FakeClear
    private lateinit var viewModel: EditFolderViewModel

    @Before
    fun setup() {
        order = Order()
        folderLiveDataWrapper = FakeRenameFolderLiveDataWrapper.Base(order)
        repository = FakeEditFolderRepository.Base(order)
        navigation = FakeNavigation.Base(order)
        clear = FakeClear.Base(order)
        viewModel = EditFolderViewModel(
            folderLiveDataWrapper = folderLiveDataWrapper,
            repository = repository,
            navigation = navigation,
            clear = clear,
            dispatcher = Dispatchers.Unconfined,
            dispatcherMain = Dispatchers.Unconfined
        )
    }

    @Test
    fun test_rename() {
        viewModel.renameFolder(folderId = 4L, newName = "new folder name")

        repository.checkRename(4L, "new folder name")
        folderLiveDataWrapper.check("new folder name")
        navigation.checkScreen(FolderDetailsScreen)
        clear.check(listOf(EditFolderViewModel::class.java))
        order.check(listOf(REPOSITORY_RENAME, RENAME_LIVEDATA, CLEAR, NAVIGATE))
    }

    @Test
    fun test_delete() {
        viewModel.deleteFolder(folderId = 4L)

        repository.checkDelete(4L)
        clear.check(listOf(EditFolderViewModel::class.java, FolderDetailsViewModel::class.java))
        navigation.checkScreen(FoldersListScreen)
        order.check(listOf(REPOSITORY_DELETE, CLEAR, NAVIGATE))
    }

    @Test
    fun test_comeback() {
        viewModel.comeback()

        clear.check(listOf(EditFolderViewModel::class.java))
        navigation.checkScreen(FolderDetailsScreen)
        order.check(listOf(CLEAR, NAVIGATE))
    }
}

private const val REPOSITORY_DELETE = "FoldersRepository.Edit#DELETE"
private const val REPOSITORY_RENAME = "FoldersRepository.Edit#RENAME"

private interface FakeEditFolderRepository : FoldersRepository.Edit {

    fun checkDelete(expectedFolderId: Long)

    fun checkRename(expectedFolderId: Long, expectedNewName: String)

    class Base(private val order: Order) : FakeEditFolderRepository {

        private var actualFolderId: Long = -1L
        private var actualRenamedValue: String = ""

        override fun checkDelete(expectedFolderId: Long) {
            assertEquals(expectedFolderId, actualFolderId)
        }

        override fun checkRename(expectedFolderId: Long, expectedNewName: String) {
            assertEquals(expectedFolderId, actualFolderId)
            assertEquals(expectedNewName, actualRenamedValue)
        }

        override suspend fun delete(folderId: Long) {
            order.add(REPOSITORY_DELETE)
            actualFolderId = folderId
        }

        override suspend fun folder(folderId: Long): Folder {
            throw IllegalStateException("not used in test")
        }

        override suspend fun rename(folderId: Long, newName: String) {
            order.add(REPOSITORY_RENAME)
            actualFolderId = folderId
            actualRenamedValue = newName
        }
    }
}

private const val RENAME_LIVEDATA = "FolderLiveDataWrapper.Rename#rename"

private interface FakeRenameFolderLiveDataWrapper : FolderLiveDataWrapper.Rename {

    fun check(expected: String)

    class Base(private val order: Order) : FakeRenameFolderLiveDataWrapper {

        private var actual = ""

        override fun check(expected: String) {
            assertEquals(expected, actual)
        }

        override fun rename(newName: String) {
            order.add(RENAME_LIVEDATA)
            actual = newName
        }

        override fun update(folder: FolderUi) {
            throw IllegalStateException("not used in test")
        }

        override fun folderLiveData(): LiveData<FolderUi> {
            throw IllegalStateException("not used in test")
        }
    }
}