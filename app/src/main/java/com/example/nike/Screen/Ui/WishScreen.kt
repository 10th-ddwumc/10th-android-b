package com.example.nike.Screen.Ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nike.Model.BuyViewModel
import com.example.nike.Screen.Ui.Item.ItemData

@Composable
fun WishScreen(
    buyViewModel: BuyViewModel = viewModel(),
    onProductClick: (ItemData) -> Unit = {}
) {

    // 하트 눌린 상품만 추출
    val wishList = buyViewModel.itemList.filter {
        it.isWishlisted
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {

        Text(
            text = "위시리스트",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 비어있을 때
        if (wishList.isEmpty()) {

            Text("찜한 상품이 없습니다.")

        } else {

            // 위시 상품 출력
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),

                verticalArrangement = Arrangement.spacedBy(16.dp),

                horizontalArrangement = Arrangement.spacedBy(16.dp),

                contentPadding = PaddingValues(4.dp),

                modifier = Modifier.fillMaxSize()
            ) {

                items(
                    items = wishList,
                    key = { it.id }
                ) { product ->

                    BuyProductItem(
                        product = product,

                        onProductClick = {
                            onProductClick(product)
                        },

                        // 위시 화면에서도 하트 해제 가능
                        onHeartClick = {
                            buyViewModel.toggleWish(product.id)
                        }
                    )
                }
            }
        }
    }
}