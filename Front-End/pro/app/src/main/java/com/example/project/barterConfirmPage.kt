package com.example.project

import FormattedDateDot
import SelectButton
import UserDetailDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.BarterViewModel
import kotlinx.coroutines.delay
@Composable
fun BarterConfirmPage(navController: NavHostController, index: String?) {
    val barterViewModel: BarterViewModel = hiltViewModel()
    val confirmDetail by barterViewModel.barterConfirm.collectAsState()
    val barterConfirmNavi by barterViewModel.barterConfirmNavi.collectAsState()
    val error by barterViewModel.error.collectAsState()
    val scrollState = rememberScrollState()


    var showConfirmDialog by remember { mutableStateOf(false) }
    var showCancleDialog by remember { mutableStateOf(false) }
    var showUserDetailDialog by remember { mutableStateOf(false) } // 유저 자세히보기

    var showSnackbar by remember { mutableStateOf(false) } // 에러처리스낵바
    var snackbarText by remember { mutableStateOf("") }

    var selectedBuyUserIdx by remember { mutableStateOf<Long?>(null) } // 선택한 수락의 유저 인덱
    var selectedBuyUserNickname by remember { mutableStateOf<String?>(null) } // 유저 닉네임
    var selectedBuyUserImage by remember { mutableStateOf<String?>(null) } // 유저 이미지

    LaunchedEffect(Unit) {
        index?.toLongOrNull()?.let {
            barterViewModel.fetchBarterConfirmItems(it)
        }
    }
    LaunchedEffect(error) {
        if (error != null) {
            showSnackbar = true
            snackbarText = error!!
        }
    }
    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            delay(5000)
            showSnackbar = false
        }
    }
    LaunchedEffect(barterConfirmNavi) {
        if(barterConfirmNavi == true){
            navController.navigate("Home")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
    ) {
        if (showSnackbar) {
            Snackbar(
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                Text(
                    text = snackbarText,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            // barterConfirmList의 이미지들을 LazyRow로
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(confirmDetail?.barterConfirmList ?: listOf()) { item ->
                    Box(contentAlignment = Alignment.Center) {
                        val imagePainter =
                            rememberAsyncImagePainter(model = item.gifticonDataImageName)
                        Image(
                            painter = imagePainter,
                            contentDescription = null,
                            modifier = Modifier.size(200.dp),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // barterTradeList의 이름들
            confirmDetail?.barterConfirmList?.forEach { item ->
                Text(text = item.gifticonName)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 제목
            Text(text = "제목: ${confirmDetail?.barterName ?: ""}")

            Spacer(modifier = Modifier.height(8.dp))

            // 내용
            Text(text = "내용: ${confirmDetail?.barterText ?: ""}")

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                // 반복: barterTradeAllList의 모든 항목에 대해
                items(confirmDetail?.barterTradeAllList ?: listOf()) { tradeList ->

                    // 이미지 표시
                    LazyRow {
                        items(tradeList.barterTradeList) { item ->
                            Box(contentAlignment = Alignment.Center) {
                                val imagePainter = rememberAsyncImagePainter(model = item.gifticonDataImageName)
                                Image(
                                    painter = imagePainter,
                                    contentDescription = null,
                                    modifier = Modifier.size(200.dp),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 이름 표시
                    tradeList.barterTradeList.forEach { item ->
                        Text(text = "기프티콘 명 : ${item.gifticonName}")
                    }
                    // 유효기간 표시
                    tradeList.barterTradeList.forEach { item ->
                        FormattedDateDot(item.gifticonEndDate, fontSize = 18.sp, label = "유효기간 :")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 구매자 표시
                    Text(text = "구매자: ${tradeList.buyUserNickname}",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .clickable(
                                indication = rememberRipple(),  // Ripple 효과 추가
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                selectedBuyUserIdx = tradeList.buyUserIdx
                                selectedBuyUserImage = tradeList.buyUserImageUrl
                                selectedBuyUserNickname = tradeList.buyUserNickname
                                showUserDetailDialog = true
                            },
                        textDecoration = TextDecoration.Underline,  // 텍스트에 밑줄 추가
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // 버튼 표시
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(onClick = { showConfirmDialog = true
                            selectedBuyUserIdx = tradeList.buyUserIdx }) {
                            Text("수락")
                        }

                        Button(onClick = { showCancleDialog = true
                            selectedBuyUserIdx = tradeList.buyUserIdx}) {
                            Text("거절")
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }



    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = {
                showConfirmDialog = false
            },
            title = {
                Text(text = "교환 확정")
            },
            text = {
                Text("교환 하시겠습니까?")
            },
            dismissButton = {
                SelectButton(
                    text = "네",
                    onClick = {
                        barterViewModel.barterConfirm(index!!.toLong(), selectedBuyUserIdx!!, true)
                        showConfirmDialog = false
                    }
                )
            },
            confirmButton = {
                SelectButton(
                    text = "아니오",
                    onClick = {
                        showConfirmDialog = false
                    }
                )
            }
        )
    }
    if (showCancleDialog) {
        AlertDialog(
            onDismissRequest = {
                showCancleDialog = false
            },
            title = {
                Text(text = "거부하기")
            },
            text = {
                Text("교환을 거부하시겠습니까?")
            },
            dismissButton = {
                SelectButton(
                    text = "네",
                    onClick = {
                        barterViewModel.barterConfirm(index!!.toLong(), selectedBuyUserIdx!!, false)
                        showCancleDialog = false
                    }
                )
            },
            confirmButton = {
                SelectButton(
                    text = "아니오",
                    onClick = {
                        showCancleDialog = false
                    }
                )
            }
        )
    }
    // 구매자 상세보기
    if (showUserDetailDialog) {
        UserDetailDialog(
            userImageUrl = selectedBuyUserImage,
            userNickname = selectedBuyUserNickname,
            userDeposit = 0,
            onDismiss = { showUserDetailDialog = false },
            onReportClick = {
                navController.navigate("Inquiry/$selectedBuyUserIdx")
            },
            onMessageClick = {
                // 1:1 채팅 미완성
            }
        )
    }
}