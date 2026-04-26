package com.example.nike.Profit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.R
import com.example.nike.databinding.ProfitFragmentBinding
import com.bumptech.glide.Glide
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
        RetrofitClient.api.getUser(1).enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    val user = response.body()?.data ?: return

                    binding.txName.text = "${user.first_name} ${user.last_name}"

                    Glide.with(requireContext())
                        .load(user.avatar)
                        .into(binding.imProfile)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun loadFollowingList() {
        RetrofitClient.api.getUserList().enqueue(object : Callback<UserListResponse> {
            override fun onResponse(
                call: Call<UserListResponse>,
                response: Response<UserListResponse>
            ) {
                if (response.isSuccessful) {
                    val users = response.body()?.data ?: emptyList()

                    followingAdapter.updateList(users)

                   // binding.tvFollowingTitle?.text = "팔로잉 (${users.size})"
                }
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}