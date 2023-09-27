package com.example.project

import FormattedDateDot
import SelectButton
import UserDetailDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun BarterDetailPage(index: String?, navController: NavHostController) {
    val barterViewModel: BarterViewModel = hiltViewModel()
    val barterItems by barterViewModel.barterItems.collectAsState()     // 이전에 들고왔던 리스트
    val barterDetail by barterViewModel.barterDetail.collectAsState()   // 상세보기로 들고온 리스트
    val scrollState = rememberScrollState()
    val error by barterViewModel.error.collectAsState()
    val userIdFromPreference = barterViewModel.getUserIdFromPreference()

    var selectedItemIndex by remember { mutableStateOf(userIdFromPreference) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showUserDetailDialog by remember { mutableStateOf(false) } // 유저 자세히보기

    var showSnackbar by remember { mutableStateOf(false) } // 에러처리스낵바
    var snackbarText by remember { mutableStateOf("") }

    // barterItems의 index값이 들고온 값이랑 같은것들을 세팅
    val currentItem = barterItems.find { it.barterIdx.toString() == index }

    // 페이지가 호출될 때 fetchBarterDetail 함수를 호출 (MVVM에 쬐끔 어긋나지만 훨씬 효율적)
    LaunchedEffect(key1 = index) {
        index?.toLongOrNull()?.let {
            barterViewModel.fetchBarterDetail(it)
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
            kotlinx.coroutines.delay(5000)
            showSnackbar = false
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
                Text(text = snackbarText, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            currentItem?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Gray, RoundedCornerShape(4.dp))
                        .padding(8.dp)
                ) {
                    Column {

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            val imagePainter =
                                rememberAsyncImagePainter(model = it.gifticonDataImageName)
                            Image(
                                painter = imagePainter,
                                contentDescription = null,
                                modifier = Modifier.size(200.dp),
                                contentScale = ContentScale.Crop,
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        val textStyle = Modifier.padding(vertical = 4.dp)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            FormattedDateDot(it.gifticonEndDate, fontSize = 18.sp)
                            Text(
                                "판매자 : ${barterDetail?.barterUserNickname}",
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .clickable(
                                        indication = rememberRipple(),  // Ripple 효과 추가
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) {
                                        showUserDetailDialog = true
                                    },
                                textDecoration = TextDecoration.Underline,  // 텍스트에 밑줄 추가
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            FormattedDateDot(it.gifticonEndDate, fontSize = 18.sp, label = "게시기한 :")
                        }
                    }
                }
                Text("제목 : ${barterDetail?.barterName}", fontSize = 18.sp)
                Text("내용 : ${barterDetail?.barterText}", fontSize = 18.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (selectedItemIndex != barterDetail?.barterUserIdx && false /*개발용*/) {
                        SelectButton(
                            text = "제안하기",
                            onClick = { navController.navigate("BarterTradeSelectPage/${currentItem.barterIdx}") }
                        )
                    } else {
                        Spacer(modifier = Modifier.width(24.dp))

                        SelectButton(
                            text = "수정하기",
                            onClick = { navController.navigate("barterUpdate/${currentItem.barterIdx}") }
                        )

                        Spacer(modifier = Modifier.width(24.dp))

                        SelectButton(
                            text = "삭제하기",
                            onClick = { showDeleteDialog = true }
                        )
                    }
                }

                if (showDeleteDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showDeleteDialog = false
                        },
                        title = {
                            Text(text = "게시글 삭제")
                        },
                        text = {
                            Text("정말 삭제하시겠습니까?", fontSize = 18.sp)
                        },
                        dismissButton = {
                            SelectButton(
                                text = "네",
                                onClick = {
                                    barterViewModel.deleteBarterItem(index!!.toLong())
                                    if(error == null) {
                                        navController.navigate("BarterPage")
                                        showDeleteDialog = false
                                    }
                                }
                            )
                        },
                        confirmButton = {
                            SelectButton(
                                text = "아니오",
                                onClick = { showDeleteDialog = false }
                            )
                        }
                    )
                }
                // 판매자 상세보기
                if (showUserDetailDialog) {
                    UserDetailDialog(
                        userImageUrl = barterDetail?.barterUserProfileUrl,
                        userNickname = barterDetail?.barterUserNickname,
                        userDeposit = barterDetail?.barterUserDeposit,
                        onDismiss = { showUserDetailDialog = false },
                        onReportClick = {
                            // Handle report logic here
                        },
                        onMessageClick = {
                            // Handle message sending logic here
                        }
                    )
                }
            }
        }
    }
}