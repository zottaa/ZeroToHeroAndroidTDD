package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.children
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.actionButton.setOnClickListener {
            binding.inputEditText.text?.let {
                addTextView(it.toString())
                it.clear()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(
            KEY,
            ArrayList(
                binding.contentLayout.children
                    .map {
                        (it as TextView).text.toString()
                    }.toList()
            )
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getStringArrayList(KEY)?.let { list ->
            list.forEach { text ->
                addTextView(text)
            }
        }
    }

    fun addTextView(inputText: String) {
        val newTextView = TextView(this).apply {
            text = inputText
        }
        binding.contentLayout.addView(newTextView)
    }

    companion object {
        private const val KEY = "listKEY"
    }
}