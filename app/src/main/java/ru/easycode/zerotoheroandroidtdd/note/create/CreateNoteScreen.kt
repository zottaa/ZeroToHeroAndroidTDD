package ru.easycode.zerotoheroandroidtdd.note.create

import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.core.Screen

data class CreateNoteScreen(private val folderId: Long) : Screen {
    override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                containerId,
                CreateNoteFragment(folderId)
            )
            .commit()
    }
}