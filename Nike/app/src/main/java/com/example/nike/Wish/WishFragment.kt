package com.example.nike.Wish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.Items.WishItemData
import com.example.nike.Product.ProductFragment
import com.example.nike.R
import com.example.nike.databinding.WishFragmentBinding
import com.example.nike.datastore.WishItemDataStoreManager
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class WishFragment : Fragment(R.layout.wish_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = WishFragmentBinding.bind(view)
        val dataStoreManager = WishItemDataStoreManager(requireContext())

        val adapter = WishItemAdapter(emptyList<WishItemData>()) { product ->
            val fragment = ProductFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }

        binding.wishListRecycleView.adapter = adapter
        binding.wishListRecycleView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getWishItem().collect { list ->
                adapter.updateList(list)
            }
        }
    }
}