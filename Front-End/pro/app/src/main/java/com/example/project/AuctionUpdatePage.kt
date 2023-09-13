package com.example.project

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.project.viewmodels.AuctionViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AuctionUpdatePage(index: String?, navController: NavHostController) {
    val viewModel: AuctionViewModel = hiltViewModel()
    val auctionItems by viewModel.auctionItems.collectAsState()   // 대분류 내용 들고오기
    val auctionDetail by viewModel.auctionDetail.collectAsState()   // text 내용 들고오기
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    // 상태값을 저장할 변수 추가
    var postContent by remember { mutableStateOf(auctionDetail?.postContent ?: "") }
    var showDeleteDialog by remember { mutableStateOf(false) }

    // 입력값 업데이트 처리
    fun updateContent(newText: String) {
        postContent = newText
    }

    // 선택된 경매 상품 데이터 가져오기
    val selectedAuctionItem = auctionItems?.find { it.auctionIdx == index?.toLong() }

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
            // 예시용. 나중에 coil써서 들어가야함
            Icon(Icons.Default.Home, contentDescription = "Image", Modifier.size(120.dp)) // 임시 사진

            // 기존 상한가, 하한가 표시
            Text(
                text = "상한가: ${selectedAuctionItem?.upperPrice}원",
                modifier = Modifier.padding(8.dp),
                color = Color.Gray
            )
            Text(
                text = "하한가: ${selectedAuctionItem?.lowerPrice}원",
                modifier = Modifier.padding(8.dp),
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 내용 수정 부분
        Text("게시글 내용 수정", modifier = Modifier.padding(bottom = 8.dp), fontSize = 20.sp)
        OutlinedTextField(
            value = postContent,
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
            horizontalArrangement = Arrangement.Center // 버튼들을 중앙에 위치시킵니다.
        ) {
            // 수정 버튼
            Button(
                onClick = {
                    // 게시글 내용 업데이트 로직 실행
                    viewModel.updateAuctionItem(index!!.toLong(), "123", postContent)
                    navController.navigate("AuctionDetailPage/${index}")
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            ) {
                Text("수정하기")
            }

            Spacer(modifier = Modifier.width(16.dp)) // 수정하기와 삭제하기 버튼 사이의 간격

            // 삭제 버튼
            Button(
                onClick = {
                    showDeleteDialog = true // 삭제 대화 상자 표시
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            ) {
                Text("삭제하기")
            }
        }
        // 삭제 대화 상자 추가
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
                        viewModel.deleteAuctionItem(index!!.toLong())
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