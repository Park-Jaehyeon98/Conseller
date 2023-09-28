package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.api.myGifticon
import com.example.project.ui.theme.BrandColor1
import com.example.project.viewmodels.MyPageViewModel

@Composable
fun MypageCoupon(navController: NavHostController) {
    val viewModel: MyPageViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getMyGifticon()
    }
    val getMyGift by viewModel.getMyGifticonResponse.collectAsState()

    val DeleteMyGift by viewModel.deleteGifticonResponse.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    var ChoiceStatus by remember { mutableStateOf(0) }

    var ChoiceGifticonIdx by remember { mutableStateOf<Long>(0) }


    val filteredGift = when (ChoiceStatus) {
        1 -> getMyGift.filter { it.gifticonStatus == "보관" }
        2 -> getMyGift.filter { it.gifticonStatus == "경매" }
        3 -> getMyGift.filter { it.gifticonStatus == "물물교환" }
        4 -> getMyGift.filter { it.gifticonStatus == "판매" }
        else -> getMyGift
    }

    val scrollstate = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollstate),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        SelectBar(onSelectionChanged = { ChoiceStatus = it })
        Divider(color = Color.Gray, thickness = 1.dp)
        if (showDialog) {
            AlertDialog(onDismissRequest = {
                showDialog = false
            }, title = {
                Text("Conseller")
            }, text = {
                Text("정말 삭제 하시겠습니까?")
            }, confirmButton = {
                Button(onClick = {
                    viewModel.DeleteUserGifticon(ChoiceGifticonIdx)
                    showDialog = false
                    navController.navigate("MypageCoupon")
                }) {
                    Text("확인")
                }

            }, dismissButton = {
                Button(onClick = {
                    showDialog = false
                }) {
                    Text("취소")
                }
            })
        }

        filteredGift.forEach { gifticonData ->
            ShowMyGifticon(gifticonData = gifticonData, isSelected = false, onClick = {
                navController.navigate("MyPageCouponDetail/${gifticonData.gifticonIdx}")

            }, onDelete = { showDialog = true }, onSelectGifticonIdx = { ChoiceGifticonIdx = it })
        }
    }
}

// 클릭 시 수행할 함수d
@Composable
fun SelectBar(onSelectionChanged: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val commontextsize = 18
        Row(modifier = Modifier.clickable(onClick = { onSelectionChanged(1) })) {
            Text(
                text = "내 쿠폰",
                fontSize = commontextsize.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.clickable(onClick = { onSelectionChanged(2) })) {
            Text(
                text = "경매 쿠폰",
                fontSize = commontextsize.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.clickable(onClick = { onSelectionChanged(3) })) {
            Text(
                text = "물물교환 쿠폰",
                fontSize = commontextsize.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.clickable(onClick = { onSelectionChanged(4) })) {
            Text(
                text = "스토어 쿠폰",
                fontSize = commontextsize.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }
    }
}


@Composable
fun ShowMyGifticon(
    gifticonData: myGifticon,
    isSelected: Boolean,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    onSelectGifticonIdx: (Long) -> Unit
) {
    val backgroundColor = if (isSelected) BrandColor1 else Color.Transparent

    // gifticonEndDate 앞 8글자 추출
    val rawDate = gifticonData.gifticonEndDate.substring(0, 8)

    // 날짜 형식으로 변환 (예: "20230927" -> "2023-09-27")
    val formattedDate =
        "${rawDate.substring(0, 4)}-${rawDate.substring(4, 6)}-${rawDate.substring(6, 8)}"

    // mainCategoryIdx 값에 따른 텍스트 할당
    val mainCategoryText = when (gifticonData.mainCategoryIdx) {
        1 -> "버거/치킨/피자"
        2 -> "편의점"
        3 -> "카페/베이커리"
        4 -> "아이스크림"
        5 -> "기타"
        else -> "" // 기타 경우에 대한 기본값, 필요에 따라 수정 가능
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .border(width = 1.dp, color = Color.Gray), // 테두리 추가
        horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.CenterStart, modifier = Modifier.size(130.dp)
        ) {
            val imagePainter = rememberAsyncImagePainter(model = gifticonData.gifticonDataImageUrl)
            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier.size(125.dp),
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = gifticonData.gifticonName,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = mainCategoryText, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = gifticonData.gifticonStatus,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = BrandColor1
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = formattedDate, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    onDelete()
                    onSelectGifticonIdx(gifticonData.gifticonIdx)
                }) {
                    Text("삭제")
                }
            }
        }
    }
}