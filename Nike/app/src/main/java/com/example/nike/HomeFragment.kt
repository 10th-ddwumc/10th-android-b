package com.example.nike

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.databinding.HomeFragmentBinding
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

        //데이터 불러오기
        val itemList = mutableListOf(
            ItemData(R.drawable.image_4, "Air Jordan XXXVI", "US$185"),
            ItemData(R.drawable.image_2_1, "Nike Everday Plus Cushioned", "US$10")
            )

        val binding = HomeFragmentBinding.bind(view)

        val adapter = HomeItemAdapter(itemList) { product ->

            //이동할 Fragment 작성
            val fragment = ProductFragment()

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