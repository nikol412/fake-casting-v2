package ru.nikol.fakecasting.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.sdk27.coroutines.onTouch


abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun layoutRes(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        subscribeToViewModel()
    }

    protected open fun subscribeToViewModel() {

    }

    protected fun startActivityAndFinishSelf(activity: Class<out Activity>) {
        val intent = Intent(this, activity)
        startActivity(intent)
        finish()
    }

    fun setupUI(view: View) {
        if (view !is EditText && view !is Button) {
            view.onTouch { _, _ ->
                Log.d("HIDE_KEYBOARD", view.id.toString())
                hideSoftKeyboard()
            }
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }

    fun hideSoftKeyboard() {
        try {
            val inputMethodManager = this.getSystemService(
                Activity.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                this.currentFocus!!.windowToken, 0
            )
        } catch (ex: Exception) {

        }
    }

}