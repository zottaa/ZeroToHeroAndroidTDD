package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var titleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val changeButton = findViewById<Button>(R.id.changeButton)
        titleTextView = findViewById<TextView>(R.id.titleTextView)

        if (savedInstanceState != null) {
            titleTextView.text = savedInstanceState.getString("TitleTextView", "")
        }

        changeButton.setOnClickListener {
            titleTextView.text = "I am an Android Developer!"
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("TitleTextView", titleTextView.text.toString())

        super.onSaveInstanceState(outState)
    }
}