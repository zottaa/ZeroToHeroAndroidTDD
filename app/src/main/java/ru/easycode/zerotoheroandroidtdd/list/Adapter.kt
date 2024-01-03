package ru.easycode.zerotoheroandroidtdd.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemRecyclerViewBinding

class Adapter(
    private var list: List<CharSequence> = emptyList()
) : RecyclerView.Adapter<ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context)
        )

        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = list[position]
        holder.hold(item)
    }

    fun update(newList: List<CharSequence>) {
        val diffUtil = ListDiffUtil(list, newList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        diffUtilResult.dispatchUpdatesTo(this)

        list = newList
    }
}

class ListViewHolder(
    private val binding: ItemRecyclerViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun hold(source: CharSequence) {
        binding.elementTextView.text = source
    }
}

class ListDiffUtil(
    private val oldList: List<CharSequence>,
    private val newList: List<CharSequence>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] === oldList[oldItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]
}