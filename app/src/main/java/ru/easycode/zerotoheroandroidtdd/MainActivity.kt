package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hideButton = findViewById<Button>(R.id.hideButton)
        titleTextView = findViewById(R.id.titleTextView)

        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean("isHide", false)) {
                titleTextView.visibility = TextView.INVISIBLE
            }
        }

        hideButton.setOnClickListener {
            titleTextView.visibility = TextView.INVISIBLE
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("isHide", !titleTextView.isVisible)

        super.onSaveInstanceState(outState)
    }
}