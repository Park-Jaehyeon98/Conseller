package com.example.project

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun WaitingPage(navController: NavHostController) {
    Text(text = "입금완료페이지.")
    Text(text = "판매자가 입금을 확인하면 기프티콘이 구매완료됩니다. 알람줍니다. 최대 xx분 걸립니다.")
}
