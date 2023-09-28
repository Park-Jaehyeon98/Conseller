package com.example.project

import FilterButton
import SelectButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BarterUpdatePage(index: String?, navController: NavHostController) {
    val barterViewModel: BarterViewModel = hiltViewModel()
    val barterDetail by barterViewModel.barterDetail.collectAsState() // 게시글 사진
    val error by barterViewModel.error.collectAsState()
    val scrollState = rememberScrollState()

    val keyboardController = LocalSoftwareKeyboardController.current


    // 게시글 제목 및 내용을 위한 상태값
    var postTitle by remember { mutableStateOf("") }
    var postContent by remember { mutableStateOf("") }

    var showSnackbar by remember { mutableStateOf(false) } // 에러처리스낵바
    var snackbarText by remember { mutableStateOf("") }

    LaunchedEffect(error) {
        if (error != null) {
            showSnackbar = true
            snackbarText = error!!
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
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            if (showSnackbar) {
                Snackbar(
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Text(
                        text = snackbarText,
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp) // 이미지들 사이의 간격
            ) {
                items(barterDetail?.barterImageList.orEmpty()) { item ->
                    val imagePainter = rememberAsyncImagePainter(model = item.gifticonDataImageName)
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        Text(
            text = "게시글을 작성해주세요.",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 28.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 필터 버튼의 상태
        var filter1Selected by remember { mutableStateOf("대분류") }
        var filter2Selected by remember { mutableStateOf("소분류") }

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
                filter2Selected = "소분류"
            }

            Spacer(modifier = Modifier.width(24.dp))

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
                keyboardController?.hide()
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
                keyboardController?.hide()
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
                text = "수정하기",
                onClick = {
                    barterViewModel.updateBarterItem(
                        index!!.toLong(),
                        filter1Selected,
                        filter2Selected,
                        postTitle,
                        postContent,
                        "123"
                    )
                    if(error == null) {
                        navController.navigate("BarterDetailPage/${index}")
                    }
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            SelectButton(
                text = "취소하기",
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            )
        }
    }
}
