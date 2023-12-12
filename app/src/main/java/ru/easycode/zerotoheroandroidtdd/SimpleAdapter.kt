package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.RecyclerViewItemBinding

class SimpleAdapter(private val dataList: ArrayList<String> = ArrayList()) :
    RecyclerView.Adapter<SimpleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleHolder {
        val binding =
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )

        return SimpleHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        val item = dataList[position]
        holder.binding.elementTextView.text = item
    }

    fun add(item: String) {
        dataList.add(item)
        notifyItemInserted(dataList.size - 1)
    }

    fun addAll(list: ArrayList<String>) {
        list.forEach {
            add(it)
        }
    }
}

class SimpleHolder(val binding: RecyclerViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
}