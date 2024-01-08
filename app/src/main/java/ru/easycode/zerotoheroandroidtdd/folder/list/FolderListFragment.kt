package ru.easycode.zerotoheroandroidtdd.folder.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFolderListBinding
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderUi

class FolderListFragment : AbstractFragment<FragmentFolderListBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFolderListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = (activity as ProvideViewModel).viewModel(FolderListViewModel::class.java)
        val adapter = FolderAdapter(object : FolderDetails {
            override fun folderDetails(folderUi: FolderUi) {
                viewModel.folderDetails(folderUi)
            }
        })
        binding.foldersRecyclerView.adapter = adapter

        binding.addButton.setOnClickListener {
            viewModel.addFolder()
        }

        viewModel.liveData().observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        viewModel.init()
    }
}