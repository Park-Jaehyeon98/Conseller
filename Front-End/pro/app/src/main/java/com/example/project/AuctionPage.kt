package com.example.project

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
        var searchText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            placeholder = { Text("검색") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        var filter1Selected by remember { mutableStateOf("대분류") } // 필터1의 초기값
        var filter2Selected by remember { mutableStateOf("소분류") } // 필터2의 초기값
        var filter3Selected by remember { mutableStateOf("상태선택") } // 필터3의 초기값

        // 필터 버튼
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            FilterButton(
                selectedOption = filter1Selected,
                options = listOf("대분류","a", "b", "c")
            ) { filter1Selected = it; filter2Selected = "소분류" } // 선택 시 filter2 초기화

            FilterButton(
                selectedOption = filter2Selected,
                options = when (filter1Selected) {
                    "a" -> listOf("a-1", "a-2", "a-3")
                    "b" -> listOf("b-1", "b-2", "b-3")
                    "c" -> listOf("c-1", "c-2", "c-3")
                    else -> listOf("소분류")
                }
            ) { filter2Selected = it }

            FilterButton(
                selectedOption = filter3Selected,
                options = listOf("상태선택", "낮은가격순", "낮은입찰순", "미입찰")
            ) { filter3Selected = it }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // AuctionItem 추가
        AuctionItem(
            image = "ImageLink",
            name = "ItemName",
            gifticonTime = "GifticonTime",
            auctionTime = "AuctionTime",
            popular = "Popular",
            upperprice = "UpperPrice",
            nowprice = "NowPrice"
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterButton(
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth(0.3f)
            .wrapContentSize(Alignment.TopEnd)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(5.dp))
            .clickable { expanded = true }
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = selectedOption)
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Arrow Drop Down")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest  = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
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
            // Home 아이콘 임시 image가 들어갈자리
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



