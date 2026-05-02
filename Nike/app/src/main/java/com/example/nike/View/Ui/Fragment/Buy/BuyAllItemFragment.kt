package com.example.nike.View.Ui.Fragment.Buy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.View.Ui.Adapter.BuyItemAdapter
import com.example.nike.Product.ProductFragment
import com.example.nike.R
import com.example.nike.com.example.nike.View.Ui.Base.BuyViewModel
import com.example.nike.databinding.BuyAllItemsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BuyAllItemFragment : Fragment(R.layout.buy_all_items_fragment) {

    private lateinit var binding: BuyAllItemsFragmentBinding
    private lateinit var adapter: BuyItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = BuyAllItemsFragmentBinding.bind(view)
        val viewModel = ViewModelProvider(this)[BuyViewModel::class.java]

        adapter = BuyItemAdapter(
            mutableListOf(),
            onHeartClicked = { product ->
                viewModel.toggleWishItem(product.id)
            },
            onVisitClicked = {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragmentContainer, ProductFragment())
                    .addToBackStack(null)
                    .commit()
            }
        )

        binding.recyclerList.adapter = adapter
        binding.recyclerList.layoutManager = GridLayoutManager(requireContext(), 2)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                adapter.updateList(state.buyItems.toMutableList())
            }
        }
    }
}