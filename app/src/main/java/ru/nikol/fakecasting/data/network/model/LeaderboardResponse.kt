package ru.nikol.fakecasting.data.network.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class LeaderboardResponse {
    @SerializedName("head_url")
    @Expose
    var headUrl: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

    @SerializedName("site_stat")
    @Expose
    var siteStat: Double? = null
}