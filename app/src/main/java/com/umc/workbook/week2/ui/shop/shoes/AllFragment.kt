package com.umc.workbook.week2.ui.shop.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.umc.workbook.week2.R
import com.umc.workbook.week2.databinding.FragmentAllBinding
import com.umc.workbook.week2.ui.shop.ShopViewModel
import com.umc.workbook.week2.ui.shop.adapter.ShopProductAdatper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllFragment : Fragment() {

    private lateinit var binding: FragmentAllBinding
    private val viewModel: ShopViewModel by viewModels()
    private lateinit var adapter: ShopProductAdatper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ShopProductAdatper(
            productList = mutableListOf(),
            onItemClicked = {
                findNavController().navigate(R.id.action_shopFragment_to_productDetailFragment)
            },
            onWishClicked = { position, _ ->
                viewModel.toggleWish(position)
            }
        )

        binding.shopRv.adapter = adapter
        binding.shopRv.layoutManager = GridLayoutManager(requireContext(), 2)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    adapter.updateList(state.products)
                }
            }
        }
    }
}