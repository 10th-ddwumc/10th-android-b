package com.umc.workbook.week2.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.umc.workbook.week2.databinding.FragmentProfileBinding
import com.umc.workbook.week2.network.ApiClient
import com.umc.workbook.week2.network.UserRepository
import com.umc.workbook.week2.ui.profile.adapter.FollowingAdapter
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var followingAdapter: FollowingAdapter
    private val repository= UserRepository(ApiClient.userService)
    private val API_KEY = "reqres_9b160204291c4d13a1ff4ef5a677a03c"
    private val TAG = "ProfileFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        fetchUser()
        fetchUserList()
    }

    private fun fetchUser() {
        lifecycleScope.launch {
            repository.getUser(API_KEY)
                .onSuccess { user ->
                    binding.tvNickname.text = "${user.firstName}${user.lastName}"
                    Glide.with(this@ProfileFragment)
                        .load(user.avatar)
                        .into(binding.ivProfile)
                }
                .onFailure { error ->
                    Log.d(TAG, "유저 조회 실패 : ${error.message}")
                }
        }
    }

    private fun fetchUserList() {
        lifecycleScope.launch {
            repository.getUserList(API_KEY)
                .onSuccess { userList ->
                    followingAdapter.updateList(userList)
                }
                .onFailure { error ->
                    Log.d("ProfileFragment", "리스트 조회 실패: ${error.message}")
                }
        }
    }

    private fun setupRecyclerView() {
        followingAdapter = FollowingAdapter(mutableListOf())
        binding.rvFollowing.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = followingAdapter
        }
    }
}