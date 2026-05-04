package com.example.nike.Wish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.com.example.nike.data.model.WishItemData
import com.example.nike.Product.ProductFragment
import com.example.nike.R
import com.example.nike.View.Ui.Adapter.WishItemAdapter
import com.example.nike.data.local.ItemDataManager
import com.example.nike.databinding.WishFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

@AndroidEntryPoint
class WishFragment : Fragment(R.layout.wish_fragment) {

    @Inject
    lateinit var dataStoreManager: ItemDataManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = WishFragmentBinding.bind(view)

        val adapter = WishItemAdapter(emptyList()) { product ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fragmentContainer, ProductFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.wishListRecycleView.adapter = adapter
        binding.wishListRecycleView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getBuyItems().collect { buyList ->

                val wishList = buyList
                    .filter { it.isWishlisted }
                    .map { item ->
                        WishItemData(
                            id = item.id,
                            imageRes = item.imageRes,
                            name = item.name,
                            price = item.price,
                            explan = item.explan
                        )
                    }

                adapter.updateList(wishList)
            }
        }
    }
}