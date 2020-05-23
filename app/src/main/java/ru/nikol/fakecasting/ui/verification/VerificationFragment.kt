package ru.nikol.fakecasting.ui.verification

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import org.jetbrains.anko.okButton
import org.jetbrains.anko.support.v4.alert
import ru.nikol.fakecasting.R
import ru.nikol.fakecasting.databinding.FragmentVerificationBinding
import ru.nikol.fakecasting.ui.base.BaseFragment
import ru.nikol.fakecasting.ui.base.BaseVM

class VerificationFragment : BaseFragment() {

    private val viewModel: VerificationVM by viewModels()

    override fun baseViewModel(): BaseVM = viewModel

    override fun layoutRes() = R.layout.fragment_verification

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val recieveValue = activity?.intent?.getStringExtra(Intent.EXTRA_TEXT)

        recieveValue?.let {
            viewModel.linkText.value = it
        }
        val binding: FragmentVerificationBinding =
            DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventCall.observe(viewLifecycleOwner, Observer {
            when (it) {
                VerificationVM.INVALID_LINK_ERROR -> {
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
