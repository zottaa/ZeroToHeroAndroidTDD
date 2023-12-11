package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import com.google.android.material.internal.TextWatcherAdapter
import com.google.android.material.textfield.TextInputEditText
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.inputEditText.addTextChangedListener {
            binding.actionButton.isEnabled = binding.inputEditText.text.toString().length >= 3
        }

        binding.actionButton.setOnClickListener {
            binding.titleTextView.text = binding.inputEditText.text
            binding.inputEditText.text?.clear()
        }
    }
}