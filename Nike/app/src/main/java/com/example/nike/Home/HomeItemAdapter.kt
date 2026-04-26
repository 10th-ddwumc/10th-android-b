package com.example.nike.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.Items.HomeItemData
import com.example.nike.databinding.HomeItemBinding

class HomeItemAdapter(private var itemList: List<HomeItemData>,
                      private val onVisitClicked: (HomeItemData) -> Unit) :
        RecyclerView.Adapter<HomeItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = HomeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ItemViewHolder(binding)
    }

    //실제 데이터 입력
    override fun onBindViewHolder(
        holder : ItemViewHolder,
        position : Int) {
        val nowItem = itemList[position]
        holder.bind(nowItem)

        // 아이템 전체 클릭 → Fragment로 전달
        holder.itemView.setOnClickListener {
            onVisitClicked(nowItem)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateList(newList: List<HomeItemData>) {
        itemList = newList
        notifyDataSetChanged()
    }
    class ItemViewHolder(val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeItemData) {
            binding.ivItem.setImageResource(item.imageRes)
            binding.tvName.text = item.name
            binding.tvPrice.text = item.price
        }
    }
}