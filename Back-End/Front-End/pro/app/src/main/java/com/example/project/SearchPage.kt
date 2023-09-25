package com.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchPage() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar()
        Spacer(modifier = Modifier.height(16.dp))  // 여백 추가
        TrendingSearches()
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        BasicTextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
            },
            modifier = Modifier.fillMaxSize().padding(start = 40.dp)
        )
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            modifier = Modifier.align(Alignment.CenterStart).size(24.dp)
        )
    }
}

@Composable
fun TrendingSearches() {
    val trendingList = listOf("검색어1", "검색어2", "검색어3", "검색어4", "검색어5")

    Text(text = "실시간 인기 검색어", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    Spacer(modifier = Modifier.height(8.dp)) // 여백 추가

    LazyColumn {
        items(trendingList) { item ->
            Text(text = item, modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}



//@Preview
//@Composable
//fun PreviewSearchBar() {
//    SearchPage()
//}
