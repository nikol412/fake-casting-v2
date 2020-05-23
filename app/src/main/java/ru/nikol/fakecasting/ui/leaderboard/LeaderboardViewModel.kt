package ru.nikol.fakecasting.ui.leaderboard

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.nikol.fakecasting.common.extension.subscribeOnBackgroundObserveOnMain
import ru.nikol.fakecasting.data.Api
import ru.nikol.fakecasting.data.network.RetrofitRxInstance
import ru.nikol.fakecasting.data.network.model.Site
import ru.nikol.fakecasting.ui.base.BaseVM


class LeaderboardViewModel : BaseVM() {
    val service = RetrofitRxInstance.retrofitInstance!!.create(Api::class.java)

    var currentPageNumber = 0
    var totalPages: Int = 0

    var allSitesList: MutableLiveData<MutableList<Site?>> =
        MutableLiveData(mutableListOf())

    init {
        currentPageNumber++
        getPage(currentPageNumber)
    }

    fun loadMore() {
        if (currentPageNumber < totalPages - 1) {
            currentPageNumber++
            getPage(currentPageNumber)
        }
    }

    fun getPage(page: Int) {
        service.getLeaderboard(page)
            .subscribeOnBackgroundObserveOnMain()
            .doOnSubscribe { }
            .subscribe({ response ->
                when (response.code()) {
                    200 -> {
                        allSitesList.value?.addAll(response.body()?.sitesList!!.toMutableList())
                        allSitesList.value = allSitesList.value

                        totalPages = response.body()?.totalPages ?: 0
                    }
                }
            }, {

            })
    }

    override fun onCleared() {
        super.onCleared()
    }
}