package com.example.nike

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.databinding.BuyFragmentBinding
import com.example.nike.databinding.HomeFragmentBinding
import com.example.nike.databinding.WishFragmentBinding
import com.example.nike.databinding.WishItemBinding

class WishFragment : Fragment(R.layout.wish_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //데이터 목록 생성
    val itemDataList = mutableListOf<ItemData>(
        ItemData(R.drawable.image_6, "Jordan Essentials", "US$60"),
        ItemData(R.drawable.image_1_2, "Nike Air Force 1 '07", "US$110","Training Ankle Socks (6pairs)\n5colors"),
        )

        val binding = WishFragmentBinding.bind(view)

        val adapter = WishItemAdapter(itemDataList) { product ->

            //이동할 Fragment 작성
            val fragment = ProductFragment()

            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }
    binding.wishListRecycleView.adapter = adapter
    binding.wishListRecycleView.layoutManager =
    //2개씩 보이게
        GridLayoutManager(requireContext(), 2)

}
}