package com.example.project

import FormattedDateDot
import SelectButton
import UserDetailDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.ui.theme.BrandColor1
import com.example.project.viewmodels.StoreViewModel
import formattedNumber

@Composable
fun StoreDetailPage(navController: NavHostController, index: String?) {
    val storeViewModel: StoreViewModel = hiltViewModel()
    val storeDetail by storeViewModel.storeDetail.collectAsState()
    val error by storeViewModel.error.collectAsState()
    val scrollState = rememberScrollState()
    val userIdFromPreference = storeViewModel.getUserIdFromPreference()

    var selectedItemIndex by remember { mutableStateOf(userIdFromPreference) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showUserDetailDialog by remember { mutableStateOf(false) } // 유저 자세히보기

    var showSnackbar by remember { mutableStateOf(false) } // 에러처리스낵바
    var snackbarText by remember { mutableStateOf("") }


    LaunchedEffect(index) {
        index?.toLongOrNull()?.let {
            storeViewModel.fetchStoreDetail(it)
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
                Text(
                    text = snackbarText,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            storeDetail?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Gray, RoundedCornerShape(4.dp))
                        .padding(8.dp)
                ) {
                    Column {

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                        ) {
                            val imagePainter =
                                rememberAsyncImagePainter(model = it.gifticonDataImageName)
                            Image(
                                painter = imagePainter,
                                contentDescription = null,
//                                modifier = Modifier.size(200.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentScale = ContentScale.Fit,
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${storeDetail?.gifticonName}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color.DarkGray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            FormattedDateDot(it.gifticonEndDate, fontSize = 18.sp)
                            Text(
                                "판매자 : ${storeDetail?.storeUserNickname}",
                                fontSize = 18.sp,
                                modifier = Modifier.clickable(indication = rememberRipple(),  // Ripple 효과 추가
                                    interactionSource = remember { MutableInteractionSource() }) {
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
                            Text(
                                "판매가 : ${formattedNumber(it.storePrice.toString())} 원",
                                fontSize = 18.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "제품 설명",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Gray, RoundedCornerShape(4.dp))
                        .padding(8.dp)
                ) {
                    Column {
                        Box(
                            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text("${storeDetail?.postContent}", fontSize = 18.sp)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                consellerHelp()
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (selectedItemIndex != storeDetail?.storeUserIdx) {
                        SelectButton(text = "구매하기", onClick = {
                            navController.navigate("StoreTradePage/${storeDetail?.storeIdx}") {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        })
                    } else {
                        SelectButton(text = "수정하기",
                            onClick = { navController.navigate("storeUpdate/${storeDetail?.storeIdx}") })

                        Spacer(modifier = Modifier.width(24.dp))

                        SelectButton(text = "삭제하기", onClick = { showDeleteDialog = true })
                    }
                }
            }

            if (showDeleteDialog) {
                AlertDialog(onDismissRequest = {
                    showDeleteDialog = false
                }, title = {
                    Text(text = "게시글 삭제")
                }, text = {
                    Text("정말 삭제하시겠습니까?")
                }, dismissButton = {
                    SelectButton(text = "네", onClick = {
                        storeViewModel.deleteStoreItem(index!!.toLong())
                        navController.navigate("StorePage")
                        showDeleteDialog = false
                    })
                }, confirmButton = {
                    SelectButton(text = "아니오", onClick = { showDeleteDialog = false })
                })
            }
            // 판매자 상세보기
            if (showUserDetailDialog) {
                UserDetailDialog(userImageUrl = storeDetail?.storeUserProfileUrl,
                    userNickname = storeDetail?.storeUserNickname,
                    userDeposit = storeDetail?.storeUserDeposit,
                    onDismiss = { showUserDetailDialog = false },
                    onReportClick = {
                        navController.navigate("Inquiry/${storeDetail?.storeUserIdx}")
                    },
                    onMessageClick = {
                        // Handle message sending logic here
                    })
            }
        }
    }
}

@Composable
fun consellerHelp(

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Gray, RoundedCornerShape(4.dp))
            .padding(8.dp)
    ) {
        Column {

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        "ConSeller 주의사항",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = BrandColor1
                    )

                    Text(
                        "• 정확한 계좌번호 확인:\n 제공된 계좌번호가 올바른지 반드시 확인하세요. \n 작은 오류도 큰 문제로 이어질 수 있습니다.",
                        color = Color.DarkGray
                    )

                    Text(
                        "• 입금 전 계좌주 정보 확인:\n 입금하기 전에 제공된 계좌의 예금주 정보를 확인하세요.\n 계좌주와 거래 상대의 정보가 일치하는지 점검하세요.",
                        color = Color.DarkGray
                    )

                    Text(
                        "• 입금 금액 및 내역 확인:\n 정확한 금액을 입금했는지, 그리고 입금 내역에 \n 상대방이 맞는 지 확인하세요.",
                        color = Color.DarkGray
                    )

                    Text(
                        "• 거래 내역 활용:\n 거래의 문제 발생 시 거래 내역을 통해 문의해주세요.", color = Color.DarkGray
                    )
                }
            }
        }
    }
}