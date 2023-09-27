package com.example.project

import SelectButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.AuctionViewModel

@Composable
fun auctionConfirmBuyPage(navController: NavHostController, index: String?) {
    val auctionViewModel: AuctionViewModel = hiltViewModel()
    val auctionConfirmBuy by auctionViewModel.auctionConfirmBuy.collectAsState()
    val auctionConfirmBuyNavi by auctionViewModel.auctionConfirmBuyNavi.collectAsState()
    val error by auctionViewModel.error.collectAsState() // 에러메시지 확인

    var showConfirmDialog by remember { mutableStateOf(false) }
    var showCancleDialog by remember { mutableStateOf(false) }

    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarText by remember { mutableStateOf("") }

    LaunchedEffect(index) {
        index?.toLongOrNull()?.let {
            auctionViewModel.fetchAccountDetails(it)
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
    LaunchedEffect(auctionConfirmBuyNavi) {
        if (auctionConfirmBuyNavi == true) {
            navController.navigate("WaitingPage")
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
                Text(text = snackbarText, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "기프티콘의 낙찰에 성공하셨습니다. 입금을 하시고 입금확정을 눌러주세요.",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // 이미지
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                val imagePainter = rememberAsyncImagePainter(model = auctionConfirmBuy?.gifticonDataImageName)
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 이름
            Text(text = "이름: ${auctionConfirmBuy?.giftconName ?: ""}")

            Spacer(modifier = Modifier.height(4.dp))

            // 판매가
            Text(text = "낙찰가: ${auctionConfirmBuy?.auctionPrice ?: 0}")

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "내용: ${auctionConfirmBuy?.postContent ?: ""}")

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "계좌번호 : ${auctionConfirmBuy?.userAccount ?: "N/A"}", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "거래은행 : ${auctionConfirmBuy?.userAccountBank ?: "N/A"}", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            // 버튼들
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { showConfirmDialog = true }) {
                    Text("입금완료")
                }

                Button(onClick = { showCancleDialog = true }) {
                    Text("낙찰취소")
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
                Text(text = "입금 완료 확인")
            },
            text = {
                Text("입금을 완료하셨습니까?" , fontSize = 18.sp)
            },
            dismissButton = {
                SelectButton(
                    text = "네",
                    onClick = {
                        auctionViewModel.completeAuctionPayment(index!!.toLong())
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
                Text(text = "거래 취소")
            },
            text = {
                Text("정말 거래를 취소하시겠습니까?")
            },
            dismissButton = {
                SelectButton(
                    text = "네",
                    onClick = {
                        auctionViewModel.cancelAuctionTrade(index!!.toLong())
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
}