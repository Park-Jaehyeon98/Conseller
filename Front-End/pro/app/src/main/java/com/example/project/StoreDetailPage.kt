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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.StoreViewModel

@Composable
fun StoreDetailPage(navController: NavHostController, index: String?) {
    val viewModel: StoreViewModel = hiltViewModel()
    val storeItems by viewModel.storeItems.collectAsState()         // 이전에 들고왔던 스토어 리스트
    val storeDetail by viewModel.storeDetail.collectAsState()       // 글 상세보기했을때 들고온 정보
    val scrollState = rememberScrollState()
    val userIdFromPreference = viewModel.getUserIdFromPreference()

    var selectedItemIndex by remember { mutableStateOf(userIdFromPreference) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    // storeItems의 index값이 들고온 값이랑 같은것들을 세팅
    val currentItem = storeItems.find { it.storeIdx.toString() == index }

    LaunchedEffect(key1 = index) {
        index?.toLongOrNull()?.let {
            viewModel.fetchStoreDetail(it)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState)
    ) {
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

            Text("판매가 : ${it.storePrice}", modifier = textStyle, fontSize = 18.sp)
            Text("유효기간 : ${it.gifticonEndDate}", modifier = textStyle, fontSize = 18.sp)
        }

        storeDetail?.let {
            Text("User: ${it.storeUserNickname}")
            Text("User: ${it.postContent}")
        }
        // 개발되면 지울예정인 더미 1개
        Text("판매자 : 테스트", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // 개발되면 지울예정인 더미 1개
        Text("내용 : 테스트내용", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            if (selectedItemIndex != storeDetail?.storeUserIdx && false/*개발용*/) {
                Button(onClick = { }) {
                    Text("즉시구매")
                }
            } else {
                Button(onClick = { navController.navigate("storeUpdate/${currentItem?.storeIdx}") }) {
                    Text("수정하기")
                }

                Spacer(modifier = Modifier.width(24.dp))

                Button(onClick = { showDeleteDialog = true }) {
                    Text("삭제하기")
                }
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
                    Text("정말 삭제하시겠습니까?")
                },
                dismissButton = {
                    Button(onClick = {
                        viewModel.deleteStoreItem(index!!.toLong())
                        navController.navigate("StorePage")
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
