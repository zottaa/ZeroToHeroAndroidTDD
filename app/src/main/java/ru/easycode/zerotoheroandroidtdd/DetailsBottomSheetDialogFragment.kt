package ru.easycode.zerotoheroandroidtdd

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText


class DetailsBottomSheetDialogFragment(
    private val id: Long
) : BottomSheetDialogFragment(R.layout.fragment_details) {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        viewModel =
            (activity as ProvideViewModel).viewModel(DetailsViewModel::class.java) as DetailsViewModel
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.comeback()
                dismiss()
            }
        }
        (dialog as BottomSheetDialog).onBackPressedDispatcher.addCallback(onBackPressedCallback)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val updateButton = view.findViewById<Button>(R.id.updateButton)
        val deleteButton = view.findViewById<Button>(R.id.deleteButton)
        val updateInput = view.findViewById<TextInputEditText>(R.id.itemInputEditText)

        viewModel.liveData.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.itemTextView).text = it
            updateInput.setText(it)
            updateInput.setSelection(it.length)
        }

        updateInput.addTextChangedListener {
            updateButton.isEnabled = updateInput.text.toString().length > 1
        }

        updateButton.setOnClickListener {
            viewModel.update(id, updateInput.text.toString())
            dismiss()
        }

        deleteButton.setOnClickListener {
            viewModel.delete(id)
            dismiss()
        }

        viewModel.init(id)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        viewModel.comeback()
    }

    override fun onDestroy() {
        super.onDestroy()
        onBackPressedCallback.remove()
    }
}