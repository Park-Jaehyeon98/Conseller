package com.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
                AuctionItem("사진${index}",
                    "이름${index}",
                    "유효기간${index}",
                    "경매기간${index}",
                    "인자기${index}",
                    "즉시구매가${index}",
                    "현재입찰가${index}",
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
    image: String, name: String, gifticonTime: String,
    auctionTime: String, popular: String,
    upperprice: String, nowprice: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        // 60% 이미지
        Box(
            modifier = Modifier
                .weight(6f)
                .fillMaxWidth()
                .background(Color.Gray)
        ) {
            // 여기에 이미지 로드하는 로직이 들어가면 됩니다.
        }

        // 15% 이름과 유효기간
        Column(
            modifier = Modifier.weight(1.5f)
        ) {
            Text(name, fontSize = 18.sp)
            Text("유효기간: ...")
        }

        // 10% 경매기간
        Text("시간: $auctionTime", modifier = Modifier.weight(1f))

        // 15% 박스1
        Box(
            modifier = Modifier.weight(1.5f)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 40% 입찰기
                Text("입찰기: ...", modifier = Modifier.weight(4f))

                // 60% 박스2
                Column(
                    modifier = Modifier.weight(6f)
                ) {
                    // 30% 즉시구매가
                    Text("즉시구매가: $highestPrice", modifier = Modifier.weight(3f))

                    // 70% 현재입찰가
                    Text("현재입찰가: $currentBid", modifier = Modifier.weight(7f))
                }
            }
        }
    }
}

