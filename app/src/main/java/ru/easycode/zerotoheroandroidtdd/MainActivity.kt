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
    private var buttonState: RemoveButtonState = RemoveButtonState.Initial

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
            buttonState = RemoveButtonState.Disabled

            viewState.apply(linearLayout, titleTextView)
            buttonState.apply(removeButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_VIEW, viewState)
        outState.putSerializable(KEY_BUTTON, buttonState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            viewState = savedInstanceState.getSerializable(
                KEY_VIEW,
                TextViewState::class.java
            ) as TextViewState
        } else {
            viewState = savedInstanceState.getSerializable(KEY_VIEW) as TextViewState
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            buttonState = savedInstanceState.getSerializable(
                KEY_BUTTON,
                RemoveButtonState::class.java
            ) as RemoveButtonState
        } else {
            buttonState = savedInstanceState.getSerializable(KEY_BUTTON) as RemoveButtonState
        }

        viewState.apply(linearLayout, titleTextView)
        buttonState.apply(removeButton)
    }

    companion object {
        private const val KEY_VIEW = "titleTextViewState"
        private const val KEY_BUTTON = "removeButtonState"
    }
}


interface TextViewState : Serializable {

    fun apply(linearLayout: LinearLayout, textView: TextView)

    object Initial : TextViewState {
        override fun apply(linearLayout: LinearLayout, textView: TextView) = Unit
    }

    object Removed : TextViewState {
        override fun apply(linearLayout: LinearLayout, textView: TextView) {
            linearLayout.removeView(textView)
        }
    }
}

interface RemoveButtonState : Serializable {

    fun apply(removeButton: Button)

    object Initial : RemoveButtonState {
        override fun apply(removeButton: Button) = Unit
    }

    object Disabled : RemoveButtonState {
        override fun apply(removeButton: Button) {
            removeButton.isEnabled = false
        }
    }
}