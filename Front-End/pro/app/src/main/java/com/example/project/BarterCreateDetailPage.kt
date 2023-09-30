package com.example.project

import FilterButton
import SelectButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.BarterViewModel
import com.example.project.viewmodels.MygifticonViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BarterCreateDetailPage(navController: NavHostController, selectedItemIndices: List<Long>, mygifticonViewModel: MygifticonViewModel) {
    val barterviewModel: BarterViewModel = hiltViewModel()
    val error by barterviewModel.error.collectAsState()
    val myerror by mygifticonViewModel.error.collectAsState()
    val selectedItems = mygifticonViewModel.getSelectedItems(selectedItemIndices)
    val createBarterNavi by barterviewModel.createBarterNavi.collectAsState() // 등록시 idx받기
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    // 게시글 제목 및 내용을 위한 상태값
    var postTitle by remember { mutableStateOf("") }
    var postContent by remember { mutableStateOf("") }
    var showRegisterConfirmDialog by remember { mutableStateOf(false) }

    var showSnackbar by remember { mutableStateOf(false) } // 에러처리스낵바
    var snackbarText by remember { mutableStateOf("") }

    LaunchedEffect(error) {
        if (error != null) {
            showSnackbar = true
            snackbarText = error!!
        }
    }
    LaunchedEffect(myerror) {
        if (myerror != null) {
            showSnackbar = true
            snackbarText = myerror!!
        }
    }
    LaunchedEffect(createBarterNavi) {
        createBarterNavi?.let { barterIdx ->
            navController.navigate("BarterDetailPage/${barterIdx}")
            barterviewModel.resetNavigation()
        }
    }
    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            kotlinx.coroutines.delay(5000)
            showSnackbar = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        if (showSnackbar) {
            Snackbar(
            ) {
                Text(text = snackbarText, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
        }

        Text(
            text = "2. 원하는 기프티콘의 분류군과",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "게시글을 작성해주세요.",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 28.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 이미지 캐러셀
        LazyRow(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
        ) {
            items(selectedItems.size) { index ->
                val item = selectedItems[index]
                Image(
                    painter = rememberAsyncImagePainter(model = item.gifticonImageName),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .width(150.dp)
                        .height(150.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 필터 버튼의 상태
        var filter1Selected by remember { mutableStateOf("대분류") } // 필터1의 초기값
        var filter2Selected by remember { mutableStateOf("소분류") } // 필터2의 초기값

        // 필터 버튼
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            FilterButton(
                selectedOption = filter1Selected,
                options = listOf(
                    "대분류",
                    "버거/치킨/피자",
                    "편의점",
                    "카페/베이커리",
                    "아이스크림",
                    "기타"
                ),
            ) { selectedOption ->
                filter1Selected = selectedOption
                // 첫 번째 필터가 변경되면, 두 번째 필터를 초기값으로 설정
                filter2Selected = "소분류"
            }

            Spacer(modifier = Modifier.width(24.dp))  // 버튼 사이 간격 조절

            FilterButton(
                selectedOption = filter2Selected,
                options = when (filter1Selected) {
                    "버거/치킨/피자" -> listOf("전체", "버거", "치킨", "피자")
                    "편의점" -> listOf("전체", "금액권", "과자", "음료", "도시락/김밥류", "기타")
                    "카페/베이커리" -> listOf("전체", "카페", "베이커리", "기타")
                    "아이스크림" -> listOf("전체", "베스킨라빈스", "기타")
                    "기타" -> listOf("전체")
                    else -> listOf("전체")
                },
            ) { selectedOption ->
                filter2Selected = selectedOption
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 게시글 제목 입력
        Text("게시글 제목", modifier = Modifier.padding(bottom = 8.dp), fontSize = 20.sp)
        OutlinedTextField(
            value = postTitle,
            onValueChange = { postTitle = it },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide() // 키보드 숨기기
            })
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 게시글 내용 입력
        Text("게시글 내용", modifier = Modifier.padding(bottom = 8.dp), fontSize = 20.sp)
        OutlinedTextField(
            value = postContent,
            onValueChange = { postContent = it },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(200.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide() // 키보드 숨기기
            })
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            SelectButton(
                text = "등록",
                onClick = {
                    if(error == null) {
                        showRegisterConfirmDialog = true
                    }
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))  // 버튼 사이 간격 조절

            SelectButton(
                text = "이전",
                onClick = {
                    navController.navigate("BarterCreatePage")
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            )
        }

        // 등록 확인 대화상자
        if (showRegisterConfirmDialog) {
            AlertDialog(
                onDismissRequest = {
                    showRegisterConfirmDialog = false
                },
                title = {
                    Text(text = "등록")
                },
                text = {
                    Text("등록하시겠습니까?", fontSize = 18.sp)
                },
                confirmButton = {
                    SelectButton(
                        text = "아니오",
                        onClick = {
                            showRegisterConfirmDialog = false
                        }
                    )
                },
                dismissButton = {
                    SelectButton(
                        text = "예",
                        onClick = {
                            barterviewModel.createBarterItem(
                                kindBigStatus = filter1Selected,
                                kindSmallStatus = filter2Selected,
                                barterName = postTitle,
                                barterText = postContent,
                                barterEndDate = "11111111111111", // 추후 게시글 시간 조정할때 수정
                                selectedItemIndices = selectedItemIndices,
                            )
                            showRegisterConfirmDialog = false
                        }
                    )
                }
            )
        }
    }
}