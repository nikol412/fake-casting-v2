package ru.nikol.fakecasting.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import io.reactivex.disposables.CompositeDisposable


abstract class BaseVM : ViewModel() {

    lateinit var navController: NavController

    val isProgressInProcess = MutableLiveData(false)

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun navigateTo(actionId: Int) {
        navController.navigate(actionId)
    }

    fun navigateUp() {
        navController.navigateUp()
    }
}