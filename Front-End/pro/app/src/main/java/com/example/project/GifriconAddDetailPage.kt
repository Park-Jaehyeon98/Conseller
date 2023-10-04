package com.example.project

import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.api.UploadGifticonResponse
import com.example.project.api.userUploadGifticonResponse
import com.example.project.reuse_component.CustomGiftTextField
import com.example.project.ui.theme.BrandColor1
import com.example.project.viewmodels.MyPageViewModel
import com.example.project.viewmodels.OcrViewModel
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import truncateString

@Composable
fun GifticonAddDetailPage(navController: NavHostController) {
    // OCR 모델
    val OcrViewModel: OcrViewModel = hiltViewModel()
    // 기프티콘 등록 모델
    val MyPageViewModel: MyPageViewModel = hiltViewModel()
    val ocrResult by OcrViewModel.ocrSuccess.collectAsState(initial = false)
    var selectImage by remember { mutableStateOf<Uri?>(null) }
    var selectedCategoryIndex by remember { mutableStateOf(-1) }
    var SendState by remember { mutableStateOf(false) }

    val getOcrResult by OcrViewModel.uploadGifticonResponse.collectAsState()

    var gifticonBarcode by remember {
        mutableStateOf(TextFieldValue(getOcrResult.gifticonBarcode))
    }
    var gifticonName by remember {
        mutableStateOf(TextFieldValue(getOcrResult.gifticonEndDate))
    }
    var gifticonEndDate by remember {
        mutableStateOf(TextFieldValue(getOcrResult.gifticonName))
    }
// Crop된 이미지
    var gifticonCropImage by remember {
        mutableStateOf(getOcrResult.gifticonCropImage)
    }

    LaunchedEffect(getOcrResult) {
        gifticonBarcode = TextFieldValue(getOcrResult.gifticonBarcode)
        gifticonName = TextFieldValue(getOcrResult.gifticonName)
        gifticonEndDate = TextFieldValue(getOcrResult.gifticonEndDate)
        gifticonCropImage = getOcrResult.gifticonCropImage
    }

    val UploadState by MyPageViewModel.uploadGifticonResponse.collectAsState()

    // 사용자가 채워야하는 값
    var subCategoryIdx by remember { mutableStateOf(-1) }
    var mainCategoryIdx by remember { mutableStateOf(-1) }

    //화면전환
    var currentPage by remember { mutableStateOf<Int>(0) }
    val currentUpdatedPage = rememberUpdatedState(currentPage)
    // 스크롤
    val scrollState = rememberScrollState()
    //이미지 관련 로직
    val context = LocalContext.current

    LaunchedEffect(currentPage) {
        if (currentPage == -1) {
            navController.navigate("MyGifticonAdd")
        }
    }

    LaunchedEffect(UploadState) {
        if (UploadState) {
            currentPage++
        }
    }



    LaunchedEffect(selectImage) { // selectImage의 변화를 감지
        val categorychice = selectedCategoryIndex
        val currentImage = selectImage
        if (currentImage != null && SendState) {
            val inputStream = getInputStreamFromUri(context, currentImage)
            val byteArray = getBytesFromInputStream(inputStream!!)

            // RequestBody 생성
            val requestFile: RequestBody = byteArray.toRequestBody("image/*".toMediaTypeOrNull())

            // MultipartBody.Part 생성
            val multipartImage: MultipartBody.Part = MultipartBody.Part.createFormData(
                "image", "profile2.jpg", requestFile
            )

            OcrViewModel.uploadGifticon(categorychice, multipartImage)
            SendState = false
        }
    }



    // 기프티콘 업로드를 위한 값
    fun makeRequest(): RequestBody {
        val request = userUploadGifticonResponse(
            gifticonBarcode = gifticonBarcode.text,
            gifticonName = gifticonName.text,
            gifticonEndDate = gifticonEndDate.text,
            subCategory = subCategoryIdx,
            mainCategory = mainCategoryIdx
        )
        val gson = Gson()
        val jsonRequest = gson.toJson(request)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = jsonRequest.toRequestBody(mediaType)
        val requestPart =
            MultipartBody.Part.createFormData("gifticonPostRequest", null, requestBody)

        return requestBody
    }

    fun makeOriginalFile(): MultipartBody.Part {
        val myname: String? = OcrViewModel.getUserNickName()
        val currentImage = selectImage ?: throw IllegalArgumentException("Image URI is null")

        val inputStream = getInputStreamFromUri(context, currentImage)
        val byteArray = getBytesFromInputStream(inputStream!!)

        // RequestBody 생성
        val requestFile: RequestBody = byteArray.toRequestBody("image/*".toMediaTypeOrNull())

        // MultipartBody.Part 생성
        val multipartImage: MultipartBody.Part = MultipartBody.Part.createFormData(
            "originalFile", "${myname}.jpg", requestFile // jpg이름은 추가해주세요.
        )

        return multipartImage
    }

    fun makeCropFile(): MultipartBody.Part {
        val myname: String? = OcrViewModel.getUserNickName()

        // Base64로 인코딩된 문자열을 가져옵니다.
        val base64String = getOcrResult.gifticonCropImage

        // Base64 문자열을 디코딩하여 바이트 배열로 변환합니다.
        val byteArray = Base64.decode(base64String, Base64.DEFAULT)

        // RequestBody 생성
        val requestFile: RequestBody = byteArray.toRequestBody("image/*".toMediaTypeOrNull())

        // MultipartBody.Part 생성
        val multipartImage: MultipartBody.Part = MultipartBody.Part.createFormData(
            "cropFile", "${myname}.jpg", requestFile
        )

        return multipartImage
    }





    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (currentUpdatedPage.value) {
            0 -> {
                //메인 카테고리
                ChoiceMainCategory(MainCategory = mainCategoryIdx,
                    onMainCategorySet = { newMainCategory -> mainCategoryIdx = newMainCategory },
                    subCategory = subCategoryIdx,
                    onSubCategorySet = { newSubCategory -> subCategoryIdx = newSubCategory })
            }

            1 -> {
                //카테고리
                ChoiceCategory(category = selectedCategoryIndex,
                    onCategorySet = { newCategory -> selectedCategoryIndex = newCategory })
            }

            2 -> {
                GifticonUpload(onImageSelected = { uri: Uri ->
                    selectImage = uri
                }, setSendState = { Check: Boolean -> SendState = Check })
            }

            3 -> {
                GifticonCheck(imageView = selectImage)
                Spacer(modifier = Modifier.height(10.dp))
                CustomGiftTextField(label = "일련번호",
                    value = gifticonBarcode,
                    onValueChange = { ModifyGifticonBarcode ->
                        gifticonBarcode = ModifyGifticonBarcode
                    })
                Spacer(modifier = Modifier.height(10.dp))
                CustomGiftTextField(label = "유효기간",
                    value = gifticonEndDate,
                    onValueChange = { ModifyGifticonEndDate ->
                        gifticonEndDate = ModifyGifticonEndDate
                    })
                Spacer(modifier = Modifier.height(10.dp))
                CustomGiftTextField(label = " 제품명",
                    value = gifticonName,
                    onValueChange = { ModifyGifticonName -> gifticonName = ModifyGifticonName })
                Spacer(modifier = Modifier.height(30.dp))
                NormalButton(label = "기프티콘 등록", buttonSize = 50, textSize = 18, onClick = {
                    MyPageViewModel.gifticonUpload(
                        makeRequest(), makeOriginalFile(), makeCropFile()
                    )
                })
                Spacer(modifier = Modifier.height(10.dp))
            }

            4 -> {
                GifticonRegistrationCompleteMessage(gifticonName = gifticonName.text)
                NormalButton(label = "확인",
                    buttonSize = 50,
                    textSize = 18,
                    onClick = { navController.navigate("MyPage") })
            }
        }

        if (currentUpdatedPage.value <4) {
            Spacer(modifier = Modifier.height(40.dp)) // 이 부분이 Column의 나머지 공간을 채웁니다.
            pagechanger(currentPage = currentPage, onSetPage = { newPage -> currentPage = newPage })
        }
    }
}

