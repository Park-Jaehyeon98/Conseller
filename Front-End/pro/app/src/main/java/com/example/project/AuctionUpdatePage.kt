package com.example.project

import SelectButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.AuctionViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AuctionUpdatePage(navController: NavHostController, index: String?) {
    val auctionViewModel: AuctionViewModel = hiltViewModel()
    val auctionDetail by auctionViewModel.auctionDetail.collectAsState()
    val error by auctionViewModel.error.collectAsState()
    val scrollState = rememberScrollState()

    val keyboardController = LocalSoftwareKeyboardController.current


    // 상태값을 저장할 변수 추가
    var auctionText by remember { mutableStateOf(auctionDetail?.postContent ?: "") }
    var showEditConfirmDialog by remember { mutableStateOf(false) }

    var showSnackbar by remember { mutableStateOf(false) } // 에러처리스낵바
    var snackbarText by remember { mutableStateOf("") }

    // 입력값 업데이트 처리
    fun updateContent(newText: String) {
        auctionText = newText
    }
    LaunchedEffect(Unit) {
        if (index != null) {
            auctionViewModel.fetchAuctionDetail(index.toLong())
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


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // 기존 정보 표시 부분
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
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
                val imagePainter =
                    rememberAsyncImagePainter(model = auctionDetail?.gifticonDataImageName)
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop,
                )
            }

            // 기존 상한가, 하한가 표시
            Text(
                text = "상한가: ${auctionDetail?.upperPrice}원",
                modifier = Modifier.padding(8.dp),
                color = Color.Gray,
                fontSize = 18.sp
            )
            Text(
                text = "하한가: ${auctionDetail?.lowerPrice}원",
                modifier = Modifier.padding(8.dp),
                color = Color.Gray,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 내용 수정 부분
        Text("게시글 내용 수정", modifier = Modifier.padding(bottom = 8.dp), fontSize = 24.sp)
        OutlinedTextField(
            value = auctionText,
            onValueChange = ::updateContent,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(200.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide() // 키보드 숨기기
            })
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            // 수정 버튼
            SelectButton(
                text = "수정하기",
                onClick = {
                    showEditConfirmDialog = true // 수정 확인 대화 상자 표시
                },
                modifier = Modifier.defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            )

            Spacer(modifier = Modifier.width(16.dp)) // 수정하기와 삭제하기 버튼 사이의 간격

            // 취소 버튼
            SelectButton(
                text = "취소하기",
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            )
        }

        // 수정 확인 대화 상자
        if (showEditConfirmDialog) {
            AlertDialog(
                onDismissRequest = {
                    showEditConfirmDialog = false
                },
                title = {
                    Text(text = "게시글 수정")
                },
                text = {
                    Text("수정하시겠습니까?")
                },
                dismissButton = {
                    SelectButton(
                        text = "네",
                        onClick = {
                            auctionViewModel.updateAuctionItem(index!!.toLong(), "2023.09.25.11.39.39", auctionText)
                            if(error == null) {
                                navController.navigate("AuctionDetailPage/${index}")
                                showEditConfirmDialog = false
                            }
                        }
                    )
                },
                confirmButton = {
                    SelectButton(
                        text = "아니오",
                        onClick = {
                            showEditConfirmDialog = false
                        }
                    )
                }
            )
        }
    }
}