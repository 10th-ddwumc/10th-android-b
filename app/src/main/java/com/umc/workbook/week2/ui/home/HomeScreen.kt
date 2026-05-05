package com.umc.workbook.week2.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.umc.workbook.week2.R
import com.umc.workbook.week2.data.NewProductData

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Discover",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 41.dp, top = 44.dp)
        )
        Text(
            text = "9월 4일 목요일",
            fontSize = 16.sp,
            color = Color(0xFF767676),
            modifier = Modifier.padding(start = 41.dp, top = 10.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.home_logo),
            contentDescription = "홈 메인 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 50.dp, end = 24.dp)
        )

        Text(
            text = "What's new",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 42.dp, top = 40.dp)
        )
        Text(
            text = "나이키 최신 상품",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF767676),
            modifier = Modifier.padding(start = 42.dp, top = 12.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(start = 42.dp, top = 22.dp, end = 24.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(uiState.products) { product ->
                NewProductItem(product = product)
            }
        }
    }
}

@Composable
private fun NewProductItem(product: NewProductData) {
    Column(modifier = Modifier.width(320.dp)) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = product.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        )
        Text(
            text = product.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 14.dp)
        )
        Text(
            text = product.price,
            fontSize = 16.sp,
            color = Color(0xFF767676),
            modifier = Modifier.padding(top = 8.dp, bottom = 6.dp)
        )
    }
}
