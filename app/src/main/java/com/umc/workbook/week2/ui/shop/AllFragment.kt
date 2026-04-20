package com.umc.workbook.week2.ui.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.umc.workbook.week2.R
import com.umc.workbook.week2.data.ShopDataStore
import com.umc.workbook.week2.data.ShopProductData
import com.umc.workbook.week2.databinding.FragmentAllBinding
import com.umc.workbook.week2.ui.shop.adapter.ShopProductAdatper
import kotlinx.coroutines.launch

class AllFragment : Fragment() {
    lateinit var binding: FragmentAllBinding
    private lateinit var shopDataStore: ShopDataStore
    private lateinit var adapter: ShopProductAdatper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shopDataStore = ShopDataStore(requireContext())

        adapter = ShopProductAdatper(
            productList = mutableListOf(),
            onItemClicked = {
                findNavController().navigate(R.id.action_shopFragment_to_productDetailFragment)
            },
            onWishClicked = { _, _ ->
                lifecycleScope.launch {
                    shopDataStore.saveShopProducts(adapter.getCurrentList())
                }
            }
        )

        binding.shopRv.adapter = adapter
        binding.shopRv.layoutManager = GridLayoutManager(requireContext(), 2)

        lifecycleScope.launch {
            shopDataStore.getShopProducts().collect { products ->
                if (products.isEmpty()) {
                    val shopProductList = mutableListOf(
                        ShopProductData(
                            R.drawable.product1, "Nike Everyday Plus Cushioned",
                            "Training Ankle Socks (6 Pairs)", "5 Colours", "US\$10"
                        ),
                        ShopProductData(R.drawable.product3, "Nike Elite Crew",
                            "Basketball Socks", "7 Colours", "US\$16"),
                        ShopProductData(R.drawable.product2, "Nike Air Force 1 '07",
                            "Women's Shoes", "5 Colours", "US\$115", isBestSeller = true),
                        ShopProductData(R.drawable.product2, "Jordan Air Force 1 Essentials",
                            "Men's Shoes", "2 Colours", "US\$115", isBestSeller = true),
                    )
                    shopDataStore.saveShopProducts(shopProductList)
                } else {
                    adapter.updateList(products)
                }
            }
        }
    }
}