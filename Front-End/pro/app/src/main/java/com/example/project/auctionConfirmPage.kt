package com.example.project

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.AuctionViewModel
import formattedNumber
import kotlinx.coroutines.delay

@Composable
fun AuctionConfirmPage(navController: NavHostController, index: String?) {
    val auctionViewModel: AuctionViewModel = hiltViewModel()
    val confirmDetail by auctionViewModel.auctionConfirm.collectAsState()
    val auctionConfirmNavi by auctionViewModel.auctionConfirmNavi.collectAsState()
    val error by auctionViewModel.error.collectAsState()


    var showConfirmDialog by remember { mutableStateOf(false) }
    var showCancleDialog by remember { mutableStateOf(false) }
    var showUserDetailDialog by remember { mutableStateOf(false) } // 유저 자세히보기

    var showSnackbar by remember { mutableStateOf(false) } // 에러처리스낵바
    var snackbarText by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        index?.toLongOrNull()?.let {
            auctionViewModel.fetchAuctionConfirmItems(it)
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
    LaunchedEffect(auctionConfirmNavi) {
        if (auctionConfirmNavi) {
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Gray.copy(alpha = 0.76f), RoundedCornerShape(4.dp))
                .padding(22.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "${confirmDetail.buyUserNickname}님이 입금하였습니다.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = Color.DarkGray
                )
                Text(
                    text = "입금내역을 확인하고 확정을 눌러주세요.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = Color.DarkGray
                )

                // 이미지
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    val imagePainter =
                        rememberAsyncImagePainter(model = confirmDetail.gifticonDataImageName)
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 이름
                Text(
                    text = "기프티콘 명: ${confirmDetail.giftconName}",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 판매가
                val price = confirmDetail.auctionPrice
                val formatPrice = formattedNumber(price.toString())
                Text(text = "판매가  ${formatPrice}원")
                Spacer(modifier = Modifier.height(8.dp))
                // 내용
                Text(text = "제품 설명")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = " ${confirmDetail.postContent}")

                Spacer(modifier = Modifier.height(8.dp))

                // 구매자
                Text(
                    text = "구매자: ${confirmDetail.buyUserNickname}",
                    fontSize = 18.sp,
                    modifier = Modifier.clickable(indication = rememberRipple(),  // Ripple 효과 추가
                        interactionSource = remember { MutableInteractionSource() }) {
                        showUserDetailDialog = true
                    },
                    textDecoration = TextDecoration.Underline,  // 텍스트에 밑줄 추가
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼들
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SelectButton(text = "미입금", onClick = { showCancleDialog = true })

                    Spacer(modifier = Modifier.width(24.dp))

                    SelectButton(text = "확정", onClick = { showConfirmDialog = true })
                }
            }
        }
    }
    if (showConfirmDialog) {
        AlertDialog(onDismissRequest = {
            showConfirmDialog = false
        }, title = {
            Text(text = "입금 확정")
        }, text = {
            Text("입금이 확인 되셨습니까?")
        }, dismissButton = {
            SelectButton(text = "네", onClick = {
                auctionViewModel.auctionConfirm(index!!.toLong(), true)
                showConfirmDialog = false
            })
        }, confirmButton = {
            SelectButton(text = "아니오", onClick = {
                showConfirmDialog = false
            })
        })
    }
    if (showCancleDialog) {
        AlertDialog(onDismissRequest = {
            showCancleDialog = false
        }, title = {
            Text(text = "취소하기")
        }, text = {
            Text("입금이 안되셨습니까?")
        }, dismissButton = {
            SelectButton(text = "네", onClick = {
                auctionViewModel.auctionConfirm(index!!.toLong(), false)
                showCancleDialog = false
            })
        }, confirmButton = {
            SelectButton(text = "아니오", onClick = {
                showCancleDialog = false
            })
        })
    }
    // 구매자 상세보기
    if (showUserDetailDialog) {
        UserDetailDialog(userImageUrl = confirmDetail.buyUserImageUrl,
            userNickname = confirmDetail.buyUserNickname,
            userDeposit = 0,
            onDismiss = { showUserDetailDialog = false },
            onReportClick = {
                navController.navigate("Inquiry/${confirmDetail.buyUserIdx}")
            },
            onMessageClick = {
                // 1:1 채팅 미완성
            })
    }
}