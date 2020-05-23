package ru.nikol.fakecasting.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import org.jetbrains.anko.okButton
import org.jetbrains.anko.support.v4.alert

abstract class BaseFragment : Fragment() {

    abstract fun baseViewModel(): BaseVM

    @LayoutRes
    abstract fun layoutRes(): Int

    fun getBaseActivity(): BaseActivity = activity as BaseActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as BaseActivity).setupUI(view)
        subscribeToViewModelObservables()
    }

    protected open fun subscribeToViewModelObservables() {
        with(baseViewModel()) {

        }
    }

}