import android.util.Log
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.project.SelectBar
import com.example.project.viewmodels.MyPageViewModel

@Composable
fun MypageAuction(navController: NavHostController) {
    val viewModel: MyPageViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getMyAuction()
    }
    val getMyAuction by viewModel.getMyAuctionResponse.collectAsState()
    val scrollstate= rememberScrollState()
    Column(
        modifier= Modifier.verticalScroll(scrollstate),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ){
        SelectAuction()
        Divider(color = Color.Gray, thickness = 1.dp)
        getMyAuction?.forEach { item ->
            ShowMyAuction(
                image = item.gifticonDataImageName,
                name = item.gifticonName,
                gifticonTime = item.gifticonEndDate,
                auctionTime = item.auctionEndDate,
                isDeposit = item.deposit,
                upperprice = item.upperPrice,
                nowprice = item.auctionHighestBid,
                onItemClick = {
                    navController.navigate("AuctionDetailPage/${item.auctionIdx}")
                    Log.d("AuctionPage", "auctionIdx: ${item.auctionIdx}")

                }

            )
        }
    }
}

@Composable
fun SelectAuction(){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val commontextsize=18
        Row(modifier=Modifier.clickable(onClick = {})){
            Text(
                text = "내 경매",
                fontSize = commontextsize.sp, fontWeight = FontWeight.Bold, color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier=Modifier.clickable(onClick = {})){
            Text(
                text = "내 입찰",
                fontSize = commontextsize.sp, fontWeight = FontWeight.Bold, color = Color.Gray
            )
        }
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color.White)
            .padding(8.dp)
            .clickable { onItemClick() }
    ) {
        // 65% 이미지
        Box(
            modifier = Modifier
                .weight(0.65f)
                .fillMaxWidth()
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            // 이미지
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        // 10% 이름 및 유효기간
        Row(
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            FormattedDateText(gifticonTime,"유효기간")
        }

        // 구분 줄
        Spacer(modifier = Modifier.height(4.dp))
        Divider(color = Color.Gray, modifier = Modifier.padding(horizontal = 12.dp))
        Spacer(modifier = Modifier.height(4.dp))

        // 15% 박스1
        Box(
            modifier = Modifier
                .weight(0.2f)
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
                    // 30% 즉시구매가
                    Text("즉시구매가 : $upperprice 원", modifier = Modifier.weight(0.4f), fontWeight = FontWeight.Bold, fontSize = 16.sp)

                    // 70% 현재입찰가
                    Text("현재입찰가 : $nowprice 원", modifier = Modifier.weight(0.6f), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }

        // 5% 경매기간
        FormattedDateText(
            gifticonTime = auctionTime,
            prefix = "마감일",
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )
    }
}