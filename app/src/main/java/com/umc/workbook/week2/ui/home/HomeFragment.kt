package com.umc.workbook.week2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.workbook.week2.ui.home.adapter.NewProductAdapter
import com.umc.workbook.week2.ui.home.adapter.NewProductDelegate
import com.umc.workbook.week2.data.NewProductData
import com.umc.workbook.week2.R
import com.umc.workbook.week2.data.HomeDataStore
import com.umc.workbook.week2.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), NewProductDelegate {
    lateinit var binding: FragmentHomeBinding
    private lateinit var homeDataStore: HomeDataStore

    override fun onItemClicked(product: NewProductData) {
        findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeDataStore = HomeDataStore(requireContext())

        val adapter = NewProductAdapter(
            productList = mutableListOf(),
            delegate = this
        )

        binding.homeRv.adapter = adapter
        binding.homeRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        lifecycleScope.launch {
            homeDataStore.getNewProducts().collect { products ->
                if(products.isEmpty()){ //없으면 추가
                    val newProductList = mutableListOf(
                        NewProductData(R.drawable.new_product1, "Air Jordan XXXVI", "US\$185"),
                        NewProductData(R.drawable.new_product2, "Nike Air Force 1 '07", "US\$115")
                    )
                    homeDataStore.saveNewProducts(newProductList)
                } else {
                    adapter.updateList(products)
                }
            }
        }
    }
}