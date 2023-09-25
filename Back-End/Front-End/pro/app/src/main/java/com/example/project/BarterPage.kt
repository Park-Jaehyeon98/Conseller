package com.example.project

import PaginationControls
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import com.example.project.api.BarterFilterDTO
import com.example.project.viewmodels.BarterViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun BarterPage(navController: NavHostController) {
    val viewModel: BarterViewModel = hiltViewModel()
    val barterItems by viewModel.barterItems.collectAsState()
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
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    viewModel.searchItems(searchText)
                    keyboardController?.hide() // 키보드 숨기기
                })
            )

            Spacer(modifier = Modifier.height(16.dp))

            var filter1Selected by remember { mutableStateOf("대분류") } // 필터1의 초기값
            var filter2Selected by remember { mutableStateOf("소분류") } // 필터2의 초기값

            // 필터 버튼
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                FilterButton2(
                    selectedOption = filter1Selected,
                    options = listOf("대분류", "a", "b", "c")
                ) {
                    filter1Selected = it
                    filter2Selected = "소분류"
                    viewModel.applyFilter(
                        BarterFilterDTO(
                            filter1Selected,
                            filter2Selected,
                            searchText,
                            currentPage
                        )
                    )
                }

                FilterButton2(
                    selectedOption = filter2Selected,
                    options = when (filter1Selected) {
                        "a" -> listOf("소분류", "a-1", "a-2", "a-3")
                        "b" -> listOf("소분류", "b-1", "b-2", "b-3")
                        "c" -> listOf("소분류", "c-1", "c-2", "c-3")
                        else -> listOf("소분류")
                    }
                ) {
                    filter2Selected = it
                    viewModel.applyFilter(
                        BarterFilterDTO(
                            filter1Selected,
                            filter2Selected,
                            searchText,
                            currentPage
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // BarterItem 넣기
            barterItems.forEach { item ->
                BarterItem(
                    image = item.gifticonDataImageName,
                    name = item.giftconName,
                    gifticonTime = item.gifticonEndDate,
                    barterTime = item.barterEndDate,
                    popular = item.popular,
                    preper = item.preper,
                    title = item.barterName,
                    onItemClick = {
                        navController.navigate("BarterDetailPage/${item.barterIdx}")
                    }

                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))

            // 페이지네이션 컨트롤 AuctionPage에서 정의된거 사용
            PaginationControls(
                totalItems = barterItems.size,
                currentPage = currentPage,
                itemsPerPage = itemsPerPage
            ) { newPage ->
                currentPage = newPage
                viewModel.changePage(newPage)  // 페이지 변경 시 데이터 받기
            }
        }
        // 등록하기
        FloatingActionButton(
            onClick = { navController.navigate("BarterCreatePage") },
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterButton2(
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(116.dp)
            .height(28.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(5.dp))
            .clickable { expanded = true }
            .padding(horizontal = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = selectedOption,modifier = Modifier.weight(1f), fontSize = 16.sp)
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Arrow Drop Down")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest  = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, fontSize = 16.sp) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}



@Composable
fun BarterItem(
    image: String,
    name: String,
    gifticonTime: String,
    barterTime: String,
    popular: String,
    preper: String,
    title: String,
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
        }

        // 10% 이름 및 유효기간
        Row(
            modifier = Modifier.weight(0.1f).fillMaxWidth().padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(name, fontSize = 20.sp)
            Text("유효기간: $gifticonTime")
        }

        // 구분 줄
        Divider(color = Color.Gray, modifier = Modifier.padding(horizontal = 12.dp))

        // 5% 경매기간
        Text("경매기간: $barterTime", modifier = Modifier
            .weight(0.1f)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
        )

        // 15% 박스1
        Box(
            modifier = Modifier.weight(0.15f).padding(horizontal = 12.dp)
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
                    Text("대상품목: $preper", modifier = Modifier.weight(0.4f))
                }
            }
        }
    }
}