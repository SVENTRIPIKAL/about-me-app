package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aboutme.databinding.ActivityMainBinding

const val TAG = "DISPLAY NICKNAME ERROR:"

class MainActivity : AppCompatActivity() {

    // create lateinit for Activity Binder
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate activity layout
        binding = ActivityMainBinding.inflate(layoutInflater)

        // reference any layouts/views as objects
        val activityMainLayout: LinearLayout = binding.root

        val editTextView: EditText = binding.editTextView
        val nicknamePopupView: TextView = binding.nicknamePopupView
        val doneButton: Button = binding.doneButton

        // button onClickListener
        doneButton.setOnClickListener {
            displayNickName(doneButton, editTextView, nicknamePopupView)
        }

        // pass layout view
        setContentView(activityMainLayout)
    }


    /**
     * function should remove visibility of Button/editTextView
     * assign editText.text to nicknamePopupView.text,
     * reassign visibility back to nicknamePopupView, then
     * finally hide the keyboard.
     */
    private fun displayNickName(
        button: Button,
        inputText: EditText,
        nicknamePopupView: TextView
    ) {
        try {
            // transfer editText text
            nicknamePopupView.text = inputText.editableText

            // hide views
            button.visibility = View.GONE
            inputText.visibility = View.GONE

            // assign visibility to popupView
            nicknamePopupView.visibility = View.VISIBLE


            // provide feedback for any errors as they occur
        } catch (e: Exception) {

            val message = e.message.toString()

            Log.e(TAG, message)

        } finally {

            // hide keyboard
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(button.windowToken, 0)
        }
    }
}