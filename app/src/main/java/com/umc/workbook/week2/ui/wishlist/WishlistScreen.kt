package com.umc.workbook.week2.ui.wishlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.umc.workbook.week2.data.ShopProductData

@Composable
fun WishlistScreen(
    viewModel: WishlistViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "위시리스트",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 24.dp, top = 24.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 24.dp,
                end = 24.dp,
                top = 20.dp,
                bottom = 24.dp
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            items(uiState.wishlisted) { product ->
                WishlistProductItem(product = product)
            }
        }
    }
}

@Composable
private fun WishlistProductItem(product: ShopProductData) {
    Column(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = product.name,
            modifier = Modifier.size(184.dp)
        )
        Text(
            text = product.name,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 5.dp)
        )
        Text(
            text = product.price,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}
