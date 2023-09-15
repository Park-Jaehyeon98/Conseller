package com.example.project

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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


    LaunchedEffect(Unit) {
        viewModel.getMyInfo()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            UserProfile(
                profileImage=checkIdResult.userProfileUrl,
                userNickName = checkIdResult.userNickname,
                userEmail = checkIdResult.userEmail,
                userPhoneNumber = checkIdResult.userPhoneNumber
            )

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = {
//                val currentImage = selectImage
//                if(currentImage!=null){
//                    val inputStream = getInputStreamFromUri(context, currentImage)
//                    val byteArray = getBytesFromInputStream(inputStream!!)
//                    val multipartImage = getMultipartFromByteArray(byteArray, "profile.jpg") // "profile.jpg"는 예시 파일 이름입니다.
//                    Log.d("UserProfile", "Multipart data length: ${byteArray.size}")
//                    viewModel.profileSend(multipartImage)
//                }
                    navController.navigate("MyPageValid")
                },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(BrandColor1)
                ) {
                    Text("개인정보 변경")
                }
                Button(onClick = {
                    navController.navigate("MyGifticonAdd")
                },
                    modifier = Modifier.padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(BrandColor1)
                ) {
                    Text("기프티콘 등록")
                }
            }

        }
    }


}

@Composable
fun UserProfile(
    profileImage: String? = null, userNickName: String, userEmail: String, userPhoneNumber: String
) {
    val viewModel: MyPageViewModel = hiltViewModel()
    var selectImage by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            selectImage = uri
        }

//    //확인용
//    LaunchedEffect(selectImage) {
//        Log.d("UserProfile", "Selected images: $selectImage")
//    }


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
                    .clickable { galleryLauncher.launch("image/*") })
        } else {
            Image(painter = painterResource(id = R.drawable.defaultimage),
                contentDescription = "Default User Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .clickable { galleryLauncher.launch("image/*") })
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