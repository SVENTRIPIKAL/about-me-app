package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.aboutme.databinding.ActivityMainBinding


// exception tag
const val TAG = "DISPLAY NICKNAME ERROR:"

class MainActivity : AppCompatActivity() {

    // create lateinit for Activity Binder
    private lateinit var binding: ActivityMainBinding

    // lateinit for MyName data class
    // cannot be initialized with resource values
    // until activity & objects have been inflated
    private lateinit var nameObject: MyName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate activity layout & its objects
        binding = ActivityMainBinding.inflate(layoutInflater)

        // initialize data class with resource value
        nameObject = MyName(getString(R.string.name_text))

        // reference any layouts/views as objects if necessary
        // manipulate data via as-needed references
        // update data class via binder with new object data created in activity
        binding.myName = nameObject

        // button onClickListener
        binding.doneButton.setOnClickListener {

            // display nickName
            displayNickName()
        }

        // pass layout view
        setContentView(binding.mainLayout)
    }


    /**
     * function should remove visibility of Button/editTextView,
     * assign editText.text to nicknamePopupView.text via data class,
     * reassign visibility back to nicknamePopupView, then finally
     * hide the keyboard.
     */
    private fun displayNickName() {
        try {

            // block of code referencing binder
            binding.apply {

                // transfer editText.text to popUpView.text via data class object
                // both of myName data class variables should be initialized now
                myName = nameObject.copy(
                    nickName = editTextView.editableText.toString()
                )

                // remove button & editText visibility
                doneButton.visibility = View.GONE
                editTextView.visibility = View.GONE

                // assign visibility back to popupView
                // should now reference myName.nickName value
                nicknamePopupView.visibility = View.VISIBLE

                // refresh UI
                invalidateAll()
            }

            // provide feedback for any errors if applicable
        } catch (e: Exception) {

            val message = e.message.toString()

            Log.e(TAG, message)

        } finally {

            // hide keyboard
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.doneButton.windowToken, 0)
        }
    }
}