package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

val customBackgroundColor = Color(245, 245, 245)

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val sharedPreferencesUtil by lazy { SharedPreferencesUtil(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppNavigation(sharedPreferencesUtil)
        }
    }
}

@Composable
fun AppNavigation(sharedPreferencesUtil: SharedPreferencesUtil) {
    val navController = rememberNavController()

    // 로그인 여부에 따른 시작 화면 설정
    val startDestination = if (sharedPreferencesUtil.isLoggedIn()) "Login" else "TextLoginPage"

    Surface(modifier = Modifier.fillMaxSize(), color = customBackgroundColor) {
        Column {
            val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
            if (currentDestination != "Login" && currentDestination != "SignUp" && currentDestination != "TextLoginPage") {
                TopBar(navController)
            }
            Box(modifier = Modifier.weight(1f)) {
                NavHost(navController, startDestination = startDestination) {
                    composable("Login") { LoginPage(navController) }
                    composable("SignUp") { SignUpPage(navController) }
                    composable("TextLoginPage") { TextLoginPage(navController) }
                    // top bar
                    composable("AlertPage") { AlertPage() }
                    // bottom bar
                    composable("Home") { MainContent(navController = navController) }
                    composable("MyPage") { MyPage() }
                    composable("SearchPage") { SearchPage() }
                    // 경 물 스 이
                    composable("AuctionPage") { AuctionPage(navController) }
                    composable("AuctionDetailPage/{index}") { backStackEntry ->
                        val index = backStackEntry.arguments?.getString("index")
                        AuctiondetailPage(index)
                    }

                    composable("BarterPage") { BarterPage() }

                    composable("StorePage") { StorePage() }

                    composable("EventPage") { EventPage() }

                }
            }
            if (currentDestination != "Login" && currentDestination != "SignUp" && currentDestination != "TextLoginPage") {
                BottomBar(navController)
            }
        }
    }
}
