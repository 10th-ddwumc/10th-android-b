package com.example.nike

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.nike.databinding.BuyFragmentBinding
import com.example.nike.databinding.HomeFragmentBinding

class BuyFragment : Fragment(R.layout.buy_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //데이터 목록 생성
        val itemDataList = mutableListOf<ItemData>(
            ItemData(R.drawable.image_1_2, "Nike Air Force 1 '07", "US$115","Training Ankle Socks (6pairs)\n5colors"),
            ItemData(R.drawable.image_5, "Nike Elite Crew", "US$16", "Basketball Socks\n7colors"),
            ItemData(R.drawable.image_2_1, "Nike Everday Plus Cushioned", "US$10", "Women's Shoses\n5colors"),
            ItemData(R.drawable.image_3_1,"Jordan ENike Air Force 1 '07ssentials", "US$115", "Men’s Fleece Pullover Hoodie\n2colors"),
            ItemData(R.drawable.image_4, "Air Jordan XXXVI", "US$185"),
            ItemData(R.drawable.image_4_1, "양말2", "US$00"),
            ItemData(R.drawable.image_6, "Jordan Essentials", "US$60")
        )

        val binding = BuyFragmentBinding.bind(view)

        val adapter = BuyItemAdapter(itemDataList) { product ->

            //이동할 Fragment 작성
            val fragment = ProductFragment()

            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }

        binding.recyclerList.adapter = adapter
        binding.recyclerList.layoutManager =
                //2개씩 보이게
            GridLayoutManager(requireContext(), 2)
    }
}