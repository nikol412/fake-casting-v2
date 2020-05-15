package ru.nikol.fakecasting.common.extension

import io.reactivex.Single
import io.reactivex.SingleSource
import ru.nikol.fakecasting.data.network.model.NoConnection
import java.util.concurrent.TimeUnit

fun <T> Single<T>.addTestDelay(): Single<T> = this.delay(2, TimeUnit.SECONDS)

fun <T> Single<T>.subscribeOnBackgroundObserveOnMain() =
    this.subscribeOn(io.reactivex.schedulers.Schedulers.io())
        .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())


fun <T> Single<T>.onErrorReturnNoConnectionError(): Single<T> {
    return this.onErrorResumeNext {
        SingleSource { observer ->
            observer.onError(NoConnection())
        }
    }
}