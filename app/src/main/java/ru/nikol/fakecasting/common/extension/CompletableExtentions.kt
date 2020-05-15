package ru.nikol.fakecasting.common.extension

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


fun Completable.addTestDelay() = this.delay(2, TimeUnit.SECONDS)

fun Completable.subscribeOnBackgroundObserveOnMain() =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())