@Composable
fun CustomGiftTextField(
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {}
        Text(text = label, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Surface(
            modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(8.dp))
                .background(BrandColor1)
                .fillMaxWidth(0.8f)
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    textAlign = TextAlign.Left,
                    color = Color.White
                ),
                modifier = Modifier
                    .height(40.dp)
                    .weight(0.8f)
                    .padding(start = 8.dp)
                    .horizontalScroll(scrollState)
                    .background(BrandColor1)
            )
        }
    }
}

@Composable
fun NormalButton(
    label: String, buttonSize: Int, textSize: Int, onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(BrandColor1),
        modifier = Modifier
            .width(180.dp) // 버튼의 가로 크기 조절
            .height(buttonSize.dp) // 버튼의 높이 조절
    ) {
        Text(text = label, fontSize = textSize.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ChoiceMainCategory(
    MainCategory: Int,
    onMainCategorySet: (Int) -> Unit,
    subCategory: Int,
    onSubCategorySet: (Int) -> Unit
) {
    // 선택된 대분류카테고리 인덱스를 저장하는 상태
    var selectedCategoryIndex by remember { mutableStateOf(-1) }
    // 선택된 중분류카테고리 인덱스를 저장하는 상태
    var selectedSubCategoryIndex by remember { mutableStateOf(-1) }
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(text = "카테고리 선택", fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val imageModifier = Modifier
                .size(60.dp, 60.dp)
                .clip(CircleShape)

            // 햄버거/치킨/피자
            val borderModifier1 = Modifier.border(
                width = 3.dp,
                shape = CircleShape,
                color = if (selectedCategoryIndex == 1) BrandColor1 else Color.Transparent
            )
            Column(
                modifier = Modifier.clickable {
                    onMainCategorySet(1)
                    selectedCategoryIndex = 1
                }, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fastfood),
                    contentDescription = "FastFood",
                    modifier = imageModifier.then(borderModifier1),
                    contentScale = ContentScale.Crop
                )
                Text(text = "버거/치킨/피자", fontWeight = FontWeight.Bold)
            }

            // 편의점
            val borderModifier2 = Modifier.border(
                width = 3.dp,
                shape = CircleShape,
                color = if (selectedCategoryIndex == 2) BrandColor1 else Color.Transparent
            )
            Column(
                modifier = Modifier.clickable {
                    onMainCategorySet(2)
                    selectedCategoryIndex = 2
                }, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.conveniencestore),
                    contentDescription = "ConvenienceStore",
                    modifier = imageModifier.then(borderModifier2),
                    contentScale = ContentScale.Crop
                )
                Text(text = "편의점", fontWeight = FontWeight.Bold)
            }

            // 카페/베이커리
            val borderModifier3 = Modifier.border(
                width = 3.dp,
                shape = CircleShape,
                color = if (selectedCategoryIndex == 3) BrandColor1 else Color.Transparent
            )
            Column(
                modifier = Modifier.clickable {
                    onMainCategorySet(3)
                    selectedCategoryIndex = 3
                }, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cafe),
                    contentDescription = "Cafe and Bakery",
                    modifier = imageModifier.then(borderModifier3),
                    contentScale = ContentScale.Crop
                )
                Text(text = "카페/베이커리", fontWeight = FontWeight.Bold)
            }

            // 아이스크림
            val borderModifier4 = Modifier.border(
                width = 3.dp,
                shape = CircleShape,
                color = if (selectedCategoryIndex == 4) BrandColor1 else Color.Transparent
            )
            Column(
                modifier = Modifier.clickable {
                    onMainCategorySet(4)
                    selectedCategoryIndex = 4
                }, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icecream),
                    contentDescription = "IceCream",
                    modifier = imageModifier.then(borderModifier4),
                    contentScale = ContentScale.Crop
                )
                Text(text = "아이스크림", fontWeight = FontWeight.Bold)
            }
            //기타
            val borderModifier5 = Modifier.border(
                width = 3.dp,
                shape = CircleShape,
                color = if (selectedCategoryIndex == 5) BrandColor1 else Color.Transparent
            )
            Column(
                modifier = Modifier.clickable {
                    onMainCategorySet(5)
                    selectedCategoryIndex = 5
                }, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.etc),
                    contentDescription = "Etc",
                    modifier = imageModifier.then(borderModifier5),
                    contentScale = ContentScale.Crop
                )
                Text(text = "기타", fontWeight = FontWeight.Bold)
            }
        }
        if (selectedCategoryIndex == 1) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val imageModifier = Modifier
                    .size(60.dp, 60.dp)
                    .clip(CircleShape)


                val FastFoodModifier1 = Modifier.border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (selectedSubCategoryIndex == 1) BrandColor1 else Color.Transparent
                )
                Column(
                    modifier = Modifier.clickable {
                        onSubCategorySet(1)
                        selectedSubCategoryIndex = 1
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hamburger),
                        contentDescription = "FastFood",
                        modifier = imageModifier.then(FastFoodModifier1),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "햄버거", fontWeight = FontWeight.Bold)
                }


                val FastFoodModifier2 = Modifier.border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (selectedSubCategoryIndex == 2) BrandColor1 else Color.Transparent
                )
                Column(
                    modifier = Modifier.clickable {
                        onSubCategorySet(2)
                        selectedSubCategoryIndex = 2
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fry),
                        contentDescription = "ConvenienceStore",
                        modifier = imageModifier.then(FastFoodModifier2),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "치킨", fontWeight = FontWeight.Bold)
                }


                val FastFoodModifier3 = Modifier.border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (selectedSubCategoryIndex == 3) BrandColor1 else Color.Transparent
                )
                Column(
                    modifier = Modifier.clickable {
                        onSubCategorySet(3)
                        selectedSubCategoryIndex = 3
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pizza),
                        contentDescription = "Cafe and Bakery",
                        modifier = imageModifier.then(FastFoodModifier3),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "피자", fontWeight = FontWeight.Bold)
                }
            }
        } else if (selectedCategoryIndex == 2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val imageModifier = Modifier
                    .size(60.dp, 60.dp)
                    .clip(CircleShape)


                val ConvenienceModifier1 = Modifier.border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (selectedSubCategoryIndex == 4) BrandColor1 else Color.Transparent
                )
                Column(
                    modifier = Modifier.clickable {
                        onSubCategorySet(4)
                        selectedSubCategoryIndex = 4
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.giftvoucher),
                        contentDescription = "FastFood",
                        modifier = imageModifier.then(ConvenienceModifier1),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "상품권", fontWeight = FontWeight.Bold)
                }
                val ConvenienceModifier2 = Modifier.border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (selectedSubCategoryIndex == 5) BrandColor1 else Color.Transparent
                )
                Column(
                    modifier = Modifier.clickable {
                        onSubCategorySet(5)
                        selectedSubCategoryIndex = 5
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.snack),
                        contentDescription = "FastFood",
                        modifier = imageModifier.then(ConvenienceModifier2),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "과자", fontWeight = FontWeight.Bold)
                }
                val ConvenienceModifier3 = Modifier.border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (selectedSubCategoryIndex == 6) BrandColor1 else Color.Transparent
                )
                Column(
                    modifier = Modifier.clickable {
                        onSubCategorySet(6)
                        selectedSubCategoryIndex = 6
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.coca),
                        contentDescription = "FastFood",
                        modifier = imageModifier.then(ConvenienceModifier3),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "음료", fontWeight = FontWeight.Bold)
                }
                val ConvenienceModifier4 = Modifier.border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (selectedSubCategoryIndex == 7) BrandColor1 else Color.Transparent
                )
                Column(
                    modifier = Modifier.clickable {
                        onSubCategorySet(7)
                        selectedSubCategoryIndex = 7
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.kimbab),
                        contentDescription = "FastFood",
                        modifier = imageModifier.then(ConvenienceModifier4),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "도시락/김밥류", fontWeight = FontWeight.Bold)
                }
                val ConvenienceModifier5 = Modifier.border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (selectedSubCategoryIndex == 0) BrandColor1 else Color.Transparent
                )
                Column(
                    modifier = Modifier.clickable {
                        onSubCategorySet(0)
                        selectedSubCategoryIndex = 0
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.etc),
                        contentDescription = "FastFood",
                        modifier = imageModifier.then(ConvenienceModifier5),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "기타", fontWeight = FontWeight.Bold)
                }
            }
        } else if (selectedCategoryIndex == 3) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val imageModifier = Modifier
                    .size(60.dp, 60.dp)
                    .clip(CircleShape)


                val BakeryModifier1 = Modifier.border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (selectedSubCategoryIndex == 8) BrandColor1 else Color.Transparent
                )
                Column(
                    modifier = Modifier.clickable {
                        onSubCategorySet(8)
                        selectedSubCategoryIndex = 8
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.coffee),
                        contentDescription = "GiftVoucher",
                        modifier = imageModifier.then(BakeryModifier1),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "커피", fontWeight = FontWeight.Bold)
                }
                val BakeryModifier2 = Modifier.border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (selectedSubCategoryIndex == 9) BrandColor1 else Color.Transparent
                )
                Column(
                    modifier = Modifier.clickable {
                        onSubCategorySet(9)
                        selectedSubCategoryIndex = 9
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bakery),
                        contentDescription = "Snack",
                        modifier = imageModifier.then(BakeryModifier2),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "베이커리", fontWeight = FontWeight.Bold)
                }
                val BakeryModifier3 = Modifier.border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (selectedSubCategoryIndex == 0) BrandColor1 else Color.Transparent
                )
                Column(
                    modifier = Modifier.clickable {
                        onSubCategorySet(0)
                        selectedSubCategoryIndex = 0
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.etc),
                        contentDescription = "Drink",
                        modifier = imageModifier.then(BakeryModifier3),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "기타", fontWeight = FontWeight.Bold)
                }
            }
        } else if (selectedCategoryIndex == 4) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                val imageModifier = Modifier
                    .size(60.dp, 60.dp)
                    .clip(CircleShape)


                val IceCreamModifier1 = Modifier.border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (selectedSubCategoryIndex == 10) BrandColor1 else Color.Transparent
                )
                Column(
                    modifier = Modifier.clickable {
                        onSubCategorySet(10)
                        selectedSubCategoryIndex = 10
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baskinrobbins),
                        contentDescription = "GiftVoucher",
                        modifier = imageModifier.then(IceCreamModifier1),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "베스킨라빈스", fontWeight = FontWeight.Bold)
                }
                val IceCreamModifier2 = Modifier.border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (selectedSubCategoryIndex == 0) BrandColor1 else Color.Transparent
                )
                Column(
                    modifier = Modifier.clickable {
                        onSubCategorySet(0)
                        selectedSubCategoryIndex = 0
                    }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.etc),
                        contentDescription = "Snack",
                        modifier = imageModifier.then(IceCreamModifier2),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "기타", fontWeight = FontWeight.Bold)
                }
            }
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
                shape = CircleShape,
                color = if (selectedCategoryIndex == 0) BrandColor1 else Color.Transparent
            )
            Column(
                modifier = Modifier.clickable {
                    onCategorySet(0)
                    selectedCategoryIndex = 0
                }, horizontalAlignment = Alignment.CenterHorizontally
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
                shape = CircleShape,
                color = if (selectedCategoryIndex == 1) BrandColor1 else Color.Transparent
            )
            Column(
                modifier = Modifier.clickable {
                    onCategorySet(1)
                    selectedCategoryIndex = 1
                }, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.giftishow),
                    contentDescription = "GiftiShow Image",
                    modifier = imageModifier.then(borderModifier2),
                    contentScale = ContentScale.Crop
                )
                Text(text = "기프티쇼", fontWeight = FontWeight.Bold)
            }

            // 기프티쇼
            val borderModifier3 = Modifier.border(
                width = 3.dp,
                shape = CircleShape,
                color = if (selectedCategoryIndex == 2) BrandColor1 else Color.Transparent
            )
            Column(
                modifier = Modifier.clickable {
                    onCategorySet(2)
                    selectedCategoryIndex = 2
                }, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ssafy),
                    contentDescription = "SSAFY Image",
                    modifier = imageModifier.then(borderModifier3),
                    contentScale = ContentScale.Crop
                )
                Text(text = "싸피", fontWeight = FontWeight.Bold)
            }

            // 기타
            val borderModifier4 = Modifier.border(
                width = 3.dp,
                shape = CircleShape,
                color = if (selectedCategoryIndex == 3) BrandColor1 else Color.Transparent
            )
            Column(
                modifier = Modifier.clickable {
                    onCategorySet(3)
                    selectedCategoryIndex = 3
                }, horizontalAlignment = Alignment.CenterHorizontally
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
    onImageSelected: (Uri) -> Unit,
    setSendState: (Boolean) -> Unit,

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
        val imageToShow = selectImage
        if (imageToShow != null) {
            Image(
                painter = rememberAsyncImagePainter(imageToShow),
                contentDescription = "Default Gift Image",
                modifier = Modifier
                    .size(300.dp, 400.dp)
                    .border(1.dp, Color.Black),
                contentScale = ContentScale.FillBounds
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.uploadgiftdefault),
                contentDescription = "Default Gift Image",
                modifier = Modifier
                    .size(300.dp, 400.dp)
                    .border(1.dp, Color.Black),
                contentScale = ContentScale.FillBounds
            )
        }

        val commonHeight = 50.dp // 공통 높이 값
        Spacer(modifier = Modifier.height(commonHeight))
        Button(
            onClick = {
                galleryLauncher.launch("image/*")
                setSendState(true)
            },
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
fun GifticonCheck(
    imageView: (Uri?),
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageView),
            contentDescription = "Default Gift Image",
            modifier = Modifier
                .size(300.dp, 400.dp)
                .border(1.dp, Color.Black),
            contentScale = ContentScale.FillBounds
        )
    }
    val commonHeight = 50.dp // 공통 높이 값
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
                if (currentPage - 1 >= -1) {
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

@Composable
fun GifticonRegistrationCompleteMessage(gifticonName: String) {
    val OcrViewModel: OcrViewModel = hiltViewModel()
    val myname: String? = OcrViewModel.getUserNickName()
    val scrollState = rememberScrollState()

    val truncatedGifticonName = truncateString(gifticonName, 15)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .size(300.dp)
                .verticalScroll(scrollState),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, Color.White),
            colors = CardDefaults.cardColors(BrandColor1)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "\uD83C\uDF89 축하드려요! \uD83C\uDF89",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${myname}님 \n\n 제품명: ${truncatedGifticonName} \n\n 등록이 완료되었습니다!",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
