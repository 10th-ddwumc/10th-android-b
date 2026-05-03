package ddwu.com.mobile.week02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.week02.databinding.ItemProductBinding

class ProductAdapter( val productList: MutableList<ProductData>,
                     private val showHeart: Boolean = true,
                     private val onHeartClicked: (ProductData,Int) -> Unit = {_,_ -> })
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductData){
            binding.productimg.setImageResource(product.image)
            binding.productName.text = product.name
            binding.productPrice.text = product.price

            if (product.description.isEmpty()) {
                binding.productDesc.visibility = View.GONE
            } else {
                binding.productDesc.visibility = View.VISIBLE
                binding.productDesc.text = product.description
            }

            if (showHeart) { binding.ivHeart.visibility = View.VISIBLE
                if (product.isLiked) {
                    binding.ivHeart.setImageResource(R.drawable.heart_filled)
                } else {
                    binding.ivHeart.setImageResource(R.drawable.heart_empty)
                }
                binding.ivHeart.setOnClickListener { onHeartClicked(product, adapterPosition) }
            } else {
                binding.ivHeart.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        val nowProduct = productList[position]
        holder.bind(nowProduct)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateList(newList: MutableList<ProductData>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }

}