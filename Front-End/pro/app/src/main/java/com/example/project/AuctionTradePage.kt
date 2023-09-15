package com.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.project.api.AuctionTradeCompleteResponseDTO
import com.example.project.viewmodels.AuctionViewModel

@Composable
fun AuctionTradePage(index: String?, navController: NavHostController) {
    val viewModel: AuctionViewModel = hiltViewModel()
    val auctionItems by viewModel.auctionItems.collectAsState()
    val tradeItems by viewModel.auctionTrades.collectAsState()
    val cancelTradeResult by viewModel.cancelTradeSuccessful.collectAsState()
    val paymentCompleted by viewModel.paymentCompleted.collectAsState()

    val currentItem = auctionItems.find { it.auctionIdx.toString() == index }

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showDepositConfirmDialog by remember { mutableStateOf(false) }
    var triggerEffect by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarText by remember { mutableStateOf("") }

    LaunchedEffect(key1 = index) {
        index?.toLongOrNull()?.let {
            viewModel.fetchAccountDetails(it)
        }
    }

    LaunchedEffect(key1 = triggerEffect) {
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

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "Home icon",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(text = "계좌번호 : ${tradeItems?.userAccount ?: "N/A"}", fontWeight = FontWeight.Bold)
            Text(text = "거래은행 : ${tradeItems?.userAccountBank ?: "N/A"}", fontWeight = FontWeight.Bold)
            Text(text = "거래가격 : ${currentItem?.upperPrice ?: "N/A"}", fontWeight = FontWeight.Bold)

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { showDepositConfirmDialog = true }, modifier = Modifier.weight(1f)) {
                    Text(text = "입금완료")
                }

                Button(onClick = { showDeleteDialog = true }, modifier = Modifier.weight(1f)) {
                    Text(text = "거래취소")
                }

                Button(onClick = { navController.navigate("WaitingPage") }, modifier = Modifier.weight(1f)) {
                    Text(text = "임시 완료 이동 버튼")
                }
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
                    Text("입금을 완료하셨습니까?")
                },
                dismissButton = {
                    Button(onClick = {
                        viewModel.completeAuctionPayment(index!!.toLong())
                        when (paymentCompleted) {
                            is AuctionTradeCompleteResponseDTO -> {
                                navController.navigate("DesiredDestination") // 원하는 경로로 변경하세요.
                                showDepositConfirmDialog = false
                            }
                            else -> {
                                showSnackbar = true
                                snackbarText = "입금 처리에 실패했습니다. 다시 시도해주세요."
                                showDepositConfirmDialog = false
                            }
                        }
                    }) {
                        Text("예")
                    }
                },
                confirmButton = {
                    Button(onClick = { showDepositConfirmDialog = false }) {
                        Text("아니오")
                    }
                }
            )
        }

        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text(text = "거래 취소") },
                text = { Text("정말 거래를 취소하시겠습니까?") },
                dismissButton = {
                    Button(onClick = {
                        viewModel.cancelAuctionTrade(index!!.toLong())
                        triggerEffect = true
                    }) {
                        Text("네")
                    }
                },
                confirmButton = {
                    Button(onClick = { showDeleteDialog = false }) {
                        Text("아니오")
                    }
                }
            )
        }

        if (showSnackbar) {
            Snackbar(dismissAction = { showSnackbar = true }) {
                Text(text = snackbarText)
            }
            LaunchedEffect(key1 = showSnackbar) {
                if (showSnackbar) {
                    kotlinx.coroutines.delay(3000)
                    showSnackbar = false
                }
            }
        }
    }
}
