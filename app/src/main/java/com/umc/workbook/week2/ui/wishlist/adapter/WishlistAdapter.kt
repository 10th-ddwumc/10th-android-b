package com.umc.workbook.week2.ui.wishlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.workbook.week2.databinding.ItemWishlistBinding
import com.umc.workbook.week2.model.WishlistData

class WishlistAdapter(
    private val productList: MutableList<WishlistData>,
    private val onItemClicked: (WishlistData) -> Unit
) : RecyclerView.Adapter<WishlistViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WishlistViewHolder {
        val binding = ItemWishlistBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return WishlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
        holder.binding.root.setOnClickListener {
            onItemClicked(product)
        }
    }

    override fun getItemCount(): Int = productList.size
}

class WishlistViewHolder(val binding: ItemWishlistBinding)
    : RecyclerView.ViewHolder(binding.root){
        fun bind(product: WishlistData){
            binding.ivShopProduct.setImageResource(product.image)
            binding.tvProductN.text = product.name
            binding.tvPrice2.text = product.price

            if (product.subtitle != null) {
                binding.tvSubtitle.visibility = View.VISIBLE
                binding.tvSubtitle.text = product.subtitle
            } else {
                binding.tvSubtitle.visibility = View.GONE
            }

            if (product.colors != null) {
                binding.tvColour.visibility = View.VISIBLE
                binding.tvColour.text = product.colors
            } else {
                binding.tvColour.visibility = View.GONE
            }
        }
    }