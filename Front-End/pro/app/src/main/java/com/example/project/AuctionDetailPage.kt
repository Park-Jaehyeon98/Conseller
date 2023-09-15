package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.AuctionViewModel
import com.example.project.viewmodels.MyAuctionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuctionDetailPage(navController: NavHostController, index: String?) {
    val auctionViewModel: AuctionViewModel = hiltViewModel()
    val myAuctionViewModel: MyAuctionViewModel = hiltViewModel()
    val auctionItems by auctionViewModel.auctionItems.collectAsState()     // 이전에 들고왔던 옥션리스트
    val auctionDetail by auctionViewModel.auctionDetail.collectAsState()   // 글 상세보기했을때 들고온 정보
    val scrollState = rememberScrollState()
    val myAuctions by myAuctionViewModel.myAuctions.collectAsState()
    val currentBidResponse by auctionViewModel._bidResponseState.collectAsState(null) // 에러메시지
    val userIdFromPreference = auctionViewModel.getUserIdFromPreference()

    var selectedItemIndex by remember { mutableStateOf(userIdFromPreference) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var bidPrice by remember { mutableStateOf("") } //입찰가격 받기
    var showBidDialog by remember { mutableStateOf(false) }
    var showConfirmBidDialog by remember { mutableStateOf(false) } // 입찰재확인
    var showAlertBidDialog by remember { mutableStateOf(false) } // 경고 다이얼로그

    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarText by remember { mutableStateOf("") }


    // auctionItems의 index값이 들고온 값이랑 같은것들을 세팅
    val currentItem = auctionItems.find { it.auctionIdx.toString() == index }

    // myAuctions의 index값이 들고온 값이랑 같은것들을 세팅
    val matchingAuction = myAuctions.find { it.auctionIdx == index?.toLongOrNull() }

    LaunchedEffect(key1 = index) {
        index?.toLongOrNull()?.let {
            auctionViewModel.fetchAuctionDetail(it)
        }
    }
    LaunchedEffect(key1 = Unit) {
        myAuctionViewModel.fetchMyAuctions()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        if (showSnackbar) {
            Snackbar(dismissAction = { showSnackbar = false }) {
                Text(text = snackbarText)
            }
            LaunchedEffect(key1 = showSnackbar) {
                if (showSnackbar&&currentBidResponse?.success == true) {
                    kotlinx.coroutines.delay(3000)
                    showSnackbar = false
                }
            }
        }

        if (currentBidResponse?.success == true) {
            snackbarText = "입찰이 성공적으로 완료되었습니다!"
            showSnackbar = true
        } else if (currentBidResponse != null) {
            snackbarText = currentBidResponse!!.message ?: "입찰에 문제가 발생했습니다."
            showSnackbar = true
        } else if (currentBidResponse?.success != true){
            snackbarText = "인터넷 연결을 확인해주세요."
            showSnackbar = true
        }

        currentItem?.let {

            Spacer(modifier = Modifier.height(16.dp))

            // 이미지를 표시
            val imagePainter = rememberAsyncImagePainter(model = it.gifticonDataImageName)
            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 글자 크기 조정
            val textStyle = Modifier.padding(vertical = 4.dp)  // 여백을 추가하여 가독성 향상

            Text("상한가 : ${it.upperPrice}", modifier = textStyle, fontSize = 18.sp)
            Text("하한가 : ${it.lowerPrice}", modifier = textStyle, fontSize = 18.sp)
            Text("유효기간 : ${it.gifticonEndDate}", modifier = textStyle, fontSize = 18.sp)
            Text("경매기간 : ${it.auctionEndDate}", modifier = textStyle, fontSize = 18.sp)
        }

        auctionDetail?.let {
            Text("User: ${it.auctionUserNickname}")
            Text("User: ${it.postContent}")

            it.auctionBid.forEach { bid ->
                Text("입찰 : ${bid.auctionBidPrice}", fontSize = 18.sp)
            }
        }
        // 개발되면 지울예정인 더미 4개
        Text("판매자 : 테스트", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Text("내용 : 테스트내용", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Text("입찰가 1 : 800", fontSize = 18.sp)
        Text("입찰가 2 : 700", fontSize = 18.sp)
        Text("입찰가 3 : 800", fontSize = 18.sp)


        Spacer(modifier = Modifier.height(16.dp))

        matchingAuction?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text("현재 나의 입찰가: ${it.auctionBidPrice}", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            if (selectedItemIndex != auctionDetail?.auctionUserIdx && true /*개발용*/) {
                Button(onClick = { navController.navigate("AuctionTradePage/${currentItem?.auctionIdx}") }) {
                    Text("즉시구매")
                }

                Spacer(modifier = Modifier.width(24.dp))

                val bidButtonText = matchingAuction?.let { "재입찰하기" } ?: "입찰하기"
                Button(onClick = { showBidDialog = true }) {
                    Text(bidButtonText)
                }
            } else {
                Button(onClick = { }) {
                    Text("낙찰하기")
                }

                Spacer(modifier = Modifier.width(24.dp))

                Button(onClick = { navController.navigate("auctionUpdate/${currentItem?.auctionIdx}") }) {
                    Text("수정하기")
                }

                Spacer(modifier = Modifier.width(24.dp))

                Button(onClick = { showDeleteDialog = true }) {
                    Text("삭제하기")
                }
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
                    Button(onClick = {
                        val bidValue = bidPrice.toIntOrNull() ?: 0

                        // 상한가보다 입찰가격이 같거나 높은 경우
                        if (bidValue >= currentItem?.upperPrice ?: Int.MAX_VALUE) {
                            showBidDialog = false
                            showAlertBidDialog = true
                        } else {
                            showConfirmBidDialog = true
                        }
                    }) {
                        Text("입찰")
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        showBidDialog = false
                    }) {
                        Text("취소")
                    }
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
                    Text("${bidPrice}원 으로 입찰하시겠습니까?")
                },
                dismissButton = {
                    Button(onClick = {
                        index?.toLongOrNull()?.let {
                            auctionViewModel.bidOnAuction(it, bidPrice.toInt())
                            showBidDialog = false
                            showConfirmBidDialog = false
                            showAlertBidDialog = false
                            showDeleteDialog = false
                        }
                    }) {
                        Text("예")
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        showConfirmBidDialog = false
                    }) {
                        Text("아니오")
                    }
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
                    Text("정말 삭제하시겠습니까?")
                },
                dismissButton = {
                    Button(onClick = {
                        auctionViewModel.deleteAuctionItem(index!!.toLong())
                        navController.navigate("AuctionPage")
                        showDeleteDialog = false
                    }) {
                        Text("네")
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        showDeleteDialog = false
                    }) {
                        Text("아니오")
                    }
                }
            )
        }
    }
}