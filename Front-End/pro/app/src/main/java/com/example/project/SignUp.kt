package com.example.project

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun SignUpPage(navController: NavHostController) {
    Button(onClick = {
        // 클릭 시 Home으로 이동
        navController.navigate("Login")
    }) {
        Text("회원가입이후")
    }
}
