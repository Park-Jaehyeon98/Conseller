package com.example.project

import FormattedDateText
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.project.viewmodels.MyPageViewModel
import showNothingActionImage

@Composable
fun MypageBarter(navController: NavHostController) {
    val viewModel: MyPageViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getMyBarter()
        viewModel.getMyBarterRequest()
    }
    var ChoiceStatus by remember { mutableStateOf(1) }
    val getMyBarter by viewModel.getMyBarterResponse.collectAsState()
    val getMyBarterRequest by viewModel.getMyBarterRequestResponse.collectAsState()
    val scrollstate = rememberScrollState()


    val filteredBarter = when (ChoiceStatus) {
        1 -> getMyBarter.filter { it.barterStatus == "교환 가능" }
        2 -> getMyBarter.filter { it.barterStatus == "제안" }
        else -> getMyBarter
    }

    val filteredBarterBid = when (ChoiceStatus) {
        3 -> getMyBarterRequest.filter { it.barterRequestStatus == "요청" }
        4 -> getMyBarterRequest.filter { it.barterRequestStatus == "수락" }
        else -> getMyBarterRequest
    }


    Column(
        modifier = Modifier.verticalScroll(scrollstate),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        SelectBarter(onSelectionChanged = { ChoiceStatus = it })
        Divider(color = Color.Gray, thickness = 1.dp)
        if (filteredBarter.isEmpty() || filteredBarterBid.isEmpty()) {
            showNothingActionImage()
        } else {
            if (ChoiceStatus <= 2) {
                filteredBarter.forEach { item ->
                    ShowMyBarter(image = item.barterHostItems[0].gifticonDataImageUrl,
                        name = item.barterHostItems[0].gifticonName,
                        gifticonTime = item.barterHostItems[0].gifticonEndDate,
                        barterTime = item.barterEndDate,
                        isDeposit = false,
                        preper = item.preferSubCategory ?: "선호없음",
                        title = item.barterName,
                        onItemClick = {
                            if (ChoiceStatus == 1) {
                                navController.navigate("BarterDetailPage/${item.barterIdx}")
                            } else if (ChoiceStatus == 2) {
                                navController.navigate("barterConfirmPage/${item.barterIdx}")
                            }
                        }

                    )
                }
            } else {
                filteredBarterBid.forEach { item ->
                    ShowMyBarter(image = item.myBarterResponseDto.barterHostItems[0].gifticonDataImageUrl,
                        name = item.myBarterResponseDto.barterHostItems[0].gifticonName,
                        gifticonTime = item.myBarterResponseDto.barterHostItems[0].gifticonEndDate,
                        barterTime = item.myBarterResponseDto.barterEndDate,
                        isDeposit = false,
                        preper = item.myBarterResponseDto.preferSubCategory ?: "선호없음",
                        title = item.barterName,
                        onItemClick = {
                            if (item.barterRequestStatus == "요청") {
                                navController.navigate("BarterDetailPage/${item.barterIdx}")
                            }
                        }

                    )
                }
            }
        }
    }
}

@Composable
fun SelectBarter(onSelectionChanged: (Int) -> Unit) {
    var selectedOption by remember { mutableStateOf(0) }  // 상태 변수로 현재 선택된 항목을 저장

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        BarterOption(text = "내 교환", id = 1, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
        BarterOption(text = "교환 중", id = 2, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
        BarterOption(text = "내 제안", id = 3, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
        BarterOption(text = "내 제안(확정)", id = 4, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
    }
}

@Composable
fun BarterOption(
    text: String,
    id: Int,
    selectedOption: Int,
    onSelectionChanged: (Int) -> Unit,
    onOptionClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier.clickable(onClick = {
            onSelectionChanged(id)  // 선택된 항목의 ID를 콜백으로 알림
            onOptionClicked(id) // 여기에서 selectedOption 값을 변경
        })
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (selectedOption == id) Color.Red else Color.Gray  // 선택된 항목이면 색상을 변경
        )
    }
}


@Composable
fun ShowMyBarter(
    image: String,
    name: String,
    gifticonTime: String,
    barterTime: String,
    isDeposit: Boolean,
    preper: String,
    title: String,
    onItemClick: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(360.dp)
        .padding(2.dp)
        .background(Color.White, shape = RoundedCornerShape(8.dp))
        .shadow(elevation = 4.dp, shape = RoundedCornerShape(4.dp))
        .clickable { onItemClick() }
        .padding(8.dp)) {
        // 65% 이미지
        Box(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxWidth()
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            // 이미지
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }

        // 10% 이름 및 유효기간
        Row(
            modifier = Modifier
                .weight(0.18f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                FormattedDateText(gifticonTime, "유효기간")
            }
        }

        // 구분 줄
        Spacer(modifier = Modifier.height(4.dp))
        Divider(color = Color.Gray, modifier = Modifier.padding(horizontal = 12.dp))
        Spacer(modifier = Modifier.height(4.dp))

        // 15% 박스1
        Box(
            modifier = Modifier
                .weight(0.08f)
                .padding(horizontal = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 40% 인기
                Text(
                    text = if (isDeposit) "보증금 있음" else "보증금 없음",
                    modifier = Modifier.weight(0.4f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                // 60% 박스2
                Column(
                    modifier = Modifier.weight(0.6f), horizontalAlignment = Alignment.End
                ) {
                    Text(
                        "대상품목: $preper",
                        modifier = Modifier.weight(0.4f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
        // 5% 경매기간
        FormattedDateText(
            gifticonTime = barterTime,
            prefix = "마감일",
            modifier = Modifier
                .weight(0.08f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )
    }
}