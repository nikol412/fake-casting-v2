package ru.nikol.fakecasting.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.okButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.toast
import ru.nikol.fakecasting.R
import ru.nikol.fakecasting.databinding.FragmentHomeBinding
import ru.nikol.fakecasting.ui.base.BaseFragment
import ru.nikol.fakecasting.ui.base.BaseVM

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun baseViewModel(): BaseVM = viewModel

    override fun layoutRes() = R.layout.fragment_home

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding :FragmentHomeBinding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventCall.observe(viewLifecycleOwner, Observer {
            when(it){
                HomeViewModel.INVALID_LINK_ERROR -> {
                    alert {
                        title = "Error"
                        message = "Something went wrong, try another Url"
                        okButton { }
                    }.show()
                }
            }
        })
        return binding.root
    }
}
