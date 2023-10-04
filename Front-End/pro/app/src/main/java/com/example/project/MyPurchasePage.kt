package com.example.project

import FormattedDateText
import ShowMyPurchase
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.project.api.myGifticon
import com.example.project.ui.theme.BrandColor1
import com.example.project.viewmodels.MyPageViewModel
import formattedNumber
import showNothingActionImage

@Composable
fun MyPurchasePage(navController: NavHostController) {
    val viewModel: MyPageViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getMyPurchase()
        viewModel.getMyAuctionBid()
        viewModel.getMyBarterRequest()
    }
    val getMyPurchase by viewModel.getMyPurchaseResponse.collectAsState()
    val getMyAuctionBid by viewModel.getMyAuctionBidResponse.collectAsState()
    val getMyBarterRequest by viewModel.getMyBarterRequestResponse.collectAsState()


    var ChoiceStatus by remember { mutableStateOf(1) }


    val filteredStore = when (ChoiceStatus) {
        1 -> getMyPurchase.filter { it.storeStatus == "낙찰" }
        else -> getMyPurchase
    }
    val filteredAuction = when (ChoiceStatus) {
        2 -> getMyAuctionBid.filter { it.auctionBidStatus == "낙찰" }
        else -> getMyAuctionBid
    }
    val filteredBarter = when (ChoiceStatus) {
        3 -> getMyBarterRequest.filter { it.barterRequestStatus == "수락" }
        else -> getMyBarterRequest
    }


    val scrollstate = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollstate),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        SelectPurchaseBar(onSelectionChanged = { ChoiceStatus = it })
        Divider(color = Color.Gray, thickness = 1.dp)
        if (ChoiceStatus == 1 && filteredStore.isEmpty() || ChoiceStatus == 2 && filteredAuction.isEmpty() || ChoiceStatus == 3 && filteredBarter.isEmpty()) {
            showNothingActionImage()
        } else {
            if (ChoiceStatus == 1) {

                filteredStore.forEach { item ->
                    ShowMyPurchase(
                        image = item.gifticonDataImageName,
                        name = item.gifticonName,
                        gifticonTime = item.gifticonEndDate,
                        storeTime = item.storeEndDate,
                        isDeposit = item.deposit,
                        Price = item.storePrice,
                        Status = item.storeStatus
                    )
                }
            } else if (ChoiceStatus == 2) {

                filteredAuction.forEach { item ->
                    ShowMyPurchase(
                        image = item.auctionItemData.gifticonDataImageName,
                        name = item.auctionItemData.gifticonName,
                        gifticonTime = item.auctionItemData.gifticonEndDate,
                        storeTime = item.auctionRegistedDate,
                        isDeposit = item.auctionItemData.deposit,
                        Price = item.auctionItemData.upperPrice,
                        Status = item.auctionBidStatus
                    )
                }
            } else if (ChoiceStatus == 3) {

                filteredBarter.forEach { item ->
                    ShowMyPurchase(
                        image = item.myBarterResponseDto.barterHostItems[0].gifticonDataImageUrl,
                        name = item.myBarterResponseDto.barterHostItems[0].gifticonName,
                        gifticonTime = item.myBarterResponseDto.barterHostItems[0].gifticonEndDate,
                        storeTime = item.myBarterResponseDto.barterEndDate,
                        isDeposit = false,
                        Price = 0,
                        Status = item.barterStatus
                    )
                }
            }
        }
    }
}


// 클릭 시 수행할 함수d
@Composable
fun SelectPurchaseBar(onSelectionChanged: (Int) -> Unit) {
    var selectedOption by remember { mutableStateOf(1) }  // 상태 변수로 현재 선택된 항목을 저장

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val commontextsize = 18

        PurchaseOption(text = "구매 내역", id = 1, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
        Spacer(modifier = Modifier.height(16.dp))
        PurchaseOption(text = "입찰 내역", id = 2, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
        Spacer(modifier = Modifier.height(16.dp))
        PurchaseOption(text = "물물교환 내역", id = 3, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
    }
}

@Composable
fun PurchaseOption(
    text: String,
    id: Int,
    selectedOption: Int,
    onSelectionChanged: (Int) -> Unit,
    onOptionClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier.clickable(onClick = {
            onSelectionChanged(id)
            onOptionClicked(id)  // 여기에 selectedOption 값을 변경하는 로직을 상위 Composable에게 위임
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
fun ShowMyPurchase(
    image: String,
    name: String,
    gifticonTime: String,
    storeTime: String,
    isDeposit: Boolean,
    Price: Int,
    Status: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .padding(2.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(4.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .padding(8.dp)
        ) {
            // 65% 이미지
            Box(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth()
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
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
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
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
                            "구매가: ${formattedNumber(Price.toString())} 원",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            // 5% 경매기간
            FormattedDateText(
                gifticonTime = storeTime,
                prefix = "마감일",
                modifier = Modifier
                    .weight(0.08f)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )
        }

        // "낙찰" 상태일 경우 중앙에 표시될 이미지
        if (Status == "낙찰") {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.purchase), // 'purchase.jpg' 를 리소스로 로드
                    contentDescription = "구매 완료", modifier = Modifier.align(Alignment.Center)
                )
            }
        } else if (Status == "교환 완료") {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.barterend), // 'purchase.jpg' 를 리소스로 로드
                    contentDescription = "구매 완료", modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}