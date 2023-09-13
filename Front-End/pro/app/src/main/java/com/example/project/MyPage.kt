package com.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.project.reuse_component.UserProfile

@Composable
fun MyPage(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column{
            UserProfile(userNickName = "testNickName", userEmail ="testEmail" , userPhoneNumber ="testPhoneNumber" )
        }
    }


}
