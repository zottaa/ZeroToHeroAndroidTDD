package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.contains
import androidx.core.view.isGone
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var rootLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hideButton = findViewById<Button>(R.id.removeButton)
        titleTextView = findViewById(R.id.titleTextView)
        rootLayout = findViewById(R.id.rootLayout)

        hideButton.setOnClickListener {
            rootLayout.removeView(titleTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(KEY, rootLayout.contains(titleTextView))
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        if (!savedInstanceState.getBoolean(KEY)) {
            rootLayout.removeView(titleTextView)
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

    companion object {
        const val KEY = "isRemoved"
    }
}