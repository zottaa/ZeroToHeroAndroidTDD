package ru.easycode.zerotoheroandroidtdd.note.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentNoteEditBinding

class EditNoteFragment(private val noteId: Long) : AbstractFragment<FragmentNoteEditBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentNoteEditBinding.inflate(inflater, container, false)

    private lateinit var viewModel: EditNoteViewModel
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(EditNoteViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        viewModel.liveData().observe(viewLifecycleOwner) {
            binding.noteEditText.apply {
                setText(it)
                setSelection(it.length)
            }
        }

        binding.noteEditText.addTextChangedListener {
            binding.saveNoteButton.isEnabled = it.toString().isNotEmpty()
        }

        binding.saveNoteButton.setOnClickListener {
            viewModel.renameNote(noteId, binding.noteEditText.text.toString())
        }

        binding.deleteNoteButton.setOnClickListener {
            viewModel.deleteNote(noteId)
        }

        viewModel.init(noteId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}