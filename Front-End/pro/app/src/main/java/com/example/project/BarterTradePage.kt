package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.BarterViewModel

@Composable
fun BarterTradePage(navController: NavHostController, selectedItemIndices: List<Long>, index: String?) {
    val barterviewModel: BarterViewModel = hiltViewModel()
    val selectedItems = barterviewModel.getSelectedItems(selectedItemIndices) // 내가 고른사진
    val barterItems by barterviewModel.barterItems.collectAsState()

    val currentItem = barterItems.find { it.barterIdx.toString() == index } // 게시글 사진

    var showCancelDialog by remember { mutableStateOf(false) } // 취소 대화상자 표시 상태
    var showTradeProposalDialog by remember { mutableStateOf(false) } //거래신청 대화상자

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 게시글의 이미지들을 보여주는 LazyRow
        LazyRow(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listOf(currentItem?.gifticonDataImageName).filterNotNull()) { imageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(180.dp)
                        .clip(shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                        .background(Color.Gray)
                )
            }
        }

        // 선택한 내 이미지들을 보여주는 LazyRow
        LazyRow(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(selectedItems) { item ->
                Image(
                    painter = rememberAsyncImagePainter(model = item.gifticonDataImageName),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(180.dp)
                        .clip(shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                        .background(Color.Gray)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {
                showTradeProposalDialog = true
            }, modifier = Modifier.weight(1f)) {
                Text("거래신청")
            }

            Button(onClick = {
                showCancelDialog = true
            }, modifier = Modifier.weight(1f)) {
                Text("취소하기")
            }
        }
    }

    if (showCancelDialog) {
        AlertDialog(
            onDismissRequest = {
                showCancelDialog = false
            },
            title = {
                Text(text = "거래 취소")
            },
            text = {
                Text("거래를 그만두고 돌아가시겠습니까?")
            },
            dismissButton = {
                Button(onClick = {
                    navController.navigate("BarterDetailPage/${index}")
                }) {
                    Text("네")
                }
            },
            confirmButton = {
                Button(onClick = {
                    showCancelDialog = false
                }) {
                    Text("아니오")
                }
            }
        )
    }
    if (showTradeProposalDialog) {
        AlertDialog(
            onDismissRequest = {
                showTradeProposalDialog = false
            },
            title = {
                Text(text = "거래 제안")
            },
            text = {
                Text("거래를 제안하시겠습니까?")
            },
            dismissButton = {
                Button(onClick = {
                    showTradeProposalDialog = false
                    barterviewModel.proposeBarterTrade(index?.toLongOrNull() ?: return@Button, selectedItemIndices)
                    // TODO: 거래 제안 결과에 따른 메시지 처리 로직 추가
                }) {
                    Text("예")
                }
            },
            confirmButton = {
                Button(onClick = {
                    showTradeProposalDialog = false
                }) {
                    Text("아니오")
                }
            }
        )
    }
}