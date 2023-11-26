package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var countTextView: TextView
    private lateinit var incrementButton: Button
    private lateinit var decrementButton: Button

    private val count = Count.Base(2, 4, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countTextView = findViewById(R.id.countTextView)
        incrementButton = findViewById(R.id.incrementButton)
        decrementButton = findViewById(R.id.decrementButton)

        incrementButton.setOnClickListener {
            val state: UiState = count.increment(countTextView.text.toString())
            state.apply(countTextView, incrementButton, decrementButton)
        }

        decrementButton.setOnClickListener {
            val state: UiState = count.decrement(countTextView.text.toString())
            state.apply(countTextView, incrementButton, decrementButton)
        }

        val state = count.initial(countTextView.text.toString())
        state.apply(countTextView, incrementButton, decrementButton)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(KEY, count.initial(countTextView.text.toString()))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, UiState::class.java) as UiState
        } else {
            savedInstanceState.getSerializable(KEY) as UiState
        }

        state.apply(countTextView, incrementButton, decrementButton)
    }

    companion object {
        const val KEY = "UiState"
    }
}