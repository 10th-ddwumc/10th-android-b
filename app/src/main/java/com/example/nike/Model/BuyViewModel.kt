package com.example.nike.Model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.nike.Screen.Ui.Data.ProductData

class BuyViewModel : ViewModel() {
    //사용할 상태 및 로직 넣기 (MVVM 형식)
    // == 화면에 영향을 주는 것들

    //탭 선택( 0, 1, 2 )
    var selectedTabIndex by mutableIntStateOf(0)
        private set

    fun selectTab(index: Int) {
        selectedTabIndex = index
    }

    //아이템 리스트
    var itemList by mutableStateOf(ProductData.buyProducts)
        private set

    //위시
    fun toggleWish(id: Int) {
        itemList = itemList.map { item ->

            if (item.id == id) {
                item.copy(
                    isWishlisted = !item.isWishlisted
                )
            } else {
                item
            }
        }
    }
}