package com.example.nike.Wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.Items.BuyItemData
import com.example.nike.Items.HomeItemData
import com.example.nike.Items.WishItemData
import com.example.nike.databinding.WishItemBinding

class WishItemAdapter(private var itemList: List<WishItemData>,
                      private val onVisitClicked: (WishItemData) -> Unit) :
        RecyclerView.Adapter<WishItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = WishItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ItemViewHolder(binding)
    }

    //실제 데이터 입력
    override fun onBindViewHolder(
        holder : ItemViewHolder,
        position : Int) {
        val nowItem =itemList[position]
        holder.bind(nowItem)

        // 아이템 전체 클릭 → Fragment로 전달
        holder.itemView.setOnClickListener {
            onVisitClicked(nowItem)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateList(newList: List<WishItemData>) {
        itemList = newList
        notifyDataSetChanged()
    }
    class ItemViewHolder(val binding: WishItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WishItemData) {
            binding.ivItem.setImageResource(item.imageRes)
            binding.tvName.text = item.name
            binding.tvExplan.text = item.explan
            binding.tvPrice.text = item.price
        }
    }

}