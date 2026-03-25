package com.umc.workbook.week2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.workbook.week2.databinding.ItemNewProductBinding

class NewProductAdapter(
    private val productList: MutableList<ProductData>,
    private val delegate: NewProductDelegate
): RecyclerView.Adapter<NewProductViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewProductViewHolder {
        val binding = ItemNewProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
        holder.binding.root.setOnClickListener {
            delegate.onItemClicked(product)
        }
    }

    override fun getItemCount(): Int = productList.size
}

class NewProductViewHolder(val binding: ItemNewProductBinding):
    RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductData){
        binding.ivNewProduct.setImageResource(product.image)
        binding.tvProductName.text = product.name
        binding.tvPrice.text = product.price
    }
}