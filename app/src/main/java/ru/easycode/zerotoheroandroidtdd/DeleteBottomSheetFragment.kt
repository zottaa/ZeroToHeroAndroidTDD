package ru.easycode.zerotoheroandroidtdd

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DeleteBottomSheetFragment(private val id: Long) :
    BottomSheetDialogFragment(R.layout.fragment_delete) {

    private lateinit var viewModel: DeleteViewModel
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        viewModel =
            (activity as ProvideViewModel).viewModel(DeleteViewModel::class.java) as DeleteViewModel
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.comeback()
                dismiss()
            }
        }
        (dialog as BottomSheetDialog).onBackPressedDispatcher.addCallback(onBackPressedCallback)
        return dialog
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        viewModel.comeback()
        dismiss()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.deleteButton)

        viewModel.liveData.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.itemTitleTextView).text = it
        }

        button.setOnClickListener {
            viewModel.delete(id)
            dismiss()
        }

        viewModel.init(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}