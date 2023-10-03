import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.project.R
import com.example.project.viewmodels.MyPageViewModel

@Composable
fun MypageStore(navController: NavHostController) {
    val viewModel: MyPageViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getMyStore()
        viewModel.getMyPurchase()
    }
    var ChoiceStatus by remember { mutableStateOf(1) }
    val getMyStore by viewModel.getMyStoreResponse.collectAsState()
    val getMyPurchase by viewModel.getMyPurchaseResponse.collectAsState()
    val scrollstate = rememberScrollState()

    val filteredStore = when (ChoiceStatus) {
        1 -> getMyStore.filter { it.storeStatus == "진행 중" }
        2 -> getMyStore.filter { it.storeStatus == "거래 중" }
        else -> getMyStore
    }
    val filteredPurchase = when (ChoiceStatus) {
        3 -> getMyPurchase.filter { it.storeStatus == "거래 중" }
        4 -> getMyPurchase.filter { it.storeStatus == "낙찰" }
        else -> getMyPurchase
    }

    Column(
        modifier = Modifier.verticalScroll(scrollstate),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        SelectStore(onSelectionChanged = { ChoiceStatus = it })
        Divider(color = Color.Gray, thickness = 1.dp)
        if (filteredStore.isEmpty() || filteredPurchase.isEmpty()) {
            showNothingActionImage()
        } else {
            if (ChoiceStatus <= 2) {
                filteredStore.forEach { item ->
                    ShowMyStore(image = item.gifticonDataImageName,
                        name = item.gifticonName,
                        gifticonTime = item.gifticonEndDate,
                        storeTime = item.storeEndDate,
                        isDeposit = item.deposit,
                        storePrice = item.storePrice,
                        onItemClick = {
                            if (item.storeStatus == "진행 중") {
                                navController.navigate("StoreDetailPage/${item.storeIdx}")
                            } else if (item.storeStatus == "거래 중") {
                                navController.navigate("storeConfirmPage/${item.storeIdx}") {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        }

                    )
                }
            } else {
                filteredPurchase.forEach { item ->
                    ShowMyPurchase(
                        image = item.gifticonDataImageName,
                        name = item.gifticonName,
                        gifticonTime = item.gifticonEndDate,
                        storeTime = item.storeEndDate,
                        isDeposit = item.deposit,
                        storePrice = item.storePrice,
                        onItemClick = {
                            if (item.storeStatus == "낙찰") {
            //                        navController.navigate("StoreDetailPage/${item.storeIdx}")
                            } else if (item.storeStatus == "거래 중") {
                                navController.navigate("StoreDetailPage/${item.storeIdx}")
                            }
                        },
                        storeStatus = item.storeStatus

                    )
                }
            }
        }
    }
}

@Composable
fun SelectStore(onSelectionChanged: (Int) -> Unit) {
    var selectedOption by remember { mutableStateOf(0) }  // 상태 변수로 현재 선택된 항목을 저장

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val commontextsize = 18

        StoreOption(text = "내 판매", id = 1, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
        Spacer(modifier = Modifier.height(16.dp))
        StoreOption(text = "입금 확인", id = 2, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
        Spacer(modifier = Modifier.height(16.dp))
        StoreOption(text = "구매 신청", id = 3, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
        Spacer(modifier = Modifier.height(16.dp))
        StoreOption(text = "구매 확정", id = 4, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
    }
}

@Composable
fun StoreOption(
    text: String,
    id: Int,
    selectedOption: Int,
    onSelectionChanged: (Int) -> Unit,
    onOptionClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier.clickable(onClick = {
            onSelectionChanged(id)
            onOptionClicked(id)
        })
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (selectedOption == id) Color.Red else Color.Gray
        )
    }
}


@Composable
fun ShowMyStore(
    image: String,
    name: String,
    gifticonTime: String,
    storeTime: String,
    isDeposit: Boolean,
    storePrice: Int,
    onItemClick: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(360.dp)
        .padding(2.dp)
        .background(Color.White, shape = RoundedCornerShape(8.dp))
        .shadow(elevation = 6.dp, shape = RoundedCornerShape(4.dp))
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
                        "구매가: ${formattedNumber(storePrice.toString())} 원",
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
}

@Composable
fun ShowMyPurchase(
    image: String,
    name: String,
    gifticonTime: String,
    storeTime: String,
    isDeposit: Boolean,
    storePrice: Int,
    storeStatus: String,
    onItemClick: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(360.dp)
        .padding(2.dp)
        .background(Color.White, shape = RoundedCornerShape(8.dp))
        .shadow(elevation = 6.dp, shape = RoundedCornerShape(4.dp))
        .clickable { onItemClick() }) {
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
                            "구매가: ${formattedNumber(storePrice.toString())} 원",
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
        if (storeStatus == "낙찰") {
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
        }
    }
}
