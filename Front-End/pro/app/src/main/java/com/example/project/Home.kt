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

@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout2() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout3(navController) }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout4() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout5() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout6() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout7() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeLayout8() }

        }
    }
}

@Composable
fun HomeLayout2() {
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
fun HomeLayout3(navController: NavHostController) {
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
                ImageButton("경매") { navController.navigate("AuctionPage") }
                ImageButton("물물교환") { navController.navigate("BarterPage") }
                ImageButton("스토어") { navController.navigate("StorePage") }
                ImageButton("이벤트") { navController.navigate("EventPage") }
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
            .size(88.dp)
            .clickable(onClick = onClick)
    )
}






@Composable
fun HomeLayout4() {
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
fun HomeLayout5() {
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
fun HomeLayout6() {
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
fun HomeLayout7() {
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
fun HomeLayout8() {
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