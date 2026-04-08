package com.example.nike

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
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
    }
}