package com.umc.workbook.week2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavItems = listOf(
        Triple(AppDestination.Home, R.drawable.ic_housesimple, "홈"),
        Triple(AppDestination.Shop, R.drawable.ic_listmagnifyingglass, "구매하기"),
        Triple(AppDestination.Wishlist, R.drawable.ic_heartstraight, "위시리스트"),
        Triple(AppDestination.Cart, R.drawable.ic_bagsimple, "장바구니"),
        Triple(AppDestination.Profile, R.drawable.ic_user, "프로필"),
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { (destination, icon, label) ->
                    NavigationBarItem(
                        selected = currentDestination?.hasRoute(destination::class) == true,
                        onClick = {
                            navController.navigate(destination) {
                                popUpTo(AppDestination.Home) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = label
                            )
                        },
                        label = { Text(label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<AppDestination.Home> { HomeScreen() }
            composable<AppDestination.Shop> { ShopScreen() }
            composable<AppDestination.Wishlist> { WishlistScreen() }
            composable<AppDestination.Cart> {
                CartScreen(
                    onNavigateToShop = {
                        navController.navigate(AppDestination.Shop) {
                            popUpTo(AppDestination.Home) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable<AppDestination.Profile> { ProfileScreen() }
        }
    }
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("홈 화면")
    }
}

@Composable
fun ShopScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("구매하기 화면")
    }
}

@Composable
fun WishlistScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("위시리스트 화면")
    }
}

@Composable
fun CartScreen(onNavigateToShop: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("장바구니 화면")
    }
}

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("프로필 화면")
    }
}
