package com.example.project

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun TopBar(navController: NavHostController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
    Row(
        modifier = Modifier.fillMaxWidth().height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 로고
        Image(
            painter = painterResource(id = R.drawable.logov5),
            contentDescription = null,
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 알림
            Image(
                painter = painterResource(id = R.drawable.alert),
                contentDescription = null,
                modifier = Modifier.clickable { navController.navigate("AlertPage") }
            )

            // 챗봇 or 홈 아이콘
            if (currentDestination == null || currentDestination == "Home") {
                Image(
                    painter = painterResource(id = R.drawable.chatbot),
                    contentDescription = null,
                    modifier = Modifier.clickable { navController.navigate("ChatBotPage") }
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.home), // 홈 아이콘으로 변경
                    contentDescription = null,
                    modifier = Modifier.clickable { navController.navigate("Home") }
                )
            }

            // 마이페이지
            Image(
                painter = painterResource(id = R.drawable.account),
                contentDescription = null,
                modifier = Modifier.clickable { navController.navigate("MyPage") }
            )
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth().height(56.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarButton("전체계좌") {
            navController.navigate("AccountPage")
        }
        BottomBarButton("그린계좌") {
            navController.navigate("GreenPage")
        }
        BottomBarButton("금융상품") {
            navController.navigate("FinancePage")
        }
        BottomBarButton("자산관리") {
            navController.navigate("AssetPage")
        }
        BottomBarButton("혜택") {
            navController.navigate("BenefitPage")
        }
    }
}

@Composable
fun BottomBarButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.widthIn(min = 64.dp, max = 80.dp) // 버튼의 가로 크기
    ) {
        Text(label)
    }
}

@Composable
fun MainContentScrollable(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { ParentLayout1() }
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item { ParentLayout2() }
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item { ParentLayout3() }
        }
    }
}

@Composable
fun ParentLayout1(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxWidth().background(Color.White)) {
        Text("박해종님 안녕하세요", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ParentLayout2(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(300.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text("여기에 내 모든 계좌를 모아서 보여줌")
    }
}

@Composable
fun ParentLayout3(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(300.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text("여기서 내 탄소계좌를 보여줌")
    }
}