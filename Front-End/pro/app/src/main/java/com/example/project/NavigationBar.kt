package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.project.ui.theme.logocolor

@Composable
fun TopBar(navController: NavHostController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(modifier = Modifier.padding(top = 24.dp, start = 12.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        Modifier.padding(start = 120.dp).scale(1.5f)
                    )
                }

                if (currentDestination != "AlertPage") {
                    Image(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { navController.navigate("AlertPage") }
                            .size(70.dp)
                            .scale(1.4f)
                            .padding(top = 16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(logocolor)
            )
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("알림") },
            text = { Text("개발중인 기능입니다") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("확인")
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .height(72.dp)
    ) {
        // 그림자 추가를 위한 Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.2.dp)
                .shadow(elevation = 0.2.dp, shape = RectangleShape)
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarButton("메인") {
                navController.navigate("Home")
            }
            BottomBarButton("검색") {
                showDialog = true
            }
            BottomBarButton("내정보") {
                navController.navigate("MyPage")
            }
        }
    }
}


@Composable
fun BottomBarButton(label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(onClick = onClick)
            .widthIn(min = 64.dp, max = 80.dp)
    ) {
        val icon = when(label) {
            "메인" -> Icons.Default.Home
            "검색" -> Icons.Default.Search
            "내정보" -> Icons.Default.AccountCircle
            else -> Icons.Default.Home
        }

        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp)) // 아이콘과 텍스트 사이 간격
        Text(label)
    }
}
