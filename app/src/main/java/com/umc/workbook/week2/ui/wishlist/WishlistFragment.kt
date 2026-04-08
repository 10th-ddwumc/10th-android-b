package com.umc.workbook.week2.ui.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.umc.workbook.week2.R
import com.umc.workbook.week2.data.ShopDataStore
import com.umc.workbook.week2.ui.wishlist.adapter.WishlistAdapter
import com.umc.workbook.week2.databinding.FragmentWishlistBinding
import com.umc.workbook.week2.data.WishlistData
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {

    lateinit var binding: FragmentWishlistBinding
    private lateinit var shopDataStore: ShopDataStore
    private lateinit var adapter: WishlistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishlistBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shopDataStore = ShopDataStore(requireContext())

//        val wishList = mutableListOf(
//            WishlistData(R.drawable.product1, "Air Jordan 1 Mid", null, null, "US\$125"),
//            WishlistData(
//                R.drawable.product3, "Nike Everyday Plus Cushioned",
//                "Training Ankle Socks (6 Pairs)", "5 Colours", "US\$10"
//            ),
//        )

        adapter = WishlistAdapter(
            productList = mutableListOf(),
            onItemClicked = { product ->
                findNavController().navigate(R.id.action_wishlistFragment_to_productDetailFragment)
            }
        )

        binding.wishRv.adapter = adapter
        binding.wishRv.layoutManager = GridLayoutManager(requireContext(), 2)

        lifecycleScope.launch {
            shopDataStore.getShopProducts().collect { products ->
                // 하트 채워진 것만
                val wishlist = products.filter { it.isWishlisted }
                adapter.updateList(wishlist)
            }
        }
    }
}