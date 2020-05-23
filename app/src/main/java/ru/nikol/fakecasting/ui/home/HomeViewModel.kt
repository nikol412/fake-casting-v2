package ru.nikol.fakecasting.ui.home

import androidx.lifecycle.MutableLiveData
import ru.nikol.fakecasting.common.extension.subscribeOnBackgroundObserveOnMain
import ru.nikol.fakecasting.common.live.SingleLiveEvent
import ru.nikol.fakecasting.data.Api
import ru.nikol.fakecasting.data.network.RetrofitRxInstance
import ru.nikol.fakecasting.ui.base.BaseVM
import kotlin.math.round

class HomeViewModel : BaseVM() {

    val service = RetrofitRxInstance.retrofitInstance!!.create(Api::class.java)
    val linkText: MutableLiveData<String> = MutableLiveData()
    val truthCount: MutableLiveData<String> = MutableLiveData()
    val eventCall: SingleLiveEvent<Int> = SingleLiveEvent()


    init {
        truthCount.value ="0%"
    }

    fun onSendLinkClick() {
        if (!linkText.value.isNullOrEmpty()) {
            checkLink(linkText.value!!)
            clearInput()
        }
    }

    fun checkLink(url: String) {
        compositeDisposable.add(
            service.checkLinkRx(url)
                .subscribeOnBackgroundObserveOnMain()
                .doOnSubscribe { isProgressInProcess.postValue(true) }
                .doFinally { isProgressInProcess.value = false }
                .subscribe({ response ->
                    when (response.code()) {
                        200 -> {
                            truthCount.value = "True ${round(response.body()?.siteStat!! * 100).toInt()}%"
                        }
                        else -> {
                            eventCall.value = INVALID_LINK_ERROR
                        }
                    }
                }, {
                    eventCall.value = INVALID_LINK_ERROR
                })
        )
    }

    fun clearInput() {
        truthCount.value = "0%"
        linkText.value = ""
    }

    companion object {
        const val INVALID_LINK_ERROR = 1
    }
}