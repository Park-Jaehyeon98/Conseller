package com.example.project

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.ui.theme.BrandColor1
import com.example.project.viewmodels.MygifticonViewModel
import com.example.project.viewmodels.OcrViewModel

@Composable
fun GifticonAddDetailPage(navController: NavHostController) {
    // OCR 모델
    val OcrViewModel: OcrViewModel= hiltViewModel()
    // 기프티콘 등록 모델
    val viewModel: MygifticonViewModel = hiltViewModel()

    var selectImage by remember { mutableStateOf<Uri?>(null) }
    var selectedCategoryIndex by remember { mutableStateOf(0) }

    val getOcrResult by viewModel.uploadGifticonResponse.collectAsState()
    // OCR이 자동으로 채워주는 값
    var gifticonBarcode by remember { mutableStateOf(TextFieldValue(getOcrResult.gitriconBarcode)) }
    var gifticonName by remember { mutableStateOf(TextFieldValue(getOcrResult.gifticonName)) }
    var gifticonEndDate by remember { mutableStateOf(TextFieldValue(getOcrResult.gifticonEndData)) }
    // Crop된 이미지
    var gifticonCropImage by remember { mutableStateOf<Uri?>(null) }

    // 사용자가 채워야하는 값
    var subCategoryIdx by remember { mutableStateOf("버거/치킨/피자") }
    var mainCategoryIdx by remember { mutableStateOf("피자") }

    var currentPage by remember { mutableStateOf<Int>(0) }
    val currentUpdatedPage = rememberUpdatedState(currentPage)
    // 스크롤
    val scrollState = rememberScrollState()
    //이미지 관련 로직
    val context = LocalContext.current



    LaunchedEffect(selectImage) {
        selectImage?.let { currentImage ->
            Log.d("GifticonAddDetailPage", "Trying to upload image for OCR")
            val inputStream = getInputStreamFromUri(context, currentImage)
            val byteArray = getBytesFromInputStream(inputStream!!)
            val multipartImage = getMultipartFromByteArray(byteArray, "gifticon.jpg")

            OcrViewModel.uploadGifticon(selectedCategoryIndex, multipartImage)

            Log.d("GifticonAddDetailPage", "Image upload attempted")
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (currentUpdatedPage.value == 0) {
            //카테고리
            ChoiceCategory(category = selectedCategoryIndex,
                onCategorySet = { newCategory -> selectedCategoryIndex = newCategory })
            Spacer(modifier = Modifier.height(24.dp))
            pagechanger(currentPage = currentPage, onSetPage = { newPage -> currentPage = newPage })
        } else if (currentUpdatedPage.value == 1) {
            GifticonUpload(
                onImageSelected = { uri: Uri ->
                    selectImage = uri
                },
            )
            Spacer(modifier = Modifier.height(24.dp))
            pagechanger(currentPage = currentPage, onSetPage = { newPage -> currentPage = newPage })
        } else if (currentUpdatedPage.value == 2) {
            Text(text = "다음2")
            pagechanger(currentPage = currentPage, onSetPage = { newPage -> currentPage = newPage })
        } else if (currentUpdatedPage.value == 3) {
            Text(text = "다음3")
            pagechanger(currentPage = currentPage, onSetPage = { newPage -> currentPage = newPage })
        } else if (currentUpdatedPage.value == 4) {
            Text(text = "다음4")
            pagechanger(currentPage = currentPage, onSetPage = { newPage -> currentPage = newPage })
        } else if (currentUpdatedPage.value == 5) {
            Text(text = "다음5")
            pagechanger(currentPage = currentPage, onSetPage = { newPage -> currentPage = newPage })
        }
    }
}


@Composable
fun ChoiceCategory(
    category: Int, onCategorySet: (Int) -> Unit
) {
    // 선택된 카테고리 인덱스를 저장하는 상태
    var selectedCategoryIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(text = "구매처 선택", fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val imageModifier = Modifier
                .size(70.dp, 70.dp)
                .clip(CircleShape)

            // 카카오톡
            val borderModifier1 = Modifier.border(
                width = 3.dp,
                shape= CircleShape,
                color = if (selectedCategoryIndex == 0) BrandColor1 else Color.Transparent
            )
            Column(
                modifier = Modifier
                    .clickable {
                        onCategorySet(0)
                        selectedCategoryIndex = 0
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.kakaotalk),
                    contentDescription = "KakaoTalk Image",
                    modifier = imageModifier.then(borderModifier1),
                    contentScale = ContentScale.Crop
                )
                Text(text = "카카오톡", fontWeight = FontWeight.Bold)
            }

            // 싸피
            val borderModifier2 = Modifier.border(
                width = 3.dp,
                shape= CircleShape,
                color = if (selectedCategoryIndex == 1) BrandColor1 else Color.Transparent
            )
            Column(
                modifier = Modifier
                    .clickable {
                        onCategorySet(1)
                        selectedCategoryIndex = 1
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ssafy),
                    contentDescription = "SSAFY Image",
                    modifier = imageModifier.then(borderModifier2),
                    contentScale = ContentScale.Crop
                )
                Text(text = "싸피", fontWeight = FontWeight.Bold)
            }

            // 기프티쇼
            val borderModifier3 = Modifier.border(
                width = 3.dp,
                shape= CircleShape,
                color = if (selectedCategoryIndex == 2) BrandColor1 else Color.Transparent
            )
            Column(
                modifier = Modifier
                    .clickable {
                        onCategorySet(2)
                        selectedCategoryIndex = 2
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.giftishow),
                    contentDescription = "GiftiShow Image",
                    modifier = imageModifier.then(borderModifier3),
                    contentScale = ContentScale.Crop
                )
                Text(text = "기프티쇼", fontWeight = FontWeight.Bold)
            }

            // 기타
            val borderModifier4 = Modifier.border(
                width = 3.dp,
                shape= CircleShape,
                color = if (selectedCategoryIndex == 3) BrandColor1 else Color.Transparent
            )
            Column(
                modifier = Modifier
                    .clickable {
                        onCategorySet(3)
                        selectedCategoryIndex = 3
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.etc),
                    contentDescription = "Etc Image",
                    modifier = imageModifier.then(borderModifier4),
                    contentScale = ContentScale.Crop
                )
                Text(text = "기타", fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Composable
fun GifticonUpload(
    profileImage: Uri? = null,
    onImageSelected: (Uri) -> Unit,
) {
    var selectImage by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            selectImage = uri
            uri?.let { onImageSelected(it) }
        }

    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val imageToShow = selectImage ?: profileImage
        if (imageToShow != null) {
            Image(
                painter = rememberAsyncImagePainter(imageToShow),
                contentDescription = "Default Gift Image",
                modifier = Modifier
                    .size(500.dp, 200.dp)
                    .border(1.dp, Color.Black),
                contentScale = ContentScale.FillBounds
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.uploadgiftdefault),
                contentDescription = "Default Gift Image",
                modifier = Modifier
                    .size(300.dp, 200.dp)
                    .border(1.dp, Color.Black),
                contentScale = ContentScale.FillBounds
            )
        }

        val commonHeight = 50.dp // 공통 높이 값

        Button(
            onClick = { galleryLauncher.launch("image/*") },
            colors = ButtonDefaults.buttonColors(BrandColor1),
            modifier = Modifier
                .width(180.dp) // 버튼의 가로 크기 조절
                .height(commonHeight) // 버튼의 높이 조절
        ) {
            Text(text = "기프티콘 첨부", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun pagechanger(
    currentPage: Int,
    onSetPage: (Int) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                if (currentPage - 1 >= 0) {
                    onSetPage(currentPage - 1)
                }
            },
            colors = ButtonDefaults.buttonColors(BrandColor1),
            modifier = Modifier
                .width(100.dp)
                .height(50.dp)
        ) {
            Text(text = "이전", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = {
                if (currentPage < 5) {
                    onSetPage(currentPage + 1)
                }
            },
            colors = ButtonDefaults.buttonColors(BrandColor1),
            modifier = Modifier
                .width(100.dp)
                .height(50.dp)
        ) {
            Text(text = "다음", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}