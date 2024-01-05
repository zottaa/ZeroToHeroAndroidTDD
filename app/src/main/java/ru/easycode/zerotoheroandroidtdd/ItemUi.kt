package ru.easycode.zerotoheroandroidtdd

import android.widget.TextView

data class ItemUi(
    private val id: Long,
    private val text: String
) {
    fun areItemsSame(other: ItemUi) = other.id == this.id

    fun show(textView: TextView) {
        textView.text = text
    }

    fun delete(deleteItemUi: DeleteItemUi) = deleteItemUi.delete(id)
}