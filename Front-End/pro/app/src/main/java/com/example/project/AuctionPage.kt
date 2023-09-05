package com.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuctionPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 검색창
        OutlinedTextField(
            value = remember { mutableStateOf("") }.value,
            onValueChange = {},
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            placeholder = { Text("검색") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 필터 버튼
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            FilterButton("필터1")
            FilterButton("필터2")
            FilterButton("필터3")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 게시글 리스트
        LazyColumn {
            // 임시로 10개
            items(10) { index ->
                AuctionItem(
                    image = "사진${index}",
                    name = "이름${index}",
                    gifticonTime = "유효기간${index}",
                    auctionTime = "경매기간${index}",
                    popular = "인자기${index}",
                    upperprice = "즉시구매가${index}",
                    nowprice = "현재입찰가${index}"
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun FilterButton(text: String) {
    Button(onClick = {}) {
        Text(text)
    }
}

@Composable
fun AuctionItem(
    image: String,
    name: String,
    gifticonTime: String,
    auctionTime: String,
    popular: String,
    upperprice: String,
    nowprice: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color.White)
            .padding(8.dp)
    ) {
        // 65% 이미지
        Box(
            modifier = Modifier
                .weight(0.65f)
                .fillMaxWidth()
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            // Home 아이콘 임시
            Icon(Icons.Default.Home, contentDescription = "Image")
        }

        // 10% 이름 및 유효기간
        Row(
            modifier = Modifier.weight(0.1f).fillMaxWidth().padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(name, fontSize = 20.sp)
            Text("유효기간: $gifticonTime")
        }

        // 구분 줄
        Divider(color = Color.Gray, modifier = Modifier.padding(horizontal = 12.dp))

        // 5% 경매기간
        Text("경매기간: $auctionTime", modifier = Modifier
            .weight(0.1f)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
        )

        // 15% 박스1
        Box(
            modifier = Modifier.weight(0.15f).padding(horizontal = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 40% 인기
                Text("인기: $popular", modifier = Modifier.weight(0.4f))

                // 60% 박스2
                Column(
                    modifier = Modifier.weight(0.6f),
                    horizontalAlignment = Alignment.End
                ) {
                    // 30% 즉시구매가
                    Text("즉시구매가: $upperprice", modifier = Modifier.weight(0.4f))

                    // 70% 현재입찰가
                    Text("현재입찰가: $nowprice", modifier = Modifier.weight(0.6f))
                }
            }
        }
    }
}



