package ru.easycode.zerotoheroandroidtdd.folder.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFolderEditBinding

class EditFolderFragment(private val folderId: Long) :
    AbstractFragment<FragmentFolderEditBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFolderEditBinding.inflate(inflater, container, false)

    private lateinit var viewModel: EditFolderViewModel
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(EditFolderViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        binding.folderEditText.addTextChangedListener {
            binding.saveFolderButton.isEnabled = it.toString().isNotEmpty()
        }

        binding.saveFolderButton.setOnClickListener {
            viewModel.renameFolder(folderId, binding.folderEditText.text.toString())
        }

        binding.deleteFolderButton.setOnClickListener {
            viewModel.deleteFolder(folderId)
        }

        viewModel.folderLiveData().observe(viewLifecycleOwner) {
            it.showTitle(binding.folderEditText)
        }

        viewModel.init(folderId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}