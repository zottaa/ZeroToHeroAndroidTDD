package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.databinding.RecyclerViewItemBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SimpleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = SimpleAdapter()
        binding.recyclerView.adapter = adapter
        binding.actionButton.setOnClickListener {
            binding.inputEditText.text?.let {
                adapter.add(it.toString())
                it.clear()
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val itemList = savedInstanceState.getStringArrayList(KEY)
        itemList?.let {
            adapter.addAll(ArrayList(itemList))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val list = ArrayList((binding.recyclerView.children.map { view ->
            (view as TextView).text.toString()
        }).toList())
        outState.putStringArrayList(KEY, list)
    }

    companion object {
        private const val KEY = "listKEY"
    }
}