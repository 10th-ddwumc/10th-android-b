package com.umc.workbook.week2.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.umc.workbook.week2.databinding.FragmentShopBinding
import com.umc.workbook.week2.ui.shop.sale.SaleFragment
import com.umc.workbook.week2.ui.shop.shoes.AllFragment
import com.umc.workbook.week2.ui.shop.tops.TopsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopFragment : Fragment() {
    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 3
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> AllFragment()
                    1 -> TopsFragment()
                    2 -> SaleFragment()
                    else -> AllFragment()
                }
            }
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "전체"
                1 -> "Tops & T-Shirts"
                2 -> "sale"
                else -> ""
            }
        }.attach()
    }
}