package com.example.nike

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

class ItemListFragment : Fragment() {


    override fun onViewCreated(view : View, savedInstanceState : Bundle?){
        super.onViewCreated(view, savedInstanceState)

        //데이터 목록 생성
        val itemDataList = mutableListOf<ItemData>(
            ItemData(R.drawable.image_1_2, "Nike Air Force 1 '07", "US$115"),
            ItemData(R.drawable.image_2_1, "Nike Everday Plus Cushioned", "US$10"),
            ItemData(R.drawable.image_3_1,"신발2", "US00$"),
            ItemData(R.drawable.image_4, "Air Jordan XXXVI", "US$185"),
            ItemData(R.drawable.image_4_1, "양말2", "US$00"),
            ItemData(R.drawable.image_5, "양말3", "US$00"),
            ItemData(R.drawable.image_6, "Jordan Essentials", "US$60")
        )

        //어뎁터 만들기
        val adapter = HomeItemAdapter(itemDataList,
            onVisitClicked = {
                friend -> Toast.makeText(context, "${friend.name}에게 방문하기", Toast.LENGTH_SHORT).show()
            })
    }
}