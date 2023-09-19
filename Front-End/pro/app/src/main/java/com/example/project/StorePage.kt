package com.example.project

import FilterButton
import FormattedDateText
import PaginationControls
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.api.StoreFilterDTO
import com.example.project.viewmodels.StoreViewModel
import convertNameToNum

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun StorePage(navController: NavHostController) {
    val viewModel: StoreViewModel = hiltViewModel()
    val storeItems by viewModel.storeItems.collectAsState()
    val scrollState = rememberScrollState()

    var currentPage by remember { mutableIntStateOf(1) } // 현재 페이지 초기값
    val itemsPerPage = 10 // 페이지 당 표시할 항목 수

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            // 검색창
            var searchText by remember { mutableStateOf("") }
            val keyboardController = LocalSoftwareKeyboardController.current
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            viewModel.searchItems(searchText)
                            keyboardController?.hide() // 키보드 숨기기
                        }
                    )
                },
                placeholder = { Text("검색") },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(5.dp)),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    viewModel.searchItems(searchText)
                    keyboardController?.hide() // 키보드 숨기기
                }),
            )

            Spacer(modifier = Modifier.height(16.dp))

            var filter1Selected by remember { mutableStateOf("대분류") } // 필터1의 초기값
            var filter2Selected by remember { mutableStateOf("소분류") } // 필터2의 초기값
            var filter3Selected by remember { mutableStateOf("등록일") } // 필터3의 초기값

            // 필터 버튼
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                FilterButton(
                    selectedOption = filter1Selected,
                    options = listOf("대분류", "버거/치킨/피자", "편의점", "카페/베이커리", "아이스크림", "기타"),
                ) {
                    filter1Selected = it
                    filter2Selected = "소분류"
                    val (filter1Id, filter2Id, filter3Id) = convertNameToNum(
                        filter1Selected,
                        filter2Selected,
                        filter3Selected
                    )
                    viewModel.applyFilter(
                        StoreFilterDTO(
                            filter1Id,
                            filter2Id,
                            filter3Id,
                            searchText,
                            currentPage
                        )
                    )
                    }

                }
                item {
                FilterButton(
                    selectedOption = filter2Selected,
                    options = when (filter1Selected) {
                        "버거/치킨/피자" -> listOf("전체", "버거", "치킨", "피자")
                        "편의점" -> listOf("전체", "금액권", "과자", "음료","도시락/김밥류","기타")
                        "카페/베이커리" -> listOf("전체", "카페", "베이커리", "기타")
                        "아이스크림" -> listOf("전체", "베스킨라빈스", "기타")
                        "기타" -> listOf("전체")
                        else -> listOf("전체")
                    }
                ) {
                    filter2Selected = it
                    val (filter1Id, filter2Id, filter3Id) = convertNameToNum(
                        filter1Selected,
                        filter2Selected,
                        filter3Selected
                    )
                    viewModel.applyFilter(
                        StoreFilterDTO(
                            filter1Id,
                            filter2Id,
                            filter3Id,
                            searchText,
                            currentPage
                        )
                    )
                }
                }
                item {
                    FilterButton(
                        selectedOption = filter3Selected,
                        options = listOf("등록일", "유효기한", "입찰가", "즉시구입가")
                    ) {
                        filter3Selected = it
                        val (filter1Id, filter2Id, filter3Id) = convertNameToNum(
                            filter1Selected,
                            filter2Selected,
                            filter3Selected
                        )
                        viewModel.applyFilter(
                            StoreFilterDTO(
                                filter1Id,
                                filter2Id,
                                filter3Id,
                                searchText,
                                currentPage
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // StoreItem 넣기
            storeItems.forEach { item ->
                StoreItem(
                    image = item.gifticonDataImageName,
                    name = item.giftconName,
                    gifticonTime = item.gifticonEndDate,
                    storeTime = item.storeEndDate,
                    popular = item.popular,
                    storePrice = item.storePrice,
                    onItemClick = {
                        navController.navigate("StoreDetailPage/${item.storeIdx}")
                    }

                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))

            // 페이지네이션 컨트롤
            PaginationControls(
                totalItems = storeItems.size,
                currentPage = currentPage,
                itemsPerPage = itemsPerPage
            ) { newPage ->
                currentPage = newPage
                viewModel.changePage(newPage)  // 페이지 변경 시 데이터 받기
            }
        }
        // 등록하기
        FloatingActionButton(
            onClick = { navController.navigate("StoreCreatePage") },
            modifier = Modifier
                .align(Alignment.BottomEnd) // 우하단
                .padding(16.dp) // 화면의 가장자리 간격
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                // 글자 크기 조절
                Text("등록하기", fontSize = 18.sp)
            }

        }
    }
}



@Composable
fun StoreItem(
    image: String,
    name: String,
    gifticonTime: String,
    storeTime: String,
    popular: String,
    storePrice: Int,
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color.White)
            .padding(8.dp)
            .clickable { onItemClick() }
    ) {
        // 65% 이미지
        Box(
            modifier = Modifier
                .weight(0.65f)
                .fillMaxWidth()
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            // Home 아이콘 임시 image가 들어갈자리
            Icon(Icons.Default.Home, contentDescription = "Image")
            Image(
                painter = rememberAsyncImagePainter(model = image),
                contentDescription = "Loaded Image",
                modifier = Modifier.size(100.dp) // 원하는 크기로 조정
            )
        }

        // 10% 이름 및 유효기간
        Row(
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(name, fontSize = 20.sp)
            FormattedDateText(gifticonTime,"유효기간")
        }

        // 구분 줄
        Divider(color = Color.Gray, modifier = Modifier.padding(horizontal = 12.dp))
        Spacer(modifier = Modifier.height(4.dp))

        // 15% 박스1
        Box(
            modifier = Modifier
                .weight(0.10f)
                .padding(horizontal = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 40% 인기
                Text("인기: $popular", modifier = Modifier.weight(0.4f))

                // 60% 박스2
                Column(
                    modifier = Modifier.weight(0.6f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text("구매가: $storePrice 원")
                }
            }
        }
        // 5% 경매기간
        FormattedDateText(
            gifticonTime = storeTime,
            prefix = "마감일",
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )
    }
}
