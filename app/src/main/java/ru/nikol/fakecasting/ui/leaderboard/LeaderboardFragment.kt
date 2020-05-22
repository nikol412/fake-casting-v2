package ru.nikol.fakecasting.ui.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.support.v4.toast
import ru.nikol.fakecasting.R
import ru.nikol.fakecasting.databinding.FragmentLeaderboardBinding
import ru.nikol.fakecasting.ui.leaderboard.adapter.LeaderboardAdapter

class LeaderboardFragment : Fragment() {

    private lateinit var leaderboardViewModel: LeaderboardViewModel
    private lateinit var leaderboardAdapter: LeaderboardAdapter

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

        leaderboardAdapter =
            LeaderboardAdapter(
                object :
                    LeaderboardAdapter.OnLoadMoreListener {
                    override fun loadMore() {
                        leaderboardViewModel.loadMore()
                    }
                }
            )

        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                leaderboardViewModel.loadMore()
                toast("downloading")
            }
        }

        binding.leaderboardRecyclerview.addOnScrollListener(scrollListener)

        with(binding.leaderboardRecyclerview) {
            layoutManager = LinearLayoutManager(context)
            adapter = leaderboardAdapter
        }

        leaderboardViewModel.allSitesList.observe(viewLifecycleOwner, Observer {
            leaderboardAdapter.setItems(
                leaderboardViewModel.allSitesList.value?.toMutableList())
        })

        return binding.root
    }
}
