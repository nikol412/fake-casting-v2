package ru.nikol.fakecasting.data

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nikol.fakecasting.data.network.model.CheckLinkResponse

interface Api {
    @GET("/get_prob/url")
    fun checkLink(@Query("url") url: String?): Call<CheckLinkResponse>

    @GET("/get_prob/text")
    fun sendText(@Query("text") text:String): Call<CheckLinkResponse>


}