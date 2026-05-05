package com.umc.workbook.week2.ui.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.umc.workbook.week2.R
import com.umc.workbook.week2.data.ShopProductData

@Composable
fun ShopScreen(
    viewModel: ShopViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("전체", "Tops & T-Shirts", "sale")

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTab == index
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .clickable { selectedTab = index },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        color = if (isSelected) Color.Black else Color(0xFF9E9E9E),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(if (isSelected) Color.Black else Color.Transparent)
                    )
                }
            }
        }

        when (selectedTab) {
            0 -> ShopAllTab(
                products = uiState.products,
                onToggleWish = { viewModel.toggleWish(it) }
            )
            else -> Box(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun ShopAllTab(
    products: List<ShopProductData>,
    onToggleWish: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.padding(top = 32.dp)
    ) {
        itemsIndexed(products) { index, product ->
            ShopProductItem(
                product = product,
                onToggleWish = { onToggleWish(index) }
            )
        }
    }
}

@Composable
private fun ShopProductItem(
    product: ShopProductData,
    onToggleWish: () -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Box {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = product.name,
                modifier = Modifier.size(184.dp)
            )
            IconButton(
                onClick = onToggleWish,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (product.isWishlisted) R.drawable.ic_heart_filled
                        else R.drawable.ic_heart_blank
                    ),
                    contentDescription = "위시리스트 아이콘",
                    tint = Color.Unspecified
                )
            }
        }

        if (product.isBestSeller) {
            Text(
                text = "BestSeller",
                color = Color(0xFFFC5100),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 14.dp, start = 14.dp)
            )
        }
        Text(
            text = product.name,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 5.dp, start = 14.dp, end = 14.dp)
        )
        Text(
            text = product.subtitle,
            color = Color(0xFF767676),
            modifier = Modifier.padding(top = 5.dp, start = 14.dp, end = 14.dp)
        )
        Text(
            text = product.colors,
            color = Color(0xFF767676),
            modifier = Modifier.padding(top = 5.dp, start = 14.dp, end = 14.dp)
        )
        Text(
            text = product.price,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 5.dp, start = 14.dp, end = 14.dp)
        )
    }
}
