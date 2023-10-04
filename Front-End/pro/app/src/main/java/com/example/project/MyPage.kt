package com.example.project

import android.content.Context
import android.net.Uri
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.ui.theme.BrandColor1
import com.example.project.viewmodels.MyPageViewModel
import java.io.InputStream

@Composable
fun MyPage(navController: NavHostController) {
    val viewModel: MyPageViewModel = hiltViewModel()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.getMyInfo()
        viewModel.getMyGifticon()
        viewModel.getMyBarter()
        viewModel.getMyStore()
        viewModel.getMyAuction()
        viewModel.getMyBarterRequest()
        viewModel.getMyPurchase()
        viewModel.getMyAuctionBid()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .verticalScroll(scrollState)
                .background(Color.White),
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            UserProfile()
            Spacer(modifier = Modifier.height(14.dp))
            MypageCheck(onClick1 = { navController.navigate("MypageCoupon") },
                onClick2 = { navController.navigate("MypageAuction") },
                onClick3 = { navController.navigate("MypageStore") },
                onClick4 = { navController.navigate("MypageBarter") },
                onClick5 = { navController.navigate("MySalesPage") },
                onClick6 = { navController.navigate("MyPurchasePage") },
                onClick7 = { navController.navigate("MypageSellCoupon") },
                onClick8 = { navController.navigate("MyGifticonAdd") },
                onClick9 = { navController.navigate("MyModifyPageValidPage") },
                onClick10 = {         navController.navigate("Inquiry/1") },
                onClick11 = { navController.navigate("MyDeletePageValidPage") },
                )

            Spacer(modifier = Modifier.height(14.dp))
        }
    }


}

