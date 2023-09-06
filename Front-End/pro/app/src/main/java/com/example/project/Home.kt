package com.example.project

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun TopBar(navController: NavHostController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    Box(modifier = Modifier.fillMaxWidth().background(Color(201f/255f, 235f/255f, 243f/255f))) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().height(48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 로고
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 알림
                    Image(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        modifier = Modifier.clickable { navController.navigate("AlertPage") }
                            .size(40.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("박해종님 안녕하세요", fontSize = 2f * 10.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
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
                navController.navigate("SearchPage")
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



@Composable
fun MainContent(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { ParentLayout2() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { ParentLayout3(navController) }
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
fun ParentLayout2() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text("Hot")
    }
}

@Composable
fun ParentLayout3(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))  // 상단에 공간을 주기 위해 weight를 사용
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                ImageButton("경매", { navController.navigate("AuctionPage") })
                ImageButton("물물교환", { navController.navigate("BarterPage") })
                ImageButton("스토어", { navController.navigate("StorePage") })
                ImageButton("이벤트", { navController.navigate("EventPage") })
                Spacer(modifier = Modifier.width(8.dp))
            }
            Spacer(modifier = Modifier.weight(1f))  // 하단에 공간을 주기 위해 weight를 사용
        }
    }
}

@Composable
fun ImageButton(imageName: String, onClick: () -> Unit) {
    val resourceId = when (imageName) {
        "경매" -> R.drawable.auction
        "물물교환" -> R.drawable.barter
        "스토어" -> R.drawable.store
        "이벤트" -> R.drawable.event
        else -> R.drawable.chatbot
    }

    Image(
        painter = painterResource(id = resourceId),
        contentDescription = null,
        modifier = Modifier
            .size(92.dp)
            .clickable(onClick = onClick)
    )
}






@Composable
fun ParentLayout4() {
    Row(
        modifier = Modifier.fillMaxWidth().height(250.dp).padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 입찰
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
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
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
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
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text("여기부터 알고리즘 인기")
    }
}

@Composable
fun ParentLayout6() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text("알고리즘 인기 2")
    }
}

@Composable
fun ParentLayout7() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text("알고리즘 인기 3")
    }
}

@Composable
fun ParentLayout8() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text("약관 등등")
    }
}