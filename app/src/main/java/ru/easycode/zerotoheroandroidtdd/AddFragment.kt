package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentAddBinding

class AddFragment : AbstractFragment<FragmentAddBinding>() {

    override fun bind(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentAddBinding.inflate(inflater, container, false)


    private lateinit var viewModel: AddViewModel

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            (activity as ProvideViewModel).viewModel(AddViewModel::class.java) as AddViewModel

        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        binding.addInputEditText.addTextChangedListener {
            binding.saveButton.isEnabled = it.toString().length >= 3
        }

        binding.saveButton.setOnClickListener {
            hideKeyboard()
            viewModel.add(binding.addInputEditText.text.toString())
            requireActivity().supportFragmentManager.beginTransaction()
                .remove(this)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}