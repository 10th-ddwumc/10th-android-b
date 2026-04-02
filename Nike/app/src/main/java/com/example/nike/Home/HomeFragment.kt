package com.example.nike.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.Coroutine.HomeItemDataManager
import com.example.nike.Items.HomeItemData
import com.example.nike.Product.ProductFragment
import com.example.nike.R
import com.example.nike.databinding.HomeFragmentBinding
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate

class HomeFragment : Fragment(R.layout.home_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //tvWeather에 오늘 날짜 입력하기(LocalDate(time)사용)
        val tvWeather = view.findViewById<TextView>(R.id.tvWeather)

        val today = LocalDate.now()
        val dayOfWeek = when (today.dayOfWeek) {
            DayOfWeek.MONDAY -> "월"
            DayOfWeek.TUESDAY -> "화"
            DayOfWeek.WEDNESDAY -> "수"
            DayOfWeek.THURSDAY -> "목"
            DayOfWeek.FRIDAY -> "금"
            DayOfWeek.SATURDAY -> "토"
            DayOfWeek.SUNDAY -> "일"
        }
        val result = "${today.monthValue}월 ${today.dayOfMonth}일 ${dayOfWeek}요일"
        tvWeather.text = result

//        //데이터 불러오기
//        val itemList = mutableListOf(
//            HomeItemData("H_item1", R.drawable.image_4, "Air Jordan XXXVI", "US$185"),
//            HomeItemData("H_item2", R.drawable.image_2_1, "Nike Everday Plus Cushioned", "US$10")
//            )

        val binding = HomeFragmentBinding.bind(view)

        //데이터 불러오기
         var dataStoreManager = HomeItemDataManager(requireContext())
         lateinit var adapter: HomeItemAdapter


        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getHomeItem().collect { list ->
                if (list.isEmpty()) {
                    dataStoreManager.saveHomeItem(
                        listOf(
                            HomeItemData("H_item1", R.drawable.image_4, "Air Jordan XXXVI", "US$185"
                            ),
                            HomeItemData("H_item2", R.drawable.image_2_1, "Nike Everyday Plus Cushioned", "US$10")
                        )
                    )
                } else {
                    adapter.updateList(list)
                }
            }
        }
            //이동할 Fragment 작성
            val fragment = ProductFragment()
        adapter = HomeItemAdapter(emptyList()) { product ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }

        // RecyclerView 연결
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }
}