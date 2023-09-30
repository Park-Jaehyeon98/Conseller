package com.example.project

import SelectButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun AuctionTradePage(index: String?, navController: NavHostController) {
    val auctionViewModel: AuctionViewModel = hiltViewModel()
    val auctionDetail by auctionViewModel.auctionDetail.collectAsState()    // 판매정보
    val tradeItems by auctionViewModel.auctionTrades.collectAsState()   // 계좌번호불러오기
    val cancelTradeResult by auctionViewModel.cancelTradeSuccessful.collectAsState()
    val error by auctionViewModel.error.collectAsState() // 에러메시지 확인


    var showDeleteDialog by remember { mutableStateOf(false) }
    var showDepositConfirmDialog by remember { mutableStateOf(false) }
    var triggerEffect by remember { mutableStateOf(false) }

    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        index?.toLongOrNull()?.let {
            auctionViewModel.fetchAccountDetails(it)
        }
        if(index != null) {
            auctionViewModel.fetchAuctionDetail(index.toLong())
        }
    }

    LaunchedEffect(triggerEffect) {
        if (triggerEffect) {
            when (cancelTradeResult) {
                true -> {
                    navController.navigate("AuctionDetailPage/${index}")
                    triggerEffect = false
                }
                false -> {
                    showSnackbar = true
                    snackbarText = "거래 취소에 실패했습니다. 다시 시도해주세요."
                    triggerEffect = false
                }
                else -> {
                    showSnackbar = true
                    snackbarText = "알 수 없는 오류가 발생했습니다."
                    triggerEffect = false
                }
            }
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

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                if (showSnackbar) {
                    Snackbar(
                        modifier = Modifier.align(Alignment.TopCenter)
                    ) {
                        Text(text = snackbarText, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        )
                    }
                }
                val imagePainter = rememberAsyncImagePainter(model = auctionDetail?.gifticonDataImageName)
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop,
                )
            }

            Text(text = "계좌번호 : ${tradeItems?.userAccount ?: "N/A"}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = "거래은행 : ${tradeItems?.userAccountBank ?: "N/A"}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = "거래가격 : ${auctionDetail?.upperPrice ?: "N/A"}", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                SelectButton(
                    text = "입금완료",
                    onClick = { showDepositConfirmDialog = true },
                    modifier = Modifier.weight(1f)
                )

                SelectButton(
                    text = "거래취소",
                    onClick = { showDeleteDialog = true },
                    modifier = Modifier.weight(1f)
                )

                SelectButton(
                    text = "(임시) 완료 버튼",
                    onClick = { navController.navigate("WaitingPage"){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    } },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        if (showDepositConfirmDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDepositConfirmDialog = false
                },
                title = {
                    Text(text = "입금 완료 확인")
                },
                text = {
                    Text("입금을 완료하셨습니까?" , fontSize = 18.sp)
                },
                dismissButton = {
                    SelectButton(
                        text = "예",
                        onClick = {
                            auctionViewModel.completeAuctionPayment(index!!.toLong())
                            when (error) {
                                null -> {
                                    navController.navigate("WaitingPage"){
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                    showDepositConfirmDialog = false
                                }
                                else -> {
                                    showSnackbar = true
                                    snackbarText = "응답실패. 완료를 다시 눌러주세요!"
                                    showDepositConfirmDialog = false
                                }
                            }
                        }
                    )
                },
                confirmButton = {
                    SelectButton(
                        text = "아니오",
                        onClick = { showDepositConfirmDialog = false }
                    )
                }
            )
        }

        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text(text = "거래 취소") },
                text = { Text("정말 거래를 취소하시겠습니까?", fontSize = 18.sp) },
                dismissButton = {
                    SelectButton(
                        text = "네",
                        onClick = {
                            if(error == null) {
                                auctionViewModel.cancelAuctionTrade(index!!.toLong())
                                triggerEffect = true
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
    }
}
