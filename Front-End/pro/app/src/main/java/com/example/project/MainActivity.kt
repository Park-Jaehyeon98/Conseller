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

val customBackgroundColor = Color(240, 245, 250)

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
    val startDestination = if (sharedPreferencesUtil.isLoggedIn()) "Login" else "SignUp"

    Surface(modifier = Modifier.fillMaxSize(), color = customBackgroundColor) {
        Column {
            val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
            if (currentDestination != "Login" && currentDestination != "SignUp") {
                TopBar(navController)
            }
            Box(modifier = Modifier.weight(1f)) {
                NavHost(navController, startDestination = startDestination) {
                    composable("Login") { LoginPage(navController) }
                    composable("SignUp") { SignUpPage(navController) }
                    composable("Home") { MainContentScrollable() }
                    composable("AccountPage") { AccountPage() }
                    composable("GreenPage") { GreenPage() }
                    composable("FinancePage") { FinancePage() }
                    composable("AssetPage") { AssetPage() }
                    composable("BenefitPage") { BenefitPage() }
                    composable("AlertPage") { AlertPage() }
                    composable("ChatBotPage") { ChatBotPage() }
                    composable("MyPage") { MyPage() }
                }
            }
            if (currentDestination != "Login" && currentDestination != "SignUp") {
                BottomBar(navController)
            }
        }
    }
}
