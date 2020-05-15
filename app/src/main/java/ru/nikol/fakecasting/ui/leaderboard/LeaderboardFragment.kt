package ru.nikol.fakecasting.ui.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ru.nikol.fakecasting.R
import ru.nikol.fakecasting.databinding.FragmentLeaderboardBinding

class LeaderboardFragment : Fragment() {

    private lateinit var leaderboardViewModel: LeaderboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        leaderboardViewModel =
            ViewModelProviders.of(this).get(LeaderboardViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentLeaderboardBinding>(
            inflater,
            R.layout.fragment_leaderboard,
            container,
            false
        )
        binding.viewModel = leaderboardViewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}
