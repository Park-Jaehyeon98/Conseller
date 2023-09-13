package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.project.viewmodels.BarterViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BarterCreateDetailPage(navController: NavHostController, selectedItemIndices: List<Long>) {
    val barterviewModel: BarterViewModel = hiltViewModel()
    val selectedItems = barterviewModel.getSelectedItems(selectedItemIndices)
    val navigateToIdx by barterviewModel.navigateToBarterDetail.collectAsState() // 등록시 idx받기
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    // 게시글 제목 및 내용을 위한 상태값
    var postTitle by remember { mutableStateOf("") }
    var postContent by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
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
                    painter = rememberAsyncImagePainter(model = item.gifticonDataImageName),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .width(150.dp)
                        .clip(RoundedCornerShape(8.dp))
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
                options = listOf("대분류", "a", "b", "c"),
            ) { selectedOption ->
                filter1Selected = selectedOption
                // 첫 번째 필터가 변경되면, 두 번째 필터를 초기값으로 설정
                filter2Selected = "소분류"
            }

            Spacer(modifier = Modifier.width(24.dp))  // 버튼 사이 간격 조절

            FilterButton(
                selectedOption = filter2Selected,
                options = when (filter1Selected) {
                    "a" -> listOf("소분류", "a-1", "a-2", "a-3")
                    "b" -> listOf("소분류", "b-1", "b-2", "b-3")
                    "c" -> listOf("소분류", "c-1", "c-2", "c-3")
                    else -> listOf("소분류")
                }
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
            Button(
                onClick = {
                    barterviewModel.createBarterItem(
                        kindBigStatus = filter1Selected,
                        kindSmallStatus = filter2Selected,
                        barterName = postTitle,
                        barterText = postContent,
                        barterEndDate = "123", // 나중에하자....
                        selectedItemIndices = selectedItemIndices,
                    )
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            ) {
                Text(text = "등록")
            }

            Spacer(modifier = Modifier.width(16.dp))  // 버튼 사이 간격 조절

            Button(
                onClick = {
                    navController.navigate("BarterCreatePage")
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            ) {
                Text("이전")
            }

            LaunchedEffect(navigateToIdx) {
                navigateToIdx?.let { auctionIdx ->
                    navController.navigate("AuctionDetailPage/${auctionIdx}")
                    barterviewModel.resetNavigation()
                }
            }
        }
    }
}