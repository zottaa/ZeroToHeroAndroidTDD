package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemRecyclerViewBinding

class Adapter(
    private val detailsItemUi: DetailsItemUi,
    private var list: List<ItemUi> = emptyList()
) : RecyclerView.Adapter<ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
        return ListViewHolder(binding, detailsItemUi)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.hold(list[position])
    }

    fun update(newList: List<ItemUi>) {
        val diffUtil = ListDiffUtil(list, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }

}

class ListViewHolder(
    private val binding: ItemRecyclerViewBinding,
    private val detailsItemUi: DetailsItemUi
) : RecyclerView.ViewHolder(binding.root) {

    fun hold(itemUi: ItemUi) {
        itemUi.show(binding.elementTextView)
        binding.elementTextView.setOnClickListener {
            itemUi.details(detailsItemUi)
        }
    }
}

class ListDiffUtil(
    private val oldList: List<ItemUi>,
    private val newList: List<ItemUi>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].areItemsSame(newList[newItemPosition])


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == (newList[newItemPosition])


}

