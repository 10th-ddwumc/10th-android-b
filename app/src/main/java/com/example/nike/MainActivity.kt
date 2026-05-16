package com.example.nike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.nike.Screen.Ui.BuyScreen
import com.example.nike.Screen.Ui.HomeScreen
import com.example.nike.Screen.Ui.Item.ItemData
import com.example.nike.Screen.Ui.PocketScreen
import com.example.nike.Screen.Ui.ProductDetailScreen
import com.example.nike.Screen.Ui.ProfitScreen
import com.example.nike.Screen.Ui.ReProfitScreen
import com.example.nike.Screen.Ui.WishScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}


@Composable
fun MainScreen() {

    //상세페이지 이동
    var selectedProduct by remember {
        mutableStateOf<ItemData?>(null)
    }

    if (selectedProduct != null) {
        ProductDetailScreen(
            product = selectedProduct!!,
            onBackClick = {
                selectedProduct = null
            }
        )
    } else {
        BuyScreen(
            onProductClick = { product ->
                selectedProduct = product
            }
        )
    }

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(
                onHomeClick = { navController.navigate(AppDestination.Home) },
                onBuyClick = { navController.navigate(AppDestination.Buy) },
                onPocketClick = { navController.navigate(AppDestination.Pocket) },
                onWishClick = { navController.navigate(AppDestination.Wish) },
                onProfitClick = { navController.navigate(AppDestination.Profit) }
            )
        }

    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.Home,
            modifier = Modifier.padding(paddingValues)
        )
        {
            // 홈
            composable<AppDestination.Home> {
                HomeScreen(
                    onProductClick = { product ->

                    }
                )
            }

            // 구매하기
            composable<AppDestination.Buy> {
                BuyScreen(
                    onProductClick = { product ->

                    }
                )
            }
            // 장바구니
            composable<AppDestination.Pocket> {
                PocketScreen(
                    onOrderClick = {
                        navController.navigate(AppDestination.Buy) {
                            popUpTo(AppDestination.Home)
                            launchSingleTop = true
                        }
                    }
                )
            }
            // 위시리스트
            composable<AppDestination.Wish> { WishScreen() }
            // 프로필
            composable<AppDestination.Profit> {
                ProfitScreen(
                    onNavigateReProfit = { navController.navigate(AppDestination.ReProfit) }
                )
            }
            // 프로필 수정
            composable<AppDestination.ReProfit> { ReProfitScreen() }
        }
    }
}

@Composable
fun BottomBar(
    onHomeClick: () -> Unit,
    onBuyClick: () -> Unit,
    onPocketClick: () -> Unit,
    onWishClick: () -> Unit,
    onProfitClick: () -> Unit
) {

    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = onHomeClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "홈"
                )
            },
            label = {
                Text("홈")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = onBuyClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "구매"
                )
            },
            label = {
                Text("구매")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = onPocketClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "장바구니"
                )
            },
            label = {
                Text("장바구니")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = onWishClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "위시"
                )
            },
            label = {
                Text("위시")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = onProfitClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "프로필"
                )
            },
            label = {
                Text("프로필")
            }
        )
    }
}