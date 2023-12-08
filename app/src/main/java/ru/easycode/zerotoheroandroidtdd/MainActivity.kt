package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as App).viewModel

        val textView = findViewById<TextView>(R.id.titleTextView)
        val button = findViewById<Button>(R.id.actionButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        button.setOnClickListener {
            viewModel.load()
        }

        viewModel.liveData().observe(this) {uiState ->
            uiState.apply(button, progressBar, textView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val bundleWrapper = BundleWrapper.Base(outState)

        viewModel.save(bundleWrapper)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val bundleWrapper = BundleWrapper.Base(savedInstanceState)

        viewModel.restore(bundleWrapper)
    }
}