package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textView = findViewById<TextView>(R.id.titleTextView)
        val button = findViewById<Button>(R.id.actionButton)

        button.setOnClickListener {
            it.isEnabled = false
            progressBar.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                textView.visibility = View.VISIBLE
                it.isEnabled = true
                progressBar.visibility = View.INVISIBLE
            }, 2000)
        }
    }
}