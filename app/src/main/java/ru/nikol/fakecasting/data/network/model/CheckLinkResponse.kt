package ru.nikol.fakecasting.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CheckLinkResponse(
    @SerializedName("icon")
    @Expose
    var iconLink: String? = null,

    @SerializedName("site_stat")
    @Expose
    var siteStat: Double? = null,

    @SerializedName("prob")
    @Expose
    var prob: Double? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null
)