@Composable
fun UserProfile() {
    val viewModel: MyPageViewModel = hiltViewModel()
    val checkIdResult by viewModel.getMyinfoResponse.collectAsState()
    val myAuction by viewModel.getMyAuctionResponse.collectAsState()
    val myBarter by viewModel.getMyBarterResponse.collectAsState()
    val myStore by viewModel.getMyStoreResponse.collectAsState()
    val myAcutionBid by viewModel.getMyAuctionBidResponse.collectAsState()
    val myPurchase by viewModel.getMyPurchaseResponse.collectAsState()
    val myBarterRequest by viewModel.getMyBarterRequestResponse.collectAsState()
    val AuctionedCount = myAuction.filter { it.auctionStatus == "낙찰" }.size
    val StoredCount = myStore.filter { it.storeStatus == "낙찰" }.size
    val BarterCount = myBarter.filter { it.barterStatus == "교환 완료" }.size
    val AllCount = AuctionedCount + StoredCount + BarterCount
    val AuctionedBidCount = myAcutionBid.filter { it.auctionBidStatus == "낙찰" }.size
    val PurchaseCount = myPurchase.filter { it.storeStatus == "낙찰" }.size
    val BarterRequestCount = myBarterRequest.filter { it.barterRequestStatus == "수락" }.size
    val PurcahseAllCount = AuctionedBidCount + PurchaseCount + BarterRequestCount

    Surface(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 12.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // 이를 사용하여 내용을 중앙에 배치
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    if (checkIdResult.userProfileUrl != null) {
                        Image(
                            painter = rememberAsyncImagePainter(checkIdResult.userProfileUrl),
                            contentDescription = "유저 프로필 이미지",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.FillHeight
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.defaultimage),
                            contentDescription = "Default User Image",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.FillHeight
                        )
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "${checkIdResult.userNickname}님", fontSize = 20.sp)
                        Text(
                            text = "현재 구매내역: ${AllCount} 건",
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "현재 판매내역: ${PurcahseAllCount} 건",
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}

fun getInputStreamFromUri(context: Context, uri: Uri): InputStream? {
    return context.contentResolver.openInputStream(uri)
}

// InputStream에서 ByteArray로 변환
fun getBytesFromInputStream(inputStream: InputStream): ByteArray {
    return inputStream.readBytes()
}


@Composable
fun MypageCheck(
    onClick1: () -> Unit,
    onClick2: () -> Unit,
    onClick3: () -> Unit,
    onClick4: () -> Unit,
    onClick5: () -> Unit,
    onClick6: () -> Unit,
    onClick7: () -> Unit,
    onClick8: () -> Unit,
    onClick9: () -> Unit,
    onClick10: () -> Unit,
    onClick11: () -> Unit
) {
    val viewModel: MyPageViewModel = hiltViewModel()
    val myGift by viewModel.getMyGifticonResponse.collectAsState()
    val myAuction by viewModel.getMyAuctionResponse.collectAsState()
    val myBarter by viewModel.getMyBarterResponse.collectAsState()
    val myStore by viewModel.getMyStoreResponse.collectAsState()
    val myAcutionBid by viewModel.getMyAuctionBidResponse.collectAsState()
    val myPurchase by viewModel.getMyPurchaseResponse.collectAsState()
    val myBarterRequest by viewModel.getMyBarterRequestResponse.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Conseller 기프티콘 관리",
                fontSize = 22.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 15.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                CustomCard(
                    label = "기프티콘 등록",
                    imageResId = R.drawable.auction,
                    modifier = Modifier.fillMaxSize(),
                    onClick = onClick8
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalArrangement = Arrangement.Center  // 항목들을 수평 방향으로 중앙에 배치
            ) {
                // 첫 번째 Box
                Box(
                    modifier = Modifier
                        .weight(0.8f)
                        .background(Color.White, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    CustomCard(
                        label = "내 쿠폰",
                        imageResId = R.drawable.coupon1,
                        modifier = Modifier.fillMaxSize(),
                        onClick = onClick1
                    )
                }
                // 두 번째 Box
                Box(
                    modifier = Modifier
                        .weight(0.8f)
                        .background(Color.White, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    val notAuctionedCount = myAuction.filter { it.auctionStatus != "낙찰" }.size
                    CustomCard(
                        label = "거래 중 쿠폰",
                        imageResId = R.drawable.coupon2,
                        modifier = Modifier.fillMaxSize(),
                        onClick = onClick7
                    )
                }

            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Conseller 거래 관리",
                fontSize = 22.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 15.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            Spacer(modifier = Modifier.height(10.dp))

            // 각 Box 사이의 간격 조정
            val boxModifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(5.dp)

            Box(modifier = boxModifier, contentAlignment = Alignment.Center) {
                val notAuctionedCount = myAuction.filter { it.auctionStatus != "낙찰" }.size
                CustomCard(
                    label = "경매 관리", imageResId = R.drawable.auction,

                    modifier = Modifier.fillMaxSize(), onClick = onClick2
                )
            }

            Box(modifier = boxModifier, contentAlignment = Alignment.Center) {
                val notStoredCount = myStore.filter { it.storeStatus != "낙찰" }.size
                CustomCard(
                    label = "판매 관리", imageResId = R.drawable.store,

                    modifier = Modifier.fillMaxSize(), onClick = onClick3
                )
            }

            Box(modifier = boxModifier, contentAlignment = Alignment.Center) {
                val notBarterCount = myBarter.filter { it.barterStatus != "교환 완료" }.size
                CustomCard(
                    label = "물물교환 관리", imageResId = R.drawable.barter,

                    modifier = Modifier.fillMaxSize(), onClick = onClick4
                )
            }

            Box(modifier = boxModifier, contentAlignment = Alignment.Center) {
                val AuctionedCount = myAuction.filter { it.auctionStatus == "낙찰" }.size
                val StoredCount = myStore.filter { it.storeStatus == "낙찰" }.size
                val BarterCount = myBarter.filter { it.barterStatus == "교환 완료" }.size
                val AllCount = AuctionedCount + StoredCount + BarterCount
                CustomCard(
                    label = "경매/판매 내역", imageResId = R.drawable.coupon1,

                    modifier = Modifier.fillMaxSize(), onClick = onClick5
                )
            }

            Box(modifier = boxModifier, contentAlignment = Alignment.Center) {
                val AuctionedBidCount = myAcutionBid.filter { it.auctionBidStatus == "낙찰" }.size
                val PurchaseCount = myPurchase.filter { it.storeStatus == "낙찰" }.size
                val BarterRequestCount =
                    myBarterRequest.filter { it.barterRequestStatus == "수락" }.size
                val PurcahseAllCount = AuctionedBidCount + PurchaseCount + BarterRequestCount
                CustomCard(
                    label = "구매/입찰 내역", imageResId = R.drawable.coupon,

                    modifier = Modifier.fillMaxSize(), onClick = onClick6
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "일반",
                fontSize = 22.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 15.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            Spacer(modifier = Modifier.height(10.dp))

            // 각 Box 사이의 간격 조정
            val boxModifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(5.dp)

            Box(modifier = boxModifier, contentAlignment = Alignment.Center) {
                val notAuctionedCount = myAuction.filter { it.auctionStatus != "낙찰" }.size
                CustomCard(
                    label = "회원정보 수정", imageResId = R.drawable.auction,

                    modifier = Modifier.fillMaxSize(), onClick = onClick9
                )
            }

            Box(modifier = boxModifier, contentAlignment = Alignment.Center) {
                val notStoredCount = myStore.filter { it.storeStatus != "낙찰" }.size
                CustomCard(
                    label = "고객센터 문의", imageResId = R.drawable.store,

                    modifier = Modifier.fillMaxSize(), onClick = onClick10
                )
            }

            Box(modifier = boxModifier, contentAlignment = Alignment.Center) {
                val notBarterCount = myBarter.filter { it.barterStatus != "교환 완료" }.size
                CustomCard(
                    label = "회원탈퇴", imageResId = R.drawable.barter,

                    modifier = Modifier.fillMaxSize(), onClick = onClick11
                )
            }


        }
        Spacer(modifier = Modifier.height(14.dp))
    }
}


@Composable
fun CustomCard(
    label: String, imageResId: Int, modifier: Modifier = Modifier, onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .background(Color.White)
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            Column(
                modifier = Modifier.background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(text = label, fontSize = 22.sp, color = Color.DarkGray)
//                    Text(text = number.toString(), fontWeight = FontWeight.Bold, fontSize = 32.sp)
                }
            }
        }
    }
}

