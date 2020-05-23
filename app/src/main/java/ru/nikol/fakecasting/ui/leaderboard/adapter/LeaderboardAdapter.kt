package ru.nikol.fakecasting.ui.leaderboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.nikol.fakecasting.R
import ru.nikol.fakecasting.data.network.model.Site


class LeaderboardAdapter(var listener: OnLoadMoreListener) :
    RecyclerView.Adapter<BaseViewHolder>() {
    var totalSites: Int = 0

    private var mSitesList: MutableList<Site?>? = mutableListOf()

    fun setItems(items: MutableList<Site?>?, total: Int = 0) {
        totalSites = total

        mSitesList?.let {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                override fun getOldListSize(): Int = mSitesList?.size ?: 0

                override fun getNewListSize(): Int = items!!.size

                override fun areItemsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    return mSitesList?.getOrNull(oldItemPosition)?.headUrl == mSitesList?.getOrNull(
                        newItemPosition
                    )?.headUrl
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val newApp = items?.get(newItemPosition)?.headUrl
                    val oldApp = mSitesList?.get(oldItemPosition)?.headUrl
                    return newApp == oldApp
                }
            })

            mSitesList = items

            notifyDataSetChanged()

            result.dispatchUpdatesTo(this)

        } ?: kotlin.run {
            if (items!!.isNotEmpty()) {
                mSitesList = items
                notifyDataSetChanged()
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val site: Site? = mSitesList?.getOrNull(position)

        when (holder.itemViewType) {
            VIEW_TYPE_NORMAL -> {
                (holder as ArticleViewHolder).onBind(position, site)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            VIEW_TYPE_NORMAL -> {
                return ArticleViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.leaderboard_row_layout, parent, false)
                )
            }
            else -> {
                return ArticleViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.leaderboard_row_layout, parent, false)
                )
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_NORMAL
    }

    override fun getItemCount(): Int {
        return mSitesList!!.size
    }


    interface OnLoadMoreListener {
        fun loadMore()
    }

    companion object {
        const val VIEW_TYPE_NORMAL = 0
    }

}

