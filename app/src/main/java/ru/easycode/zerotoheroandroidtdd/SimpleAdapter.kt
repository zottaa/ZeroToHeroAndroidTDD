package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.easycode.zerotoheroandroidtdd.databinding.ElementTextViewBinding

class SimpleAdapter(private var list: List<CharSequence>) :
    RecyclerView.Adapter<SimpleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val binding = ElementTextViewBinding.inflate(
            LayoutInflater.from(parent.context)
        )

        return SimpleViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        val item = list[position]
        holder.hold(item)
    }

    fun update(newList: List<CharSequence>) {
        val diffUtil = SimpleDiffUtil(list, newList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        diffUtilResult.dispatchUpdatesTo(this)

        list = newList
    }
}

class SimpleViewHolder(private val binding: ElementTextViewBinding) : ViewHolder(binding.root) {

    fun hold(source: CharSequence) {
        binding.elementTextView.text = source
    }

}