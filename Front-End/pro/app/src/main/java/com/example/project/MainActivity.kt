package com.example.project

import InquiryPage
import MypageAuction
import MypageStore
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.project.sharedpreferences.SharedPreferencesUtil
import com.example.project.viewmodels.AuctionViewModel
import com.example.project.viewmodels.MygifticonViewModel
import com.example.project.viewmodels.StoreViewModel
import dagger.hilt.android.AndroidEntryPoint

val customBackgroundColor = Color(245, 245, 245)

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val sharedPreferencesUtil by lazy { SharedPreferencesUtil(this) }

    private val auctionViewModel: AuctionViewModel by viewModels()
    private val storeViewModel: StoreViewModel by viewModels()
    private val barterViewModel: StoreViewModel by viewModels()
    private val myGifticonViewModel: MygifticonViewModel by viewModels()

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
                    composable("FindIdPage"){ FindIdPage(navController)}
                    composable("FindPwPage"){ FindPwPage(navController)}
                    composable("MakePatternPage"){ MakePatternPage(navController)}
                    // top bar
                    composable("AlertPage") { AlertPage() }
                    // bottom bar
                    composable("Home") { HomePage(navController = navController) }
                    composable("MyPage") { MyPage(navController = navController) }
                    composable("MyPageModify") { MyPageModifyPage(navController = navController) }
                    composable("MyModifyPageValidPage") { MyModifyPageValidPage(navController = navController) }
                    composable("MyDeletePageValidPage") { MyDeletePageValidPage(navController = navController) }

                    composable("MypageCoupon") { MypageCoupon(navController = navController) }
                    composable("MyPageCouponDetail/{index}") { backStackEntry ->
                        val gifticonIdx = backStackEntry.arguments?.getString("index")
                        MyPageCouponDetail(navController, gifticonIdx)
                    }
                    composable("MypageBarter") { MypageBarter(navController = navController) }
                    composable("MypageStore") { MypageStore(navController = navController) }
                    composable("MypageAuction") { MypageAuction(navController = navController) }
                    composable("MyGifticonAdd") { GifticonAddPage(navController = navController) }
                    composable("MypageDelete") { MypageDelete(navController = navController) }
                    composable("MyGifticonAddDetail") { GifticonAddDetailPage(navController = navController) }
                    composable("SearchPage") { SearchPage() }
                    // 신고하기
                    composable("Inquiry/{opponentIdx}") { backStackEntry ->
                        val opponentIdx = backStackEntry.arguments?.getString("opponentIdx")
                        InquiryPage(navController,opponentIdx)
                    }

                    // 스토어
                    composable("StorePage") { StorePage(navController = navController) }
                    composable("StoreDetailPage/{storeIdx}") { backStackEntry ->
                        val storeIdx = backStackEntry.arguments?.getString("storeIdx")
                        StoreDetailPage(navController, storeIdx)
                    }
                    composable("StoreCreatePage") { StoreCreatePage(navController) }
                    composable("StoreCreateDetailPage/{selectedItemIndex}") { backStackEntry ->
                        val selectedItemIndex = backStackEntry.arguments?.getString("selectedItemIndex")
                        StoreCreateDetailPage(navController, selectedItemIndex)
                    }
                    composable("storeUpdate/{storeIdx}") { backStackEntry ->
                        val storeIdx = backStackEntry.arguments?.getString("storeIdx")
                        StoreUpdatePage(navController, storeIdx)
                    }
                    composable("StoreTradePage/{storeIdx}") { backStackEntry ->
                        val storeIdx = backStackEntry.arguments?.getString("storeIdx")
                        StoreTradePage(storeIdx, navController)
                    }
                    composable("storeConfirmPage/{storeIdx}") { backStackEntry ->
                        val storeIdx = backStackEntry.arguments?.getString("storeIdx")
                        StoreConfirmPage(navController, storeIdx)
                    }


                    // 경매
                    composable("AuctionPage") { AuctionPage(navController) }
                    composable("AuctionDetailPage/{index}") { backStackEntry ->
                        val auctionIdx = backStackEntry.arguments?.getString("index")
                        AuctionDetailPage(navController, auctionIdx)
                    }
                    composable("AuctionCreatePage") { AuctionCreatePage(navController) }
                    composable("AuctionCreateDetailPage/{selectedItemIndex}") { backStackEntry ->
                        val selectedItemIndex = backStackEntry.arguments?.getString("selectedItemIndex")
                        AuctionCreateDetailPage(navController, selectedItemIndex)
                    }
                    composable("auctionUpdate/{auctionIdx}") { backStackEntry ->
                        val auctionIdx = backStackEntry.arguments?.getString("auctionIdx")
                        AuctionUpdatePage(navController, auctionIdx)
                    }
                    composable("AuctionTradePage/{auctionIdx}") { backStackEntry ->
                        val auctionIdx = backStackEntry.arguments?.getString("auctionIdx")
                        AuctionTradePage(auctionIdx, navController)
                    }
                    composable("AuctionConfirmPage/{auctionIdx}") { backStackEntry ->
                        val auctionIdx = backStackEntry.arguments?.getString("auctionIdx")
                        AuctionConfirmPage(navController, auctionIdx)
                    }
                    composable("auctionConfirmBuyPage/{auctionIdx}") { backStackEntry ->
                        val auctionIdx = backStackEntry.arguments?.getString("auctionIdx")
                        auctionConfirmBuyPage(navController, auctionIdx)
                    }


                    // 물물
                    composable("BarterPage") { BarterPage(navController) }
                    composable("BarterDetailPage/{barterIdx}") { backStackEntry ->
                        val barterIdx = backStackEntry.arguments?.getString("barterIdx")
                        BarterDetailPage(barterIdx, navController)
                    }
                    composable("BarterCreatePage") { BarterCreatePage(navController) }
                    composable("BarterCreateDetailPage/{selectedItemIndices}") { backStackEntry ->
                        val selectedItemIndicesString = backStackEntry.arguments?.getString("selectedItemIndices") ?: ""
                        val selectedItemIndicesList = selectedItemIndicesString.split(",").map { it.toLongOrNull() }.filterNotNull()
                        BarterCreateDetailPage(navController, selectedItemIndicesList)
                    }
                    composable("barterUpdate/{barterIdx}") { backStackEntry ->
                        val barterIdx = backStackEntry.arguments?.getString("barterIdx")
                        BarterUpdatePage(barterIdx, navController)
                    }
                    composable("BarterTradeSelectPage/{barterIdx}") { backStackEntry ->
                        val barterIdx = backStackEntry.arguments?.getString("barterIdx")
                        BarterTradeSelectPage(barterIdx, navController)
                    }
                    composable("BarterTradePage/{selectedItemIndices}/{barterIdx}") { backStackEntry ->
                        val selectedItemIndicesString = backStackEntry.arguments?.getString("selectedItemIndices") ?: ""
                        val selectedItemIndicesList = selectedItemIndicesString.split(",").map { it.toLongOrNull() }.filterNotNull()
                        val barterIdx = backStackEntry.arguments?.getString("barterIdx")
                        BarterTradePage(navController, selectedItemIndicesList, barterIdx)
                    }
                    composable("barterConfirmPage/{barterIdx}") { backStackEntry ->
                        val barterIdx = backStackEntry.arguments?.getString("barterIdx")
                        BarterConfirmPage(navController, barterIdx)
                    }

                    // 거래 완료
                    composable("WaitingPage") { WaitingPage(navController) }
                    // 이벤트
                    composable("EventPage") { EventPage(navController = navController) }

                }
            }
            if (currentDestination != "Login" && currentDestination != "SignUp" && currentDestination != "TextLoginPage") {
                BottomBar(navController)
            }
        }
    }
}
