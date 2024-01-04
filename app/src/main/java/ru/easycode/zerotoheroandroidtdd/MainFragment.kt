package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentMainBinding

class MainFragment : AbstractFragment<FragmentMainBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel =
            (activity as ProvideViewModel).viewModel(MainViewModel::class.java) as MainViewModel

        val adapter = Adapter()
        binding.recyclerView.adapter = adapter

        binding.addButton.setOnClickListener {
            viewModel.create()
        }

        viewModel.liveData().observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        viewModel.init()
    }
}