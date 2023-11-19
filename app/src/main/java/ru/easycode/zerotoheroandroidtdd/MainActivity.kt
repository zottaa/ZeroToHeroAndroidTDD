package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private var viewState: TextViewState = TextViewState.Initial

    private lateinit var linearLayout: LinearLayout
    private lateinit var removeButton: Button
    private lateinit var titleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayout = findViewById(R.id.rootLayout)
        removeButton = findViewById(R.id.removeButton)
        titleTextView = findViewById(R.id.titleTextView)

        removeButton.setOnClickListener {
            viewState = TextViewState.Removed

            viewState.apply(linearLayout, titleTextView, removeButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, viewState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            viewState = savedInstanceState.getSerializable(
                KEY,
                TextViewState::class.java
            ) as TextViewState
        } else {
            viewState = savedInstanceState.getSerializable(KEY) as TextViewState
        }

        viewState.apply(linearLayout, titleTextView, removeButton)
    }

    companion object {
        private const val KEY = "TextViewState"
    }
}


interface TextViewState : Serializable {

    fun apply(linearLayout: LinearLayout, textView: TextView, button: Button)

    object Initial : TextViewState {
        override fun apply(linearLayout: LinearLayout, textView: TextView, button: Button) = Unit
    }

    object Removed : TextViewState {
        override fun apply(linearLayout: LinearLayout, textView: TextView, button: Button) {
            linearLayout.removeView(textView)
            button.isEnabled = false
        }
    }
}