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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.StoreViewModel

@Composable
fun StoreTradePage(index: String?, navController: NavHostController) {
    val storeViewModel: StoreViewModel = hiltViewModel()
    val storeItems by storeViewModel.storeItems.collectAsState()
    val tradeItems by storeViewModel.storeTrades.collectAsState()
    val cancelTradeResult by storeViewModel.cancelTradeSuccessful.collectAsState()
    val paymentCompleted by storeViewModel.error.collectAsState()

    val currentItem = storeItems.find { it.storeIdx.toString() == index }

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showDepositConfirmDialog by remember { mutableStateOf(false) }
    var triggerEffect by remember { mutableStateOf(false) }

    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarText by remember { mutableStateOf("") }

    LaunchedEffect(index) {
        index?.toLongOrNull()?.let {
            storeViewModel.fetchAccountDetails(it)
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

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                val imagePainter =
                    rememberAsyncImagePainter(model = currentItem?.gifticonDataImageName)
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop,
                )
            }

            Text(
                text = "계좌번호 : ${tradeItems?.userAccount ?: "N/A"}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "거래은행 : ${tradeItems?.userAccountBank ?: "N/A"}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "거래가격 : ${currentItem?.storePrice ?: "N/A"}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                SelectButton(
                    text = "입금완료",
                    modifier = Modifier.weight(1f),
                    onClick = { showDepositConfirmDialog = true }
                )

                SelectButton(
                    text = "거래취소",
                    modifier = Modifier.weight(1f),
                    onClick = { showDeleteDialog = true }
                )

                SelectButton(
                    text = "임시 완료 이동 버튼",
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate("WaitingPage") }
                )
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
                        Text("입금을 완료하셨습니까?", fontSize = 18.sp)
                    },
                    dismissButton = {
                        SelectButton(
                            text = "예",
                            onClick = {
                                storeViewModel.completeStorePayment(index!!.toLong())
                                when (paymentCompleted) {
                                    null -> {
                                        navController.navigate("DesiredDestination") // 원하는 경로로 변경하세요.
                                        showDepositConfirmDialog = false
                                    }
                                    else -> {
                                        showSnackbar = true
                                        snackbarText = "응답실패. 다시 시도해주세요."
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
                                storeViewModel.cancelStoreTrade(index!!.toLong())
                                triggerEffect = true
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
