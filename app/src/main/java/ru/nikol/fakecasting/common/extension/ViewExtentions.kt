package ru.nikol.fakecasting.common.extension

import android.view.View


fun View.show(isVisible: Boolean = true) {
    this.visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.hide() {
    show(false)
}