package com.umc.workbook.week2.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.umc.workbook.week2.R
import com.umc.workbook.week2.ui.shop.adapter.ShopProductAdatper
import com.umc.workbook.week2.model.ShopProductData
import com.umc.workbook.week2.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    lateinit var binding : FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shopProductList = mutableListOf(
            ShopProductData(
                R.drawable.product1, "Nike Everyday Plus Cushioned",
                "Training Ankle Socks (6 Pairs)", "5 Colours", "US\$10", isBestSeller = false
            ),
            ShopProductData(
                R.drawable.product3, "Nike Elite Crew",
                "Basketball Socks", "7 Colours", "US\$16", isBestSeller = false
            ),
            ShopProductData(
                R.drawable.product2, "Nike Air Force 1 '07",
                "Women's Shoes", "5 Colours", "US\$115", isBestSeller = true
            ),
            ShopProductData(
                R.drawable.product2, "Jordan ENike Air Force 1 '07ssentials",
                "Men's Shoes", "2 Colours", "US\$115", isBestSeller = true
            ),
        )

        val adapter = ShopProductAdatper(
            productList = shopProductList,
            onItemClicked = { product ->
                findNavController().navigate(R.id.action_shopFragment_to_productDetailFragment)
            }
        )

        binding.shopRv.adapter = adapter
        binding.shopRv.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}