package ru.nikol.fakecasting.ui.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.nikol.fakecasting.R
import ru.nikol.fakecasting.databinding.FragmentLeaderboardBinding
import ru.nikol.fakecasting.ui.base.BaseFragment
import ru.nikol.fakecasting.ui.base.BaseVM
import ru.nikol.fakecasting.ui.leaderboard.adapter.LeaderboardAdapter

class LeaderboardFragment : BaseFragment() {

    private lateinit var leaderboardAdapter: LeaderboardAdapter

    private val viewModel: LeaderboardViewModel by viewModels()

    override fun baseViewModel(): BaseVM = viewModel

    override fun layoutRes(): Int = R.layout.fragment_leaderboard

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentLeaderboardBinding>(
            inflater,
            R.layout.fragment_leaderboard,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        leaderboardAdapter =
            LeaderboardAdapter(
                object :
                    LeaderboardAdapter.OnLoadMoreListener {
                    override fun loadMore() {
                        viewModel.loadMore()
                    }
                }
            )

        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                viewModel.loadMore()
            }
        }

        binding.leaderboardRecyclerview.addOnScrollListener(scrollListener)

        with(binding.leaderboardRecyclerview) {
            layoutManager = LinearLayoutManager(context)
            adapter = leaderboardAdapter
        }

        viewModel.allSitesList.observe(viewLifecycleOwner, Observer {
            leaderboardAdapter.setItems(
                viewModel.allSitesList.value?.toMutableList()
            )
        })

        return binding.root
    }
}
