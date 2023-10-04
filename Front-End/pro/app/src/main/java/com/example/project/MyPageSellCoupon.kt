package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun MypageSellCoupon(navController: NavHostController) {
    val viewModel: MyPageViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getMyGifticon()
    }
    val getMyGift by viewModel.getMyGifticonResponse.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    var ChoiceStatus by remember { mutableStateOf(1) }

    var ChoiceGifticonIdx by remember { mutableStateOf<Long>(0) }


    val filteredGift = when (ChoiceStatus) {
        1 -> getMyGift.filter { it.gifticonStatus == "스토어" }
        2 -> getMyGift.filter { it.gifticonStatus == "경매" }
        3 -> getMyGift.filter { it.gifticonStatus == "물물교환" }
        else -> getMyGift
    }

    val scrollstate = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollstate)
            .background(Color.White),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        SelectSellBar(onSelectionChanged = { ChoiceStatus = it })
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
        if (filteredGift.isEmpty()) {
            showNothingGiftImage()
        } else filteredGift.forEach { gifticonData ->
            ShowMyGifticon(
                gifticonData = gifticonData,
                isSelected = false,
                onClick = {
                    navController.navigate("MyPageCouponDetail/${gifticonData.gifticonIdx}")
                },
                onDelete = { showDialog = true },
                onSelectGifticonIdx = { ChoiceGifticonIdx = it },
                status = gifticonData.gifticonStatus
            )
            Divider()
        }
    }
}


// 클릭 시 수행할 함수d
@Composable
fun SelectSellBar(onSelectionChanged: (Int) -> Unit) {
    var selectedOption by remember { mutableStateOf(1) }  // 상태 변수로 현재 선택된 항목을 저장

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        BarOption(text = "스토어 쿠폰", id = 1, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
        Spacer(modifier = Modifier.height(16.dp))
        BarOption(text = "경매 쿠폰", id = 2, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
        Spacer(modifier = Modifier.height(16.dp))
        BarOption(text = "물물교환", id = 3, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
    }
}

@Composable
fun SellBarOption(
    text: String,
    id: Int,
    selectedOption: Int,
    onSelectionChanged: (Int) -> Unit,
    onOptionClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = {
                onSelectionChanged(id)
                onOptionClicked(id)  // 여기에 selectedOption 값을 변경하는 로직을 상위 Composable에게 위임
            })
            .background(Color.White),
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (selectedOption == id) Color.Red else Color.Gray  // 선택된 항목이면 색상을 변경
        )
    }
}




