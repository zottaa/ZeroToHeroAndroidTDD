package ru.easycode.zerotoheroandroidtdd.folder.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFolderAddBinding

class FolderAddFragment : AbstractFragment<FragmentFolderAddBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFolderAddBinding.inflate(inflater, container, false)

    private lateinit var viewModel: CreateFolderViewModel
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as ProvideViewModel).viewModel(CreateFolderViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        binding.createFolderEditText.addTextChangedListener {
            binding.saveFolderButton.isEnabled = it.toString().isNotEmpty()
        }

        binding.saveFolderButton.setOnClickListener {
            viewModel.createFolder(binding.createFolderEditText.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}