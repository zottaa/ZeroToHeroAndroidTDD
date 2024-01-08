package ru.easycode.zerotoheroandroidtdd.folder.edit

import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.core.Screen

data class EditFolderScreen(private val folderId: Long) : Screen {
    override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                containerId,
                EditFolderFragment(folderId)
            )
            .commit()
    }
}