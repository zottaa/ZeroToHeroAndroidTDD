package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.RecyclerViewItemBinding

class SimpleAdapter :
    RecyclerView.Adapter<SimpleHolder>() {


    private val dataList: ArrayList<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleHolder {
        val binding =
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )

        return SimpleHolder(binding)
    }

    fun save(bundle: Bundle) {
        bundle.putStringArrayList(KEY, dataList)
    }

    fun restore(bundle: Bundle) {
        val itemList = bundle.getStringArrayList(KEY)
        itemList?.let { list ->
            list.forEach { item ->
                add(item)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    fun add(item: String) {
        dataList.add(item)
        notifyItemInserted(dataList.size - 1)
    }

    companion object {
        private const val KEY = "listKEY"
    }
}

class SimpleHolder(private val binding: RecyclerViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(source: CharSequence) {
        binding.elementTextView.text = source
    }

}