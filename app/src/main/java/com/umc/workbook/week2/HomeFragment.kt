package com.umc.workbook.week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.workbook.week2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

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

        val newProductList = mutableListOf(
            ProductData(R.drawable.new_product1, "Air Jordan XXXVI", "US\$185"),
            ProductData(R.drawable.new_product2, "Nike Air Force 1 '07", "US\$115")
        )

        val adapter = NewProductAdapter(productList = newProductList,
            onItemClicked = { product ->
                findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
            }
        )

        binding.homeRv.adapter = adapter
        binding.homeRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }
}