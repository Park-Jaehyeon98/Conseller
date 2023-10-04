package com.example.project

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import formatPhoneNumber
import java.io.InputStream

@Composable
fun MyPage(navController: NavHostController) {
    val viewModel: MyPageViewModel = hiltViewModel()
    val checkIdResult by viewModel.getMyinfoResponse.collectAsState()
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


    Box(modifier = Modifier.fillMaxSize().background(Color.White), contentAlignment = Alignment.TopCenter) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.verticalScroll(scrollState),
        ) {
            UserProfile(
//                profileImage = checkIdResult.userProfileUrl,
//                userNickName = checkIdResult.userNickname,
//                userEmail = checkIdResult.userEmail,
//                userPhoneNumber = checkIdResult.userPhoneNumber
            )

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    onClick = {
                        navController.navigate("MyModifyPageValidPage")
                    },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(BrandColor1)
                ) {
                    Text("회원정보 변경")
                }
                Button(
                    onClick = {
                        navController.navigate("MyGifticonAdd")
                    },
                    modifier = Modifier.padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(BrandColor1)
                ) {
                    Text("기프티콘 등록")
                }

            }
            Spacer(modifier = Modifier.height(14.dp))
            MypageCheck(onClick1 = { navController.navigate("MypageCoupon") },
                onClick2 = { navController.navigate("MypageAuction") },
                onClick3 = { navController.navigate("MypageStore") },
                onClick4 = { navController.navigate("MypageBarter") },
                onClick5 = { navController.navigate("MySalesPage") },
                onClick6 = { navController.navigate("MyPurchasePage") })

            Spacer(modifier = Modifier.height(14.dp))
            Row(
                modifier = Modifier.padding(end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween, // 버튼들 사이에 간격을 주기 위함
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.navigate("MyDeletePageValidPage")
                    },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(BrandColor1)
                ) {
                    Text("회원 탈퇴")
                }

                Button(
                    onClick = {
                        navController.navigate("Inquiry/1")
                    },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(BrandColor1)
                ) {
                    Text("1:1 문의")
                }
            }

        }
    }


}

@Composable
fun UserProfile(
//    profileImage: String? = null, userNickName: String, userEmail: String, userPhoneNumber: String
) {
    val viewModel: MyPageViewModel = hiltViewModel()
    val checkIdResult by viewModel.getMyinfoResponse.collectAsState()

    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (checkIdResult.userProfileUrl != null) {
            Image(
                painter = rememberAsyncImagePainter(checkIdResult.userProfileUrl),
                contentDescription = "유저 프로필 이미지",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillHeight
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.defaultimage),
                contentDescription = "Default User Image",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillHeight
            )
        }
        // 배경색을 하얀색으로 설정
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally  // 각 항목 시작 부분 정렬
        ) {
            // 닉네임 항목
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(text = "${checkIdResult.userNickname}님 반갑습니다.", fontSize = 20.sp)
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
    onClick6: () -> Unit
) {
    val viewModel: MyPageViewModel = hiltViewModel()
    val myGift by viewModel.getMyGifticonResponse.collectAsState()
    val myAuction by viewModel.getMyAuctionResponse.collectAsState()
    val myBarter by viewModel.getMyBarterResponse.collectAsState()
    val myStore by viewModel.getMyStoreResponse.collectAsState()
    val myAcutionBid by viewModel.getMyAuctionBidResponse.collectAsState()
    val myPurchase by viewModel.getMyPurchaseResponse.collectAsState()
    val myBarterRequest by viewModel.getMyBarterRequestResponse.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 첫 번째 Box
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            CustomCard(
                label = "내 쿠폰",
                imageResId = R.drawable.coupon1,
                number = myGift.size,
                modifier = Modifier.fillMaxSize(),
                onClick = onClick1
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        // 두 번째 Box
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            val notAuctionedCount = myAuction.filter { it.auctionStatus != "낙찰" }.size
            CustomCard(
                label = "경매 현황",
                imageResId = R.drawable.auction,
                number = notAuctionedCount,
                modifier = Modifier.fillMaxSize(),
                onClick = onClick2
            )
        }
    }

    // 여백을 위한 Spacer
    Spacer(modifier = Modifier.height(20.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 세 번째 Box
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            val notStoredCount = myStore.filter { it.storeStatus != "낙찰" }.size
            CustomCard(
                label = "판매 현황",
                imageResId = R.drawable.store,
                number = notStoredCount,
                modifier = Modifier.fillMaxSize(),
                onClick = onClick3
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        // 네 번째 Box
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            val notBarterCount = myBarter.filter { it.barterStatus != "교환 완료" }.size
            CustomCard(
                label = "물물교환 현황",
                imageResId = R.drawable.barter,
                number = notBarterCount,
                modifier = Modifier.fillMaxSize(),
                onClick = onClick4
            )
        }
    }
    // 여백을 위한 Spacer
    Spacer(modifier = Modifier.height(20.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 세 번째 Box
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            val AuctionedCount = myAuction.filter { it.auctionStatus == "낙찰" }.size
            val StoredCount = myStore.filter { it.storeStatus == "낙찰" }.size
            val BarterCount = myBarter.filter { it.barterStatus == "교환 완료" }.size
            val AllCount = AuctionedCount + StoredCount + BarterCount
            CustomCard(
                label = "경매/판매 내역",
                imageResId = R.drawable.coupon1,
                number = AllCount,
                modifier = Modifier.fillMaxSize(),
                onClick = onClick5
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        // 네 번째 Box
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            val AuctionedBidCount = myAcutionBid.filter { it.auctionBidStatus == "낙찰" }.size
            val PurchaseCount = myPurchase.filter { it.storeStatus == "낙찰" }.size
            val BarterRequestCount = myBarterRequest.filter { it.barterRequestStatus == "수락" }.size
            val PurcahseAllCount = AuctionedBidCount + PurchaseCount + BarterRequestCount
            CustomCard(
                label = "구매/입찰 내역",
                imageResId = R.drawable.coupon,
                number = PurcahseAllCount,
                modifier = Modifier.fillMaxSize(),
                onClick = onClick6
            )
        }
    }
}


@Composable
fun CustomCard(
    label: String, imageResId: Int, number: Int, modifier: Modifier = Modifier, onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
    ) {
        Surface(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth(1f)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = label, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(text = number.toString(), fontWeight = FontWeight.Bold, fontSize = 32.sp)
                }
            }
        }
    }
}

