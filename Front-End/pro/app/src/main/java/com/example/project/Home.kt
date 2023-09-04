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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
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
        BottomBarButton("메인") {
            navController.navigate("Home")
        }
        BottomBarButton("검색") {
            navController.navigate("SearchPage")
        }
        BottomBarButton("내정보") {
            navController.navigate("MyPage")
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
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { ParentLayout1() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { ParentLayout2() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { ParentLayout3() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { ParentLayout4() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { ParentLayout5() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { ParentLayout6() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { ParentLayout7() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { ParentLayout8() }

        }
    }
}

@Composable
fun ParentLayout1() {
    Box(
        modifier = Modifier.fillMaxWidth().background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("박해종님 안녕하세요", fontSize = 2f * 10.sp)
    }
}

@Composable
fun ParentLayout2() {
    Box(
        modifier = Modifier.fillMaxWidth().height(150.dp).background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("Hot")
    }
}

@Composable
fun ParentLayout3() {
    Box(
        modifier = Modifier.fillMaxWidth().height(150.dp).background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("경 물 스 이")
    }
}

@Composable
fun ParentLayout4() {
    Row(
        modifier = Modifier.fillMaxWidth().height(250.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 입찰
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight().background(Color.White, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("입찰")
                Spacer(modifier = Modifier.height(20.dp))
                Text("내용") //여기에 기프티콘xxx 현재 내가 최고입찰가인지? height 조절해서
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {}) {
                    Text("더보기")
                }
            }
        }

        Spacer(modifier = Modifier.width(20.dp))

        // 판매
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight().background(Color.White, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("판매")
                Spacer(modifier = Modifier.height(20.dp))
                Text("내용") //여기에 기프티콘xxx 현재 최고 입찰가표시  height 조절해서
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {}) {
                    Text("더보기")
                }
            }
        }
    }
}


@Composable
fun ParentLayout5() {
    Box(
        modifier = Modifier.fillMaxWidth().height(150.dp).background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("여기부터 알고리즘 인기")
    }
}

@Composable
fun ParentLayout6() {
    Box(
        modifier = Modifier.fillMaxWidth().height(150.dp).background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("알고리즘 인기 2")
    }
}

@Composable
fun ParentLayout7() {
    Box(
        modifier = Modifier.fillMaxWidth().height(150.dp).background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("알고리즘 인기 3")
    }
}

@Composable
fun ParentLayout8() {
    Box(
        modifier = Modifier.fillMaxWidth().height(100.dp).background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("약관 등등")
    }
}