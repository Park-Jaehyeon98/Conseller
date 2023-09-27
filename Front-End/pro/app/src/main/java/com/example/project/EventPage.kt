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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun EventPage(modifier: Modifier = Modifier, navController: NavHostController) {
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
            item { EventLayout1() }
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
fun EventLayout1() {
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
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {navController.navigate("Inquiry/0")}) {
            Text("신고임시")
        }
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
