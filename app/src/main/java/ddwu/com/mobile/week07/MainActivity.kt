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
            composable(BottomNavItem.Purchase.route){PurchaseScreen()}
            composable(BottomNavItem.Wishlist.route){WishlistScreen()}
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