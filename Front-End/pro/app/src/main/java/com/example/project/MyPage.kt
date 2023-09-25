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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.InputStream

@Composable
fun MyPage(navController: NavHostController) {
    val viewModel: MyPageViewModel = hiltViewModel()
    val checkIdResult by viewModel.getMyinfoResponse.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.getMyInfo()
        viewModel.getMyAuction()
        viewModel.getMyBarterRequest()
        viewModel.getMyStore()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.verticalScroll(scrollState),
        ) {
            UserProfile(
                profileImage = checkIdResult.userProfileUrl,
                userNickName = checkIdResult.userNickname,
                userEmail = checkIdResult.userEmail,
                userPhoneNumber = checkIdResult.userPhoneNumber
            )

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    onClick = {
                        navController.navigate("MyPageValid")
                    },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(BrandColor1)
                ) {
                    Text("개인정보 변경")
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
            MypageCheck(
                onClick1 = { navController.navigate("MypageCoupon") },
                onClick2 = { navController.navigate("MypageAuction") },
                onClick3 = { navController.navigate("MypageStore") },
                onClick4 = { navController.navigate("MypageBarter") })

        }
    }


}

@Composable
fun UserProfile(
    profileImage: Uri? = null, userNickName: String, userEmail: String, userPhoneNumber: String
) {
    val viewModel: MyPageViewModel = hiltViewModel()


    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (profileImage != null) {
            Image(
                painter = rememberAsyncImagePainter(profileImage),
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
        Text(text = userNickName, fontSize = 22.sp)
        Text(text = userEmail)
        Text(text = userPhoneNumber)
    }
}

fun getInputStreamFromUri(context: Context, uri: Uri): InputStream? {
    return context.contentResolver.openInputStream(uri)
}

// InputStream에서 ByteArray로 변환
fun getBytesFromInputStream(inputStream: InputStream): ByteArray {
    return inputStream.readBytes()
}

// ByteArray를 MultipartBody.Part로 변환
fun getMultipartFromByteArray(byteArray: ByteArray, fileName: String): MultipartBody.Part {
    val requestBody = byteArray.toRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("image", fileName, requestBody)
}

@Composable
fun MypageCheck(
    onClick1: () -> Unit,
    onClick2: () -> Unit,
    onClick3: () -> Unit,
    onClick4: () -> Unit
) {
    val viewModel: MyPageViewModel = hiltViewModel()
    val myGift by viewModel.getMyGifticonResponse.collectAsState()
    val myAuction by viewModel.getMyAuctionResponse.collectAsState()
    val myBarter by viewModel.getMyBarterResponse.collectAsState()
    val myStore by viewModel.getMyStoreResponse.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                CustomCard(
                    label = "내 쿠폰",
                    imageResId = R.drawable.coupon1,
                    number = myGift.size,
                    modifier = Modifier.weight(1f),
                    onClick = onClick1
                )
                Spacer(modifier = Modifier.width(8.dp))
                CustomCard(
                    label = "경매 관리",
                    imageResId = R.drawable.coupon,
                    number = myAuction.size,
                    modifier = Modifier.weight(1f),
                    onClick = onClick2
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                CustomCard(
                    label = "판매 관리",
                    imageResId = R.drawable.coupon1,
                    number = myStore.size,
                    modifier = Modifier.weight(1f),
                    onClick = onClick3
                )
                Spacer(modifier = Modifier.width(8.dp))
                CustomCard(
                    label = "물물교환 관리",
                    imageResId = R.drawable.coupon,
                    number = myBarter.size,
                    modifier = Modifier.weight(1f),
                    onClick = onClick4
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CustomCard(
    label: String,
    imageResId: Int,
    number: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
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

@Composable
fun GiftCard(
    giftIcon: String? = null,
    giftName: String,
    giftDuration: String,
    transactionStatus: String, // 거래 상태 추가
    currentBid: String, // 현재 입찰가 추가
    onDetailsClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
        border = BorderStroke(1.dp, Color.Black),
    ) {
        Box(Modifier.fillMaxWidth()) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 기프티콘 사진
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    if (giftIcon != null) {
                        Image(
                            painter = rememberAsyncImagePainter(giftIcon),
                            contentDescription = "기프티콘 사진",
                            modifier = Modifier.size(200.dp, 200.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.giftdefault),
                            contentDescription = "Default Image",
                            modifier = Modifier.size(100.dp, 100.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(text = "상세보기 >", modifier = Modifier.clickable { onDetailsClick() })
                    }
                }
                // 기프트 이름, 사용 기간, 거래 상태 및 현재 입찰가
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(2f)
                ) {
                    Text(text = giftName)
                    Text(text = giftDuration)
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(20.dp)
                        .weight(2f)
                ) {
                    Text(
                        text = transactionStatus,
                        fontSize = 22.sp,
                        color = Color.Green,
                        fontWeight = FontWeight.Bold
                    ) // 거래 상태 표시
                    Text(text = "현재 입찰가", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = currentBid,
                        fontSize = 20.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )  // 현재 입찰가 표시
                }
            }
            // 삭제 아이콘
            Icon(imageVector = Icons.Default.Close,
                contentDescription = "삭제",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clickable { onDeleteClick() }
                    .padding(8.dp))
        }
    }
}