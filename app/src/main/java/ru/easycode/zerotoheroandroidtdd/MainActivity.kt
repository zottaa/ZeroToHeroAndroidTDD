package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var state: UiState
    private lateinit var incrementButton: Button
    private lateinit var countTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        incrementButton = findViewById(R.id.incrementButton)
        countTextView = findViewById(R.id.countTextView)
        incrementButton.setOnClickListener {
            val count = Count.Base(2, 4)
            state = count.increment(countTextView.text.toString())
            state.apply(countTextView, incrementButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(KEY, state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            state = savedInstanceState.getSerializable(KEY, UiState::class.java) as UiState
        } else {
            state = savedInstanceState.getSerializable(KEY) as UiState
        }

        state.apply(countTextView, incrementButton)
    }

    companion object {
        const val KEY = "UiState"
    }
}