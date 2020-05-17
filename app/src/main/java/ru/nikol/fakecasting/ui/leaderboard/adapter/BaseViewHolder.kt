package ru.nikol.fakecasting.ui.leaderboard.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.nikol.fakecasting.common.image.loadImage

abstract class BaseViewHolder(itemView: View?) :
    RecyclerView.ViewHolder(itemView!!) {
    var currentPosition = 0
        private set

    protected abstract fun clear()
    open fun onBind(position: Int) {
        currentPosition = position
        clear()
    }

    open fun loadIcon(imageView: ImageView, iconUrl: String?) {
        imageView.loadImage(iconUrl)
    }

}