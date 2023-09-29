package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.project.viewmodels.MyAuctionViewModel

@Composable
fun EventPage(modifier: Modifier = Modifier, navController: NavHostController) {
    val myAuctionViewModel: MyAuctionViewModel = hiltViewModel()
    val event by myAuctionViewModel.event.collectAsState()

    var showAlert by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("") }

    LaunchedEffect(event) {
        if (event == "성공") {
            alertMessage = "축하드립니다! 가까운시일내로 가입한 휴대폰번호로 기프티콘이 발송됩니다!"
            showAlert = true
        } else if(event != null){
            alertMessage = "연결 실패이거나 이벤트가 이미 끝났습니다"
            showAlert = true
        }
    }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = {
                showAlert = false
            },
            title = {
                Text(text = "알림")
            },
            text = {
                Text(text = alertMessage)
            },
            confirmButton = {
                Button(onClick = {
                    showAlert = false
                }) {
                    Text("확인")
                }
            }
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item {
                EventLayout1(onImageClick = {
                    myAuctionViewModel.resistEvent()
                })
            }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { EventLayout2(navController) }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { EventLayout3() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { EventLayout4() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { EventLayout5() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { EventLayout6() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { EventLayout7() }
        }
    }
}

@Composable
fun EventLayout1(onImageClick: () -> Unit) {
    val imageUrl = "https://b207-conseller.s3.ap-northeast-2.amazonaws.com/%EC%9D%B4%EB%B2%A4%ED%8A%B8%EC%95%88+3.png"
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = imageUrl).apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)
                }).build()
            ),
            contentDescription = "이벤트 이미지",
            modifier = Modifier.fillMaxSize()
                .clickable(onClick = onImageClick),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun EventLayout2(navController: NavHostController) {
    val firebaseService = MyFirebaseMessagingService()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        Text("준비중입니다", fontSize = 20.sp)
    }
}

@Composable
fun EventLayout3() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ){
            Text("준비중입니다", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.width(20.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ){
            Text("준비중입니다", fontSize = 20.sp)
        }
    }
}

@Composable
fun EventLayout4() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        Text("준비중입니다", fontSize = 20.sp)
    }
}

@Composable
fun EventLayout5() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        Text("준비중입니다", fontSize = 20.sp)
    }
}

@Composable
fun EventLayout6() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        Text("준비중입니다", fontSize = 20.sp)
    }
}

@Composable
fun EventLayout7() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        Text("준비중입니다", fontSize = 20.sp)
    }
}
