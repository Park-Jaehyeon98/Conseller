package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.BarterViewModel

@Composable
fun BarterdetailPage(index: String?) {
    val viewModel: BarterViewModel = hiltViewModel()
    val barterItems by viewModel.barterItems.collectAsState()     // 이전에 들고왔던 리스트
    val barterDetail by viewModel.barterDetail.collectAsState()   // 상세보기로 들고온 리스트
    val scrollState = rememberScrollState()

    var selectedItemIndex by remember { mutableStateOf<Long?>(null) }

    // barterItems의 index값이 들고온 값이랑 같은것들을 세팅
    val currentItem = barterItems.find { it.barterIdx.toString() == index }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
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

            currentItem?.let {
                Text("유효기간 : ${it.gifticonEndDate}", modifier = textStyle, fontSize = 18.sp)
                Text("게시기한 : ${it.barterEndDate}", modifier = textStyle, fontSize = 18.sp)
            }

            barterDetail?.let {
                Text("판매자 : ${it.barterUserNickname}", modifier = textStyle, fontSize = 18.sp)
                Text("제목 : ${it.barterName}", modifier = textStyle, fontSize = 18.sp)
                Text("상세설명 : ${it.barterText}", modifier = textStyle, fontSize = 18.sp)
            }
        }

        // 개발되면 지울예정인 더미 3개
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
            // 이 로직도 실제 바터 상황에 따라 조정해야 합니다.
            if (selectedItemIndex != barterDetail?.barterUserIdx || selectedItemIndex == null) {
                Button(onClick = {  }) {
                    Text("제안하기")
                }
            } else {
                Spacer(modifier = Modifier.width(24.dp))

                Button(onClick = {  }) {
                    Text("등록취소")
                }
            }
        }
    }
}
