package ddwu.com.mobile.week07

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ddwu.com.mobile.week07.ui.component.BottomNavItem
import ddwu.com.mobile.week07.ui.component.BottomNavigationBar
import ddwu.com.mobile.week07.ui.theme.Week07Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week07Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    var productList by rememberSaveable {
        mutableStateOf(
            listOf(
                ProductData(1,"Nike Everyday Plus Cushioned", "US\$10", R.drawable.training_socks, "Training Ankle Socks\n5 Colours"),
                ProductData(2, "Nike Elite Crew", "US\$16", R.drawable.basketball_soccks, "Basketball Socks\n7 Colours"),
                ProductData(3, "Nike Air Force 1 '07", "US\$115", R.drawable.air_force, "Women's Shoes\n5 Colours"),
                ProductData(4, "Jordan ENike Air Force", "US\$115", R.drawable.jordan_airforce, "Men's Shoes\n2 Colours")

            )
        )
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ){innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route){HomeScreen()}
            composable(BottomNavItem.Purchase.route){
                PurchaseScreen(
                    productList = productList,
                    onHeartClick = {clicked ->
                        productList = productList.map {
                            if (it.id == clicked.id) it.copy(isLiked = !it.isLiked)
                            else it
                        }
                    }
                )
            }
            composable(BottomNavItem.Wishlist.route){
                WishlistScreen(
                    wishlist = productList.filter{it.isLiked}
                )
            }
            composable(BottomNavItem.Cart.route){
                CartScreen(onOrderClick = {
                    navController.navigate(BottomNavItem.Purchase.route){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop=true
                    }
                })}
            composable(BottomNavItem.Profile.route){ProfileScreen()}
        }

    }
}