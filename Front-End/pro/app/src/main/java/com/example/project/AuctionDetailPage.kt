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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.AuctionViewModel
import com.example.project.viewmodels.MyPageViewModel
import formattedNumber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuctionDetailPage(navController: NavHostController, index: String?) {
    val auctionViewModel: AuctionViewModel = hiltViewModel()
    val myPageViewModel: MyPageViewModel = hiltViewModel()
    val auctionDetail by auctionViewModel.auctionDetail.collectAsState()   // 글 상세보기했을때 들고온 정보
    val getMyAuctionBidResponse by myPageViewModel.getMyAuctionBidResponse.collectAsState() // 내 경매 정보
    val scrollState = rememberScrollState()

    val userIdFromPreference = auctionViewModel.getUserIdFromPreference()

    var selectedItemIndex by remember { mutableStateOf(userIdFromPreference) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var bidPrice by remember { mutableStateOf("") } //입찰가격 받기
    var showBidDialog by remember { mutableStateOf(false) }
    var showConfirmBidDialog by remember { mutableStateOf(false) } // 입찰재확인
    var showAlertBidDialog by remember { mutableStateOf(false) } // 경고 다이얼로그
    var showConfirmDropDialog by remember { mutableStateOf(false) } // 낙찰하기
    var showUserDetailDialog by remember { mutableStateOf(false) } // 유저 자세히보기

    val error by auctionViewModel.error.collectAsState()
    val myerror by myPageViewModel.error.collectAsState()

    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarText by remember { mutableStateOf("") }


    // myAuctions의 index값이 들고온 값이랑 같은것들을 세팅
    val matchingAuction = getMyAuctionBidResponse.find { it.auctionIdx == index?.toLongOrNull() }

    // 입찰가 상위 3개matchingAuction
    val sortedBids = auctionDetail?.auctionBidList?.sortedByDescending { it.auctionBidPrice }
    val top3Bids = sortedBids?.take(3)
    val topBids = sortedBids?.getOrNull(0)?.auctionBidPrice ?: "Default Value or Fallback Object"

    LaunchedEffect(index) {
        index?.toLongOrNull()?.let {
            auctionViewModel.fetchAuctionDetail(it)
        }
    }
    LaunchedEffect(Unit) {
        myPageViewModel.getMyAuctionBid()
    }
    LaunchedEffect(error) {
        if (error != null) {
            showSnackbar = true
            snackbarText = error!!
        }
    }
    LaunchedEffect(myerror) {
        if (myerror != null) {
            showSnackbar = true
            snackbarText = myerror!!
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
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            if (showSnackbar) {
                Snackbar(
                ) {
                    Text(text = snackbarText, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }


            auctionDetail?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Gray, RoundedCornerShape(4.dp))
                        .padding(8.dp)
                ) {
                    Column {

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            val imagePainter = rememberAsyncImagePainter(model = it.gifticonDataImageName)
                            Image(
                                painter = imagePainter,
                                contentDescription = null,
                                modifier = Modifier.size(200.dp),
                                contentScale = ContentScale.Crop,
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${auctionDetail?.gifticonName}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        FormattedDateDot(it.gifticonEndDate, fontSize = 18.sp)
                        Text(
                            "판매자 : ${auctionDetail?.auctionUserNickname}",
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

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        FormattedDateDot(it.gifticonEndDate, fontSize = 18.sp, label = "경매기간 :")
                        Text("", fontSize = 18.sp) // 비어있는 텍스트
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "상한가 : ${formattedNumber(it.upperPrice.toString())} 원",
                            modifier = Modifier.weight(1f),
                            fontSize = 18.sp
                        )
                        Text("", fontSize = 18.sp) // 비어있는 텍스트
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "하한가 : ${formattedNumber(it.lowerPrice.toString())} 원",
                            modifier = Modifier.weight(1f),
                            fontSize = 18.sp
                        )
                        Text("", fontSize = 18.sp) // 비어있는 텍스트
                    }
                }
            }
        }

            Spacer(modifier = Modifier.height(16.dp))

            Text("내용 : ${auctionDetail?.postContent}", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                top3Bids?.forEachIndexed { index, bid ->
                    Text("입찰가 ${index + 1}순위 : ${formattedNumber(bid.auctionBidPrice.toString())} 원", fontSize = 18.sp)
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            matchingAuction?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text("현재 나의 입찰가: ${it.auctionBidPrice}", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp)) // 버튼들 사이의 간격 조절

            if (selectedItemIndex != auctionDetail?.auctionUserIdx) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SelectButton(
                        text = "즉시구매",
                        onClick = { navController.navigate("AuctionTradePage/${auctionDetail?.auctionIdx}") }
                    )

                    Spacer(modifier = Modifier.width(24.dp))

                    val bidButtonText = matchingAuction?.let { "재입찰하기" } ?: "입찰하기"

                    SelectButton(
                        text = bidButtonText,
                        onClick = { showBidDialog = true }
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (sortedBids.isNullOrEmpty()) {
                        Text(text = "현재 등록된 입찰이 없습니다", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    } else {
                        SelectButton(
                            text = "낙찰하기",
                            onClick = { showConfirmDropDialog = true }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp)) // 낙찰하기와 수정하기 사이의 간격 조절

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SelectButton(
                        text = "수정하기",
                        onClick = { navController.navigate("auctionUpdate/${auctionDetail?.auctionIdx}") }
                    )

                    Spacer(modifier = Modifier.width(24.dp))

                    SelectButton(
                        text = "삭제하기",
                        onClick = { showDeleteDialog = true }
                    )
                }
            }

            if (showBidDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showBidDialog = false
                    },
                    title = {
                        Text(text = "입찰가격 입력 *입찰취소는 0원을 입력하면 됩니다.")
                    },
                    text = {
                        TextField(
                            value = bidPrice,
                            onValueChange = {
                                if (it.all { char -> char.isDigit() }) {
                                    bidPrice = it
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text("입찰가격") }
                        )
                    },
                    dismissButton = {
                        SelectButton(
                            text = "입찰",
                            onClick = {
                                val bidValue = bidPrice.toIntOrNull() ?: 0

                                // 상한가보다 입찰가격이 같거나 높은 경우
                                if (bidValue >= auctionDetail?.upperPrice ?: Int.MAX_VALUE) {
                                    showBidDialog = false
                                    showAlertBidDialog = true
                                } else {
                                    showConfirmBidDialog = true
                                }
                            }
                        )
                    },
                    confirmButton = {
                        SelectButton(
                            text = "취소",
                            onClick = { showBidDialog = false }
                        )
                    }
                )
            }

            if (showConfirmBidDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showConfirmBidDialog = false
                    },
                    title = {
                        Text(text = "입찰 확인")
                    },
                    text = {
                        Text("${formattedNumber(bidPrice)}원 으로 입찰하시겠습니까?")
                    },
                    dismissButton = {
                        SelectButton(
                            text = "예",
                            onClick = {
                                index?.toLongOrNull()?.let {
                                    auctionViewModel.bidOnAuction(it, bidPrice.toInt())
                                    showBidDialog = false
                                    showConfirmBidDialog = false
                                    showAlertBidDialog = false
                                    showDeleteDialog = false
                                }
                            }
                        )
                    },
                    confirmButton = {
                        SelectButton(
                            text = "아니오",
                            onClick = { showConfirmBidDialog = false }
                        )
                    }
                )
            }

            if (showAlertBidDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showAlertBidDialog = false
                    },
                    title = { Text("경고") },
                    text = { Text("입찰 가격이 상한가보다 같거나 높습니다.") },
                    confirmButton = {
                        Button(onClick = {
                            showAlertBidDialog = false
                        }) {
                            Text("확인")
                        }
                    }
                )
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
                                auctionViewModel.deleteAuctionItem(index!!.toLong())
                                if (error == null) {
                                    navController.navigate("AuctionPage")
                                    showDeleteDialog = false
                                }
                            }
                        )
                    },
                    confirmButton = {
                        SelectButton(
                            text = "아니오",
                            onClick = {
                                showDeleteDialog = false
                            }
                        )
                    }
                )
            }
            if (showConfirmDropDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showConfirmDropDialog = false
                    },
                    title = {
                        Text(text = "낙찰 확인")
                    },
                    text = {
                        Text("${topBids}원에 낙찰하시겠습니까?", fontSize = 18.sp)
                    },
                    dismissButton = {
                        SelectButton(
                            text = "예",
                            onClick = {
                                // TODO
                                showConfirmDropDialog = false
                            }
                        )
                    },
                    confirmButton = {
                        SelectButton(
                            text = "아니오",
                            onClick = { showConfirmDropDialog = false }
                        )
                    }
                )
            }
            // 유저 상세보기
            if (showUserDetailDialog) {
                UserDetailDialog(
                    userImageUrl = auctionDetail?.auctionUserProfileUrl,
                    userNickname = auctionDetail?.auctionUserNickname,
                    userDeposit = auctionDetail?.auctionUserDeposit,
                    onDismiss = { showUserDetailDialog = false },
                    onReportClick = {
                        navController.navigate("Inquiry/${auctionDetail?.auctionUserIdx}")
                    },
                    onMessageClick = {
                        // Handle message sending logic here
                    }
                )
            }
        }
    }
}