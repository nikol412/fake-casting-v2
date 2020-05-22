package ru.nikol.fakecasting.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.nikol.fakecasting.common.extension.onErrorReturnNoConnectionError
import ru.nikol.fakecasting.common.extension.subscribeOnBackgroundObserveOnMain
import ru.nikol.fakecasting.data.Api
import ru.nikol.fakecasting.data.network.RetrofitRxInstance

class HomeViewModel : ViewModel() {
    val service = RetrofitRxInstance.retrofitInstance!!.create(Api::class.java)
    val textKek: MutableLiveData<String> = MutableLiveData()

    fun sendRequest() {
            service.checkLinkRx("https://www.bbc.com/news/world-europe-isle-of-man-52712482")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOnBackgroundObserveOnMain()
                //.doOnSubscribe { textKek.value = "waiting" }
                .subscribe({
                    textKek.value = it.url
                }, {
                    textKek.value = "wrong"
                })
    }
}