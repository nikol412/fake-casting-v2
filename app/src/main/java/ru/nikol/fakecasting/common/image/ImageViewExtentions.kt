package ru.nikol.fakecasting.common.image

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.GrayscaleTransformation

fun ImageView.loadImage(
    url: String?,
    placeholder: Int = 0,
    size: Pair<Int, Int>? = null,
    withGrayFilter: Boolean = false
) {

    url?.let {
        val request = Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(placeholder)

        size?.let { (width, height) ->
            request.override(width, height)
        }

        if (withGrayFilter) {
            request.apply(RequestOptions.bitmapTransform(GrayscaleTransformation()))
        }
        request.thumbnail(Glide.with(this).load(placeholder).centerCrop())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    } ?: run {
        this.setImageResource(placeholder)
    }
}

fun ImageView.generateGradientBackground(
    begin: Int,
    end: Int,
    withGray: Boolean = false
) {
    val gd = GradientDrawable(
        GradientDrawable.Orientation.TOP_BOTTOM,
        intArrayOf(begin, end)
    )
    gd.cornerRadius = 0f

    this.setImageDrawable(gd)

    if (withGray) {
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0F)
        val filter = ColorMatrixColorFilter(colorMatrix)

        this.colorFilter = filter
    } else {
        this.clearColorFilter()
    }
}