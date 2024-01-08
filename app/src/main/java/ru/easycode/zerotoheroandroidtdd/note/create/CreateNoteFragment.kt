package ru.easycode.zerotoheroandroidtdd.note.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentNoteCreateBinding

class CreateNoteFragment(private val folderId: Long) :
    AbstractFragment<FragmentNoteCreateBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentNoteCreateBinding.inflate(inflater, container, false)

    private lateinit var viewModel: CreateNoteViewModel
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(CreateNoteViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        binding.createNoteEditText.addTextChangedListener {
            binding.saveNoteButton.isEnabled = it.toString().isNotEmpty()
        }

        binding.saveNoteButton.setOnClickListener {
            viewModel.createNote(folderId, binding.createNoteEditText.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}