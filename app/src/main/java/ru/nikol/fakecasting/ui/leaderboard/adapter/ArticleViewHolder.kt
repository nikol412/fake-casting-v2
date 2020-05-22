package ru.nikol.fakecasting.ui.leaderboard.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import ru.nikol.fakecasting.R
import ru.nikol.fakecasting.data.network.model.Site

class ArticleViewHolder(itemView: View?) : BaseViewHolder(itemView) {
    var iconView: ImageView? = itemView?.findViewById(R.id.site_icon_leaderboard)
    var headUrl: TextView? = itemView?.findViewById(R.id.head_url_title_leaderboard)
    var siteState: TextView? = itemView?.findViewById(R.id.site_state_leaderboard)
    var rowIndex: TextView? = itemView?.findViewById(R.id.row_index_leaderboard)

    override fun clear() {
    }

    fun onBind(position: Int, site: Site?) {
        super.onBind(position)

        site?.let {
            loadIcon(iconView!!, it.icon)

            rowIndex?.text = position.toString()

            headUrl?.text = it.headUrl

            siteState?.text = """${"%.2f".format((it.siteStat?.times(100)))}%"""

            loadIcon(iconView!!, it.icon)
        }
    }
}