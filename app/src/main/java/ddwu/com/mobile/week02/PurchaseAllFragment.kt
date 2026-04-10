package ddwu.com.mobile.week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import ddwu.com.mobile.week02.databinding.FragmentPurchaseAllBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PurchaseAllFragment : Fragment() {

    private var _binding: FragmentPurchaseAllBinding? = null
    private val binding get() = _binding!!

    private val productList = mutableListOf(
        ProductData("Nike Everyday Plus Cushioned", "US$10", R.drawable.everyday_cushioned, "Training Ankle Socks\n5 Colours"),
        ProductData("Nike Elite Crew", "US$16", R.drawable.elite_crew, "Basketball Socks\n7 Colours"),
        ProductData("Nike Air Force 1 '07", "US$115", R.drawable.airforce_women, "Women's Shoes\n5 Colours"),
        ProductData("Jordan ENike Air Force", "US$115", R.drawable.airforce_men, "Men's Shoes\n2 Colours")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPurchaseAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            ProductDataStore.getPurchaseProducts(requireContext()).collect { products ->
                val currentList = if (products.isEmpty()) {
                    ProductDataStore.savePurchaseProducts(requireContext(), productList)
                    productList
                } else {
                    products.toMutableList()
                }

                val adapter = ProductAdapter(currentList, true) { product, position ->
                    lifecycleScope.launch {
                        val updatedProduct = product.copy(isLiked = !product.isLiked)
                        currentList[position] = updatedProduct

                        ProductDataStore.savePurchaseProducts(requireContext(), currentList)

                        val wishProducts = ProductDataStore.getWishlistProducts(requireContext()).first()
                        val newWishlist = wishProducts.toMutableList()
                        if (updatedProduct.isLiked) {
                            if (!newWishlist.any {it.name == updatedProduct.name}) {
                                newWishlist.add(updatedProduct)
                            }
                        } else {
                            newWishlist.removeAll {it.name == updatedProduct.name}
                        }
                        ProductDataStore.saveWishlistProducts(requireContext(), newWishlist)
                    }
                }
                binding.rvPurchase.adapter = adapter
                binding.rvPurchase.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}