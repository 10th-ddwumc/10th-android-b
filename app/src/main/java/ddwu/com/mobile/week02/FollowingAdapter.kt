package ddwu.com.mobile.week02

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ddwu.com.mobile.week02.databinding.ItemFollowingBinding

class FollowingAdapter(private val userList: MutableList<UserData>) :
    RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    inner class FollowingViewHolder(val binding: ItemFollowingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserData) {
            Glide.with(binding.root.context)
                .load(user.avatar)
                .into(binding.followingProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding =
            ItemFollowingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size
}