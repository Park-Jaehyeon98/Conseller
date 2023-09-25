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
fun EventPage(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { EventLayout2() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { EventLayout3(navController) }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { EventLayout4() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { EventLayout5() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { EventLayout6() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { EventLayout7() }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { EventLayout8() }

        }
    }
}

@Composable
fun EventLayout2() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        Text("Event")
    }
}

@Composable
fun EventLayout3(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        Text("Event")
    }
}

@Composable
fun EventImageButton(imageName: String, onClick: () -> Unit) {
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
fun EventLayout4() {
    Row(
        modifier = Modifier.fillMaxWidth().height(250.dp).padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ){
            Text("Event")
        }
        Spacer(modifier = Modifier.width(20.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ){
            Text("Event")
        }
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
        Text("Event")
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
        Text("Event")
    }
}

@Composable
fun EventLayout7() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        Text("Event")
    }
}

@Composable
fun EventLayout8() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        Text("Event")
    }
}
