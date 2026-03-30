package com.umc.workbook.week2.ui.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.umc.workbook.week2.R
import com.umc.workbook.week2.ui.wishlist.adapter.WishlistAdapter
import com.umc.workbook.week2.databinding.FragmentWishlistBinding
import com.umc.workbook.week2.model.WishlistData

class WishlistFragment : Fragment() {

    lateinit var binding: FragmentWishlistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishlistBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wishList = mutableListOf(
            WishlistData(R.drawable.product1, "Air Jordan 1 Mid", null, null, "US\$125"),
            WishlistData(
                R.drawable.product3, "Nike Everyday Plus Cushioned",
                "Training Ankle Socks (6 Pairs)", "5 Colours", "US\$10"
            ),
        )

        val adapter = WishlistAdapter(
            productList = wishList,
            onItemClicked = { product ->
                findNavController().navigate(R.id.action_wishlistFragment_to_productDetailFragment)
            }
        )

        binding.wishRv.adapter = adapter
        binding.wishRv.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}