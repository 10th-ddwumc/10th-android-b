package com.example.nike.Home

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.Product.ProductFragment
import com.example.nike.R
import com.example.nike.View.Ui.Adapter.HomeItemAdapter
import com.example.nike.View.Ui.Base.HomeViewModel
import com.example.nike.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate

@AndroidEntryPoint
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

        //이동할 Fragment 작성
        val fragment = ProductFragment()
        val adapter = HomeItemAdapter(emptyList()) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragmentContainer, ProductFragment())
                .addToBackStack(null)
                .commit()
        }

        // RecyclerView 연결
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.homeItems.collect { list ->
                adapter.updateList(list)
            }
        }
    }
}