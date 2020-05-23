package ru.nikol.fakecasting.ui.bindings

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import ru.nikol.fakecasting.common.image.loadImage


@BindingAdapter("app:visibleAndFocused")
fun visibleAndFocused(view: View, visible: Boolean) {
    if (visible) {
        view.visibility = View.VISIBLE
        view.requestFocus()
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("app:invisibleUnless")
fun invisibleUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("app:goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("imageMediaId")
fun imageMediaId(image: ImageView, id: Int) {
    // image.loadImageByMediaId(id)
}

@BindingAdapter("imagePath")
fun imagePath(image: ImageView, path: String) {
    image.loadImage(path)
}
