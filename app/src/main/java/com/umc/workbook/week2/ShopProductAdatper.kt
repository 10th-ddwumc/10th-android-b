package com.umc.workbook.week2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.workbook.week2.databinding.ItemShopProductBinding

class ShopProductAdatper(
    private val productList: MutableList<ShopProductData>,
    private val onItemClicked: (ShopProductData) -> Unit
): RecyclerView.Adapter<ShopProductViewHolder> (){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShopProductViewHolder {
        val binding = ItemShopProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ShopProductViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ShopProductViewHolder,
        position: Int
    ) {
        val product = productList[position]
        holder.bind(product)
        holder.binding.root.setOnClickListener {
            onItemClicked(product)
        }
    }

    override fun getItemCount(): Int = productList.size

}

class ShopProductViewHolder(val binding: ItemShopProductBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(product: ShopProductData){
            binding.ivShopProduct.setImageResource(product.image)
            binding.tvProductN.text = product.name
            binding.tvSubtitle.text = product.subtitle
            binding.tvColour.text = product.colors
            binding.tvPrice2.text = product.price

            if (product.isBestSeller){
                binding.tvBestSeller.visibility = View.VISIBLE
            } else {
                binding.tvBestSeller.visibility = View.GONE
            }

            if (product.isWishlisted){
                binding.ivWish.setImageResource(R.drawable.ic_heart_filled)
            } else {
                binding.ivWish.setImageResource(R.drawable.ic_heart_blank)
            }
        }
    }