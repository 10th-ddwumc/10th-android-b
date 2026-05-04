package com.example.nike.Profit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nike.R
import com.example.nike.databinding.ProfitFragmentBinding
import com.example.nike.View.Ui.Adapter.FollowingAdapter
import com.example.nike.View.Ui.Base.ProfitViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfitFragment : Fragment(R.layout.profit_fragment) {

    private lateinit var binding: ProfitFragmentBinding
    private lateinit var followingAdapter: FollowingAdapter
    private lateinit var viewModel: ProfitViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ProfitViewModel::class.java]

        binding = ProfitFragmentBinding.bind(view)

        followingAdapter = FollowingAdapter(emptyList())

        binding.followingRecyclerView?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.followingRecyclerView?.adapter = followingAdapter

        binding.btRe.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fragmentContainer, ReProfitFragment())
                .addToBackStack(null)
                .commit()
        }

        observeData()

        viewModel.loadMyProfile()
        viewModel.loadFollowingList()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.profile.collect { user ->
                if (user != null) {
                    binding.txName.text = "${user.first_name} ${user.last_name}"

                    Glide.with(requireContext())
                        .load(user.avatar)
                        .into(binding.imProfile)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.followingList.collect { users ->
                followingAdapter.updateList(users)
            }
        }
    }
}