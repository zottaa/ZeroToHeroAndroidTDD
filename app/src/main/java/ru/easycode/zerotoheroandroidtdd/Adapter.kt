package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemRecyclerViewBinding

class Adapter(
    private val deleteItemUi: DeleteItemUi,
    private var list: List<ItemUi> = emptyList()
) : RecyclerView.Adapter<ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context)
        )

        return ListViewHolder(deleteItemUi, binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = list[position]
        holder.hold(item)
    }

    fun update(newList: List<ItemUi>) {
        val diffUtil = ListDiffUtil(list, newList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        diffUtilResult.dispatchUpdatesTo(this)

        list = newList
    }
}

class ListViewHolder(
    private val deleteItemUi: DeleteItemUi,
    private val binding: ItemRecyclerViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun hold(itemUi: ItemUi) {
        itemUi.show(binding.elementTextView)
        binding.elementTextView.setOnClickListener {
            itemUi.delete(deleteItemUi)
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
        newList[newItemPosition].areItemsSame(oldList[oldItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]
}