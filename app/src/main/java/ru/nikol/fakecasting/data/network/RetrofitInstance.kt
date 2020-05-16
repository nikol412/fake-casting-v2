package ru.nikol.fakecasting.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://LHD--alexandrgonchar.repl.co/"
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}

fun getClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()

    logging.setLevel(HttpLoggingInterceptor.Level.BODY)

    val httpClient = OkHttpClient.Builder()

    httpClient.addInterceptor(logging)
    return httpClient.build()
}