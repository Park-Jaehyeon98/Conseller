package com.example.project

import SelectButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.BarterViewModel

@Composable
fun BarterdetailPage(index: String?, navController: NavHostController) {
    val viewModel: BarterViewModel = hiltViewModel()
    val barterItems by viewModel.barterItems.collectAsState()     // 이전에 들고왔던 리스트
    val barterDetail by viewModel.barterDetail.collectAsState()   // 상세보기로 들고온 리스트
    val scrollState = rememberScrollState()
    val userIdFromPreference = viewModel.getUserIdFromPreference()

    var selectedItemIndex by remember { mutableStateOf(userIdFromPreference) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    // barterItems의 index값이 들고온 값이랑 같은것들을 세팅
    val currentItem = barterItems.find { it.barterIdx.toString() == index }

    // 페이지가 호출될 때 fetchBarterDetail 함수를 호출 (MVVM에 쬐끔 어긋나지만 훨씬 효율적)
    LaunchedEffect(key1 = index) {
        index?.toLongOrNull()?.let {
            viewModel.fetchBarterDetail(it)
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
            currentItem?.let {

                Spacer(modifier = Modifier.height(16.dp))

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    val imagePainter = rememberAsyncImagePainter(model = it.gifticonDataImageName)
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                val textStyle = Modifier.padding(vertical = 4.dp)

                Text("유효기간 : ${it.gifticonEndDate}", modifier = textStyle, fontSize = 18.sp)
                Text("게시기한 : ${it.barterEndDate}", modifier = textStyle, fontSize = 18.sp)

                barterDetail?.let {
                    Text("판매자 : ${it.barterUserNickname}", modifier = textStyle, fontSize = 18.sp)
                    Text("제목 : ${it.barterName}", modifier = textStyle, fontSize = 18.sp)
                    Text("상세설명 : ${it.barterText}", modifier = textStyle, fontSize = 18.sp)
                }

                Text("판매자 : 테스트유저", fontSize = 18.sp)
                Text("제목 : 테스트제목", fontSize = 18.sp)
                Text("상세설명 : 테스트내용", fontSize = 18.sp)

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
                                    viewModel.deleteBarterItem(index!!.toLong())
                                    navController.navigate("BarterPage")
                                    showDeleteDialog = false
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
    }
}