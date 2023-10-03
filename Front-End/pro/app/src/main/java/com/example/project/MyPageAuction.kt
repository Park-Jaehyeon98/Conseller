import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.project.SelectBar
import com.example.project.viewmodels.MyPageViewModel

@Composable
fun MypageAuction(navController: NavHostController) {
    val viewModel: MyPageViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getMyAuction()
        viewModel.getMyAuctionBid()
    }
    val getMyAuction by viewModel.getMyAuctionResponse.collectAsState()

    val getMyAuctionBid by viewModel.getMyAuctionBidResponse.collectAsState()

    val scrollstate = rememberScrollState()
    var ChoiceStatus by remember { mutableStateOf(1) }

    val filteredAuction = when (ChoiceStatus) {
        1 -> getMyAuction.filter { it.auctionStatus == "진행 중" }
        2 -> getMyAuction.filter { it.auctionStatus == "거래 중" }
        else -> getMyAuction
    }
    val filteredAuctionBid = when (ChoiceStatus) {
        3 -> getMyAuctionBid.filter { it.auctionBidStatus == "입찰" }
        4 -> getMyAuctionBid.filter { it.auctionBidStatus == "낙찰 예정" }
        else -> getMyAuctionBid
    }

    Column(
        modifier = Modifier.verticalScroll(scrollstate),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        SelectAuction(onSelectionChanged = { ChoiceStatus = it })
        Divider(color = Color.Gray, thickness = 1.dp)
        if (ChoiceStatus == 1 && filteredAuction.isEmpty() || ChoiceStatus == 2 && filteredAuction.isEmpty() || ChoiceStatus == 3 && filteredAuctionBid.isEmpty() || ChoiceStatus == 4 && filteredAuctionBid.isEmpty()) {
            showNothingActionImage()
        } else {
            if (ChoiceStatus <= 2) {
                filteredAuction.forEach { item ->
                    ShowMyAuction(image = item.gifticonDataImageName,
                        name = item.gifticonName,
                        gifticonTime = item.gifticonEndDate,
                        auctionTime = item.auctionEndDate,
                        isDeposit = item.deposit,
                        upperprice = item.upperPrice,
                        nowprice = item.auctionHighestBid,
                        onItemClick = {
                            if (item.auctionStatus == "진행 중") {
                                navController.navigate("AuctionDetailPage/${item.auctionIdx}")
                            } else if (item.auctionStatus == "거래 중") {
                                Log.d(
                                    "AuctionClick",
                                    "Item clicked with auctionIdx: ${item.auctionIdx}"
                                )
                                navController.navigate("AuctionConfirmPage/${item.auctionIdx}")
                            }
                        }

                    )
                }
            } else {
                filteredAuctionBid.forEach { item ->
                    ShowMyAuctionBid(image = item.auctionItemData.gifticonDataImageName,
                        name = item.auctionItemData.gifticonName,
                        gifticonTime = item.auctionItemData.gifticonEndDate,
                        auctionTime = item.auctionItemData.auctionEndDate,
                        isDeposit = item.auctionItemData.deposit,
                        upperprice = item.auctionItemData.auctionHighestBid,
                        myprice = item.auctionBidPrice,
                        onItemClick = {
                            if (item.auctionBidStatus == "입찰") {
                                navController.navigate("AuctionDetailPage/${item.auctionItemData.auctionIdx}")
                            } else if (item.auctionBidStatus == "낙찰 예정") {
                                navController.navigate("AuctionConfirmBuyPage/${item.auctionItemData.auctionIdx}") {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }


                        },
                        status = item.auctionBidStatus

                    )
                }
            }
        }
    }
}

@Composable
fun SelectAuction(onSelectionChanged: (Int) -> Unit) {
    var selectedOption by remember { mutableStateOf(0) }  // 상태 변수로 현재 선택된 항목을 저장

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val commontextsize = 18

        AuctionOption(text = "내 경매", id = 1, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
        Spacer(modifier = Modifier.height(16.dp))
        AuctionOption(text = "입금 확인", id = 2, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
        Spacer(modifier = Modifier.height(16.dp))
        AuctionOption(text = "내 입찰", id = 3, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
        Spacer(modifier = Modifier.height(16.dp))
        AuctionOption(text = "내 입찰(확정)", id = 4, selectedOption, onSelectionChanged) {
            selectedOption = it
        }
    }
}

@Composable
fun AuctionOption(
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
fun ShowMyAuction(
    image: String,
    name: String,
    gifticonTime: String,
    auctionTime: String,
    isDeposit: Boolean,
    upperprice: Int,
    nowprice: Int,
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
                .weight(0.18f)
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
                    // 30% 즉시구매가
                    Text(
                        "즉시구매가 : ${formattedNumber(upperprice.toString())} 원",
                        modifier = Modifier.weight(0.4f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    // 70% 현재입찰가
                    Text(
                        "현재입찰가 : ${formattedNumber(nowprice.toString())} 원",
                        modifier = Modifier.weight(0.6f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }

        // 5% 경매기간
        FormattedDateText(
            gifticonTime = auctionTime,
            prefix = "마감일",
            modifier = Modifier
                .weight(0.08f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )
    }
}

@Composable
fun ShowMyAuctionBid(
    image: String,
    name: String,
    gifticonTime: String,
    auctionTime: String,
    isDeposit: Boolean,
    upperprice: Int,
    myprice: Int,
    status: String,
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

            Spacer(modifier = Modifier.height(4.dp))
            Divider(color = Color.Gray, modifier = Modifier.padding(horizontal = 12.dp))
            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .weight(0.18f)
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (isDeposit) "보증금 있음" else "보증금 없음",
                        modifier = Modifier.weight(0.4f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Column(
                        modifier = Modifier.weight(0.6f), horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            "현재 최고가: ${formattedNumber(upperprice.toString())} 원",
                            modifier = Modifier.weight(0.4f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            "내 입찰가 : ${formattedNumber(myprice.toString())} 원",
                            modifier = Modifier.weight(0.6f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            FormattedDateText(
                gifticonTime = auctionTime,
                prefix = "마감일",
                modifier = Modifier
                    .weight(0.08f)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )
        }

        if (status == "낙찰") {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.purchase),
                    contentDescription = "입찰 성공",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}


@Composable
fun showNothingActionImage() {
    Image(
        painter = painterResource(id = R.drawable.nothingaction),
        contentDescription = "No available gifticon",
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        contentScale = ContentScale.Crop,
    )
}
