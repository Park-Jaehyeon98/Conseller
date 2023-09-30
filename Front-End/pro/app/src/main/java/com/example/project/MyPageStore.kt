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
import com.example.project.ShowMyGifticon
import com.example.project.StoreItem
import com.example.project.viewmodels.MyPageViewModel

@Composable
fun MypageStore(navController: NavHostController) {
    val viewModel: MyPageViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getMyStore()
        viewModel.getMyPurchase()
    }
    var ChoiceStatus by remember { mutableStateOf(0) }
    val getMyStore by viewModel.getMyStoreResponse.collectAsState()
    val scrollstate= rememberScrollState()

    val filteredAuction = when (ChoiceStatus) {
        1 -> getMyStore.filter { it.storeStatus== "진행 중" }
        2 -> getMyStore.filter { it.storeStatus == "거래 중" }
        else -> getMyStore
    }

    Column(
        modifier= Modifier.verticalScroll(scrollstate),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ){
        SelectStore(onSelectionChanged = { ChoiceStatus = it})
        Divider(color = Color.Gray, thickness = 1.dp)
        getMyStore?.forEach { item ->
            ShowMyStore(
                image = item.gifticonDataImageName,
                name = item.gifticonName,
                gifticonTime = item.gifticonEndDate,
                storeTime = item.storeEndDate,
                isDeposit = item.isDeposit,
                storePrice = item.storePrice,
                onItemClick = {
                    navController.navigate("StoreDetailPage/${item.storeIdx}")
                }

            )
        }
    }
}
@Composable
fun SelectStore(onSelectionChanged: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val commontextsize = 18
        Row(modifier = Modifier.clickable(onClick = { onSelectionChanged(1) })) {
            Text(
                text = "내 판매",
                fontSize = commontextsize.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.clickable(onClick = { onSelectionChanged(2) })) {
            Text(
                text = "입금 확인",
                fontSize = commontextsize.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.clickable(onClick = { onSelectionChanged(3) })) {
            Text(
                text = "구매 신청",
                fontSize = commontextsize.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.clickable(onClick = { onSelectionChanged(4) })) {
            Text(
                text = "구매 확정",
                fontSize = commontextsize.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .padding(2.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(4.dp))
            .clickable { onItemClick() }
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
            Column (
                modifier = Modifier.weight(1f)
            ){
                Text(name, fontWeight = FontWeight.Bold, fontSize = 20.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                FormattedDateText(gifticonTime,"유효기간")
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
                    modifier = Modifier.weight(0.6f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text("구매가: ${formattedNumber(storePrice.toString())} 원", fontWeight = FontWeight.Bold, fontSize = 16.sp)
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


