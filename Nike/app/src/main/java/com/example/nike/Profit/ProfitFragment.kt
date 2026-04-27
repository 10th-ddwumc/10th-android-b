package com.example.nike.Profit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.R
import com.example.nike.databinding.ProfitFragmentBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class ProfitFragment : Fragment(R.layout.profit_fragment) {

    private lateinit var binding: ProfitFragmentBinding
    private lateinit var followingAdapter: FollowingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        loadMyProfile()
        loadFollowingList()
    }

    private fun loadMyProfile() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = RetrofitClient.api.getUser(1)
                val user = response.data

                binding.txName.text = "${user.first_name} ${user.last_name}"

                Glide.with(requireContext())
                    .load(user.avatar)
                    .into(binding.imProfile)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun loadFollowingList() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = RetrofitClient.api.getUserList()
                val users = response.data.take(3)

                followingAdapter.updateList(users)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}