package ru.easycode.zerotoheroandroidtdd.folder.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFolderDetailsBinding

class FolderDetailsFragment : AbstractFragment<FragmentFolderDetailsBinding>() {
    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFolderDetailsBinding.inflate(inflater, container, false)

    private lateinit var viewModel: FolderDetailsViewModel
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(FolderDetailsViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        val adapter = NoteAdapter(object : EditNote {
            override fun edit(noteUi: NoteUi) {
                viewModel.editNote(noteUi)
            }
        })
        binding.notesRecyclerView.adapter = adapter

        binding.editFolderButton.setOnClickListener {
            viewModel.editFolder()
        }

        binding.addNoteButton.setOnClickListener {
            viewModel.createNote()
        }

        viewModel.liveData().observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        viewModel.folderLiveData().observe(viewLifecycleOwner) {
            it.showTitle(binding.folderNameTextView)
            it.showNotesCount(binding.notesCountTextView)
        }

        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}