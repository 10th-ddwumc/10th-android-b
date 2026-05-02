package com.example.nike.Buy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nike.R
import com.example.nike.View.Ui.Fragment.Buy.BuyAllItemFragment
import com.example.nike.databinding.BuyFragmentBinding
import com.google.android.material.tabs.TabLayout

class BuyFragment : Fragment(R.layout.buy_fragment) {

    private lateinit var binding: BuyFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = BuyFragmentBinding.bind(view)

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.buyTabContainer, BuyAllItemFragment())
                .commit()
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val fragment = when (tab?.position) {
                    0 -> BuyAllItemFragment()
                    1 -> TopTshirtsFragment()
                    2 -> SaleFragment()
                    else -> BuyAllItemFragment()
                }

                childFragmentManager.beginTransaction()
                    .replace(R.id.buyTabContainer, fragment)
                    .commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}