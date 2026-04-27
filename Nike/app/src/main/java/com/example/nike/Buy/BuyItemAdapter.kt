package com.example.nike.Buy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.Items.BuyItemData
import com.example.nike.R
import com.example.nike.databinding.BuyItemBinding

class BuyItemAdapter(private var itemList: MutableList<BuyItemData>,
                     private val onHeartClicked: (BuyItemData) -> Unit, //위시하트 추가
                     private val onVisitClicked: (BuyItemData) -> Unit) :
        RecyclerView.Adapter<BuyItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = BuyItemBinding.inflate(
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

        //하트 뒤집기
        holder.binding.ivHeaert.setOnClickListener {
            onHeartClicked(nowItem)
            //직접 하지말고 넘기기(Delegate 패턴)
//            nowItem.isWishlisted = !nowItem.isWishlisted
//            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemViewHolder(val binding: BuyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BuyItemData) {
            binding.ivItem.setImageResource(item.imageRes)
            binding.tvBestSell.text = item.bestSell
            binding.tvName.text = item.name
            binding.tvExplan.text = item.explan
            binding.tvPrice.text = item.price

            //하트 (얜 그대로)
            if (item.isWishlisted) {
                binding.ivHeaert.setImageResource(R.drawable.icon)
            } else {
                binding.ivHeaert.setImageResource(R.drawable.icon__1_)
            }
        }
    }

    //데이터 수정 함수 (AI설명: 데이터 변경 후 화면에 보이게 수정해줌)
    fun updateList(newList: MutableList<BuyItemData>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }
}