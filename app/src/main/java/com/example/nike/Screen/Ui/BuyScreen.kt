package com.example.nike.Screen.Ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nike.Screen.Ui.Item.ItemData
import com.example.nike.Screen.Ui.Item.ProductData
import com.example.nike.Model.BuyViewModel

@Composable
fun BuyScreen(
    buyViewModel: BuyViewModel = viewModel()
) {

    val selectedTabIndex = buyViewModel.selectedTabIndex
    val itemList = buyViewModel.itemList

//    var selectedTabIndex by remember {
//        mutableIntStateOf(0)
//    }

    val tabList = listOf(
        "전체",
        "Tops&T-shirts",
        "sale"
    )
//
//    val itemList = when (selectedTabIndex) {
//
//        0 -> ProductData.buyProducts
//
//        1 -> emptyList<ItemData>()
//
//        2 -> emptyList<ItemData>()
//
//        else -> ProductData.buyProducts
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {

        Text(
            text = "구매하기",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        BuyTabRow(
            tabList = tabList,
            selectedTabIndex = selectedTabIndex,
            onTabSelected = {
//                selectedTabIndex = it
                buyViewModel.selectTab(it)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        BuyProductGrid(
            itemList = itemList
        )
    }
}

@Composable
fun BuyTabRow(
    tabList: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex
    ) {

        tabList.forEachIndexed { index, title ->

            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    onTabSelected(index)
                },
                text = {
                    Text(text = title)
                }
            )
        }
    }
}

@Composable
fun BuyProductGrid(
    itemList: List<ItemData>
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),

        verticalArrangement = Arrangement.spacedBy(16.dp),

        horizontalArrangement = Arrangement.spacedBy(16.dp),

        contentPadding = PaddingValues(4.dp),

        modifier = Modifier.fillMaxSize()
    ) {

        items(
            //id 및 key 배정
            items = itemList,
            key = { it.id }
        ) { product ->

            BuyProductItem(product)
        }
    }
}

@Composable
fun BuyProductItem(
    product: ItemData
) {

    Card(
        //배경 흰색
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        modifier = Modifier
            .width(180.dp)
            .clickable {

            }
    ) {

        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Image(
                painter = painterResource(id = product.imageRes),

                contentDescription = product.name,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp)),

                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = product.bestSell
            )

            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = product.explan
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = product.price,
                fontSize = 14.sp
            )
        }
    }
}