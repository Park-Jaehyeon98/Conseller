package com.example.project

import GifticonItem
import PaginationControls
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.project.viewmodels.MygifticonViewModel

@Composable
fun BarterCreatePage(navController: NavHostController) {
    val viewModel: MygifticonViewModel = hiltViewModel()
    val gifticonItems by viewModel.gifticonItems.collectAsState()
    val scrollState = rememberScrollState()
    var currentPage by remember { mutableStateOf(1) }
    val itemsPerPage = 10

    var selectedItemIndices by remember { mutableStateOf(listOf<Int>()) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "1. 기프티콘을 선택해주세요.(최대 5개)",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            gifticonItems?.forEach { item ->
                GifticonItem(
                    gifticonData = item,
                    isSelected = selectedItemIndices.contains(item.index),
                    onClick = {
                        // 선택한 항목의 인덱스가 이미 목록에 있는 경우 제거, 그렇지 않으면 추가
                        if (selectedItemIndices.contains(item.index)) {
                            selectedItemIndices = selectedItemIndices - item.index
                        } else {
                            if (selectedItemIndices.size < 5) {
                                selectedItemIndices = selectedItemIndices + item.index
                            }
                        }
                    }
                )
                Divider()
            }

            Spacer(modifier = Modifier.height(16.dp))

            PaginationControls(
                totalItems = gifticonItems?.size ?: 0,
                currentPage = currentPage,
                itemsPerPage = itemsPerPage
            ) { newPage ->
                currentPage = newPage
                viewModel.changePage(newPage)
            }
        }

        // 오른쪽 하단에 위치한 "다음" 버튼
        if (selectedItemIndices.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp)
            ) {
                Button(onClick = {
                    // 선택된 항목들을 다음 페이지로 전달. "/nextPageRoute/1,2,3"과 같이
                    navController.navigate("nextPageRoute/${selectedItemIndices.joinToString(",")}")
                }) {
                    Icon(Icons.Default.ArrowForward, contentDescription = "다음")
                }
            }
        }
    }
}