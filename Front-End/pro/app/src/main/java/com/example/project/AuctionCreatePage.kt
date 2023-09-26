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
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.project.viewmodels.MygifticonViewModel

@Composable
fun AuctionCreatePage(navController: NavHostController) {
    val mygifticonViewModel: MygifticonViewModel = hiltViewModel()
    val gifticonItems by mygifticonViewModel.gifticonItems.collectAsState()
    val error by mygifticonViewModel.error.collectAsState()
    val scrollState = rememberScrollState()
    var currentPage by remember { mutableStateOf(1) }
    val itemsPerPage = 10

    var selectedItemIndex by remember { mutableStateOf<Long?>(null) }

    var showSnackbar by remember { mutableStateOf(false) } // 에러처리스낵바
    var snackbarText by remember { mutableStateOf("") }

    LaunchedEffect(error) {
        if (error != null) {
            showSnackbar = true
            snackbarText = error!!
        }
    }
    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            kotlinx.coroutines.delay(5000)
            showSnackbar = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (showSnackbar) {
            Snackbar(
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                Text(text = snackbarText, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "1. 기프티콘을 선택해주세요.",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            gifticonItems?.forEach { item ->
                GifticonItem(
                    gifticonData = item,
                    isSelected = item.gifticonIdx == selectedItemIndex,
                    onClick = {
                        selectedItemIndex = item.gifticonIdx
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
                mygifticonViewModel.changePage(newPage)
            }
        }

        // 오른쪽 하단에 위치한 "다음" 버튼
        if (selectedItemIndex != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp)
            ) {
                Button(onClick = {
                    selectedItemIndex?.let {
                        navController.navigate("AuctionCreateDetailPage/$it")
                    }
                }) {
                    Icon(Icons.Default.ArrowForward, contentDescription = "다음")
                }
            }
        }
    }
}