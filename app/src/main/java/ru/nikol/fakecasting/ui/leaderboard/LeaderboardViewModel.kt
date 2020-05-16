package ru.nikol.fakecasting.ui.leaderboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nikol.fakecasting.data.Api
import ru.nikol.fakecasting.data.network.RetrofitInstance
import ru.nikol.fakecasting.data.network.model.CheckLinkResponse
import ru.nikol.fakecasting.data.network.model.LeaderboardResponse


class LeaderboardViewModel : ViewModel() {
    val service2 = RetrofitInstance.retrofitInstance!!.create(Api::class.java)
    var text: MutableLiveData<String> = MutableLiveData("ok")

    var currentPageNumber = 0
    var totalSites: Int = 0

    var allSitesList: MutableLiveData<MutableList<LeaderboardResponse?>> =
        MutableLiveData(mutableListOf())

    init {
        currentPageNumber++
        getPage(currentPageNumber)
    }

    fun onSendClick(){
        makeRequest("https://www.bbc.com/news/uk-52674192")
    }
    fun makeRequest(url:String) {
        service2.checkLink(url).enqueue(object : Callback<CheckLinkResponse>{
            override fun onResponse(
                call: Call<CheckLinkResponse>,
                response: Response<CheckLinkResponse>
            ) {

                text.value = "answer: ${response.body()?.prob}"
            }

            override fun onFailure(call: Call<CheckLinkResponse>, t: Throwable) {
                Log.d("retrofit2","${t.message}")
            }
        })
    }

    fun loadMore() {
        //if (currentPageNumber < lastPageNumber) {
            currentPageNumber++
            getPage(currentPageNumber)
        //}
    }

    fun getPage(page:Int){
        service2.getLeaderboard(page).enqueue(object: Callback<List<LeaderboardResponse>>{
            override fun onResponse(
                call: Call<List<LeaderboardResponse>>,
                response: Response<List<LeaderboardResponse>>
            ) {
                response.body()?.let {
                    allSitesList.value?.addAll(it.toMutableList())
                    allSitesList.value = allSitesList.value
                }
            }

            override fun onFailure(call: Call<List<LeaderboardResponse>>, t: Throwable) {
                Log.d("retrofit2", "${t.message}")
            }
        })
    }
    override fun onCleared() {
        super.onCleared()
    }
}