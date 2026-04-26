package com.example.nike.Profit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nike.databinding.ItemFollowingBinding

class FollowingAdapter(
    private var userList: List<UserData>
) : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    inner class FollowingViewHolder(
        val binding: ItemFollowingBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val user = userList[position]

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .into(holder.binding.ivFollowingProfile)
    }

    override fun getItemCount(): Int = userList.size

    fun updateList(newList: List<UserData>) {
        userList = newList
        notifyDataSetChanged()
    }
}