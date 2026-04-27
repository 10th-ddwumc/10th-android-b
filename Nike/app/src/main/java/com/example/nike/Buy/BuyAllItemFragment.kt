package com.example.nike.Buy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.Coroutine.BuyItemDataManager
import com.example.nike.Items.BuyItemData
import com.example.nike.Items.WishItemData
import com.example.nike.Product.ProductFragment
import com.example.nike.R
import com.example.nike.databinding.BuyAllItemsFragmentBinding
import com.example.nike.databinding.BuyFragmentBinding
import com.example.nike.datastore.WishItemDataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class BuyAllItemFragment : Fragment(R.layout.buy_all_items_fragment) {
        private lateinit var binding: BuyAllItemsFragmentBinding
        private lateinit var dataStoreManager: BuyItemDataManager
        private lateinit var wishDataStoreManager: WishItemDataStoreManager

        private lateinit var adapter: BuyItemAdapter

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val buyItems = listOf(
                BuyItemData("B_item1", R.drawable.image_1_2, "Nike Air Force 1 '07", "US$115","Training Ankle Socks (6pairs)\n5colors"),
                BuyItemData("B_item2", R.drawable.image_5, "Nike Elite Crew", "US$16", "Basketball Socks\n7colors"),
                BuyItemData("B_item3", R.drawable.image_2_1, "Nike Everyday Plus Cushioned", "US$10", "Women's Shoes\n5colors"),
                BuyItemData("B_item4", R.drawable.image_3_1,"Jordan Essentials", "US$115", "Men’s Fleece Pullover Hoodie\n2colors"),
                BuyItemData("B_item5", R.drawable.image_4, "Air Jordan XXXVI", "US$185"),
                BuyItemData("B_item6", R.drawable.image_4_1, "양말2", "US$00"),
                BuyItemData("B_item7", R.drawable.image_6, "Jordan Essentials", "US$60")
            )

            binding = BuyAllItemsFragmentBinding.bind(view)
            dataStoreManager = BuyItemDataManager(requireContext())
            wishDataStoreManager = WishItemDataStoreManager(requireContext())

            adapter = BuyItemAdapter(
                mutableListOf(),
                onHeartClicked = { product ->
                    viewLifecycleOwner.lifecycleScope.launch {
                        val wasWishlisted = product.isWishlisted

                        dataStoreManager.toggleWishItem(product.id)

                        val currentWishList = wishDataStoreManager.getWishItem().first().toMutableList()

                        if (wasWishlisted) {
                            currentWishList.removeAll { it.id == product.id }
                        } else {
                            currentWishList.add(
                                WishItemData(
                                    id = product.id,
                                    imageRes = product.imageRes,
                                    name = product.name,
                                    price = product.price,
                                    explan = product.explan,
                                    bestSell = product.bestSell
                                )
                            )
                        }

                        wishDataStoreManager.saveWishItem(currentWishList)
                    }
                },
                onVisitClicked = { product ->
                    val fragment = ProductFragment()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            )

            binding.recyclerList.adapter = adapter
            binding.recyclerList.layoutManager = GridLayoutManager(requireContext(), 2)

            viewLifecycleOwner.lifecycleScope.launch {
                // 처음 한 번만 JSON 저장
                val savedList = dataStoreManager.getBuyItem().first()
                if (savedList.isEmpty()) {
                    dataStoreManager.saveBuyItem(buyItems)
                }

                // 저장된 데이터 계속 관찰
                dataStoreManager.getBuyItem().collect { list ->
                    adapter.updateList(list.toMutableList())
                }
            }


        }
    }