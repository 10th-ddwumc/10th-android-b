package com.umc.workbook.week2.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.workbook.week2.data.UserData
import com.umc.workbook.week2.databinding.ItemFollowingBinding

class FollowingAdapter(
        private val itemList: MutableList<UserData>,
) : RecyclerView.Adapter<FollowingViewHolder>(){
        override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
        ): FollowingViewHolder {
                val binding = ItemFollowingBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                )
                return FollowingViewHolder(binding)
        }

        override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
                holder.bind(itemList[position])
        }

        override fun getItemCount() = itemList.size

        fun updateList(newList: List<UserData>) {
                itemList.clear()
                itemList.addAll(newList)
                notifyDataSetChanged()
        }

}

class FollowingViewHolder(val binding: ItemFollowingBinding):
        RecyclerView.ViewHolder(binding.root){
                fun bind(item: UserData){
                        Glide.with(binding.root)
                                .load(item.avatar)
                                .into(binding.ivFollowingProfile)
                }
        }