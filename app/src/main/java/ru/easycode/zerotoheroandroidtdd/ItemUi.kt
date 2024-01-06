package ru.easycode.zerotoheroandroidtdd

import android.widget.TextView

data class ItemUi(
    private val id: Long,
    private val text: String
) {
    fun areItemsSame(other: ItemUi): Boolean {
        return this.id == other.id
    }

    fun show(textView: TextView) {
        textView.text = text
    }

    fun details(detailsItemUi: DetailsItemUi) {
        detailsItemUi.detailsItemUi(id)
    }
}
