package ru.nikol.fakecasting.common.extension

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Context.hideKeyboard() {
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
    inputManager.hideSoftInputFromWindow(
        (this as Activity).window.decorView.rootView.windowToken,
        0
    )
}

fun Context.convertDipToTextSize(dips: Float): Float {
    return dips / resources.displayMetrics.density
}