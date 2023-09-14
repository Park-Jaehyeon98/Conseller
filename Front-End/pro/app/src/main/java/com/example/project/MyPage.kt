package com.example.project

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@Composable
fun MyPage(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            UserProfile(
                userNickName = "testNickName",
                userEmail = "testEmail",
                userPhoneNumber = "testPhoneNumber"
            )
            GiftCard(
                onDetailsClick = {},
                onDeleteClick = {},
                giftName = "스타벅스",
                giftDuration = "일단 오늘은 아님",
                transactionStatus = "보관중",
                currentBid = "2000원"
            )

        }
    }


}

@Composable
fun UserProfile(
    profileImage: String? = null, userNickName: String, userEmail: String, userPhoneNumber: String
) {
    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (profileImage != null) {
            Image(painter = rememberAsyncImagePainter(profileImage),
                contentDescription = "유저 프로필 이미지",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .clickable { openImagePicker() })
        } else {
            Image(
                painter = painterResource(id = R.drawable.defaultimage),
                contentDescription = "Default User Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }
        Text(text = userNickName, fontSize = 22.sp)
        Text(text = userEmail)
        Text(text = userPhoneNumber)

    }
}

fun openImagePicker() {

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