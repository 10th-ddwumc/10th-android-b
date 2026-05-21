package ddwu.com.mobile.week07

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.grid.items

@Composable
fun PurchaseScreen(productList: List<ProductData>, onHeartClick: (ProductData) -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("전체", "Tops & T-Shirts", "Shoes")


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        item(span={ GridItemSpan(2) }) {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = {selectedTab= index},
                        text = {Text(text=title)}
                    )
                }
            }
        }
        if (selectedTab == 0) {
            items(items = productList, key = {it.id}) {product ->
                ProductItem(
                    product = product,
                    onHeartClick = { clicked -> onHeartClick(clicked) }
                )
            }
        } else if (selectedTab == 1) {
        item(span = { GridItemSpan(2) }) { PurchaseTopsContent() }
    } else {
        item(span = { GridItemSpan(2) }) { PurchaseShoesContent() }
    }
    }
}

@Composable
fun PurchaseAllContent() {
    Column(modifier = Modifier.fillMaxSize()) {}
}

@Composable
fun PurchaseTopsContent() {
    Column(modifier = Modifier.fillMaxSize()) {}
}

@Composable
fun PurchaseShoesContent() {
    Column(modifier = Modifier.fillMaxSize()) {}
}
