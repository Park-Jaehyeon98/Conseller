package com.example.project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.project.viewmodels.AuctionViewModel
import com.example.project.viewmodels.MygifticonViewModel
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AuctionCreateDetailPage(navController: NavHostController, selectedItemIndex: String?) {
    val myGifticonViewModel: MygifticonViewModel = hiltViewModel()
    val auctionViewModel: AuctionViewModel = hiltViewModel()
    val gifticonItems by myGifticonViewModel.gifticonItems.collectAsState()
    val navigateToIdx by auctionViewModel.navigateToAuctionDetail.collectAsState() // 등록시 idx받기
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    val selectedGifticon = gifticonItems?.find { it.gifticonIdx == selectedItemIndex!!.toLong() }

    // 상한가, 하한가, 게시글 내용을 위한 상태값
    var upperLimit by remember { mutableStateOf(0) }
    var lowerLimit by remember { mutableStateOf(0) }
    var postContent by remember { mutableStateOf("") }

    // 1000단위 , 찍기용
    val numberFormat = DecimalFormat("#,###")

    fun formattedNumber(input: String): String {
        return try {
            val parsedNumber = input.replace(",", "").toLong()
            numberFormat.format(parsedNumber)
        } catch (e: Exception) {
            input
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // 내용 입력 부분
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "2. 입력값을 채워주세요",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            selectedGifticon?.let {
                Text(text = "Selected gifticon image URL: ${it.gifticonAllImagName}") // 예시용. 실제로는 Image 컴포넌트로 이미지를 표시해야 함
            }
            Icon(Icons.Default.Home, contentDescription = "Image", Modifier.size(120.dp)) // 임시 사진

            Spacer(modifier = Modifier.height(16.dp))

            Text("상한가", modifier = Modifier.padding(bottom = 8.dp), fontSize = 20.sp)
            OutlinedTextField(
                value = formattedNumber(upperLimit.toString()),
                onValueChange = { newValue ->
                    val pureValue = newValue.filter { it.isDigit() }
                    // 숫자만 입력되도록 체크
                    if (pureValue.isEmpty()) {
                        upperLimit = 0
                    } else if (pureValue.all { it.isDigit() }) {
                        val potentialValue = pureValue.toLong()
                        if (potentialValue <= 1_000_000_000) {  // 10억으로 제한
                            upperLimit = potentialValue.toInt()
                        } else {
                            upperLimit = 1_000_000_000
                        }
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide() // 키보드 숨기기
                }),
                placeholder = {
                    Text(text = "원단위로 숫자만 입력해주세요", color = Color.Gray)
                },
                trailingIcon = {
                    Text(text = "원")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("하한가", modifier = Modifier.padding(bottom = 8.dp), fontSize = 20.sp)
            OutlinedTextField(
                value = formattedNumber(lowerLimit.toString()),
                onValueChange = { newValue ->
                    val pureValue = newValue.filter { it.isDigit() }
                    // 숫자만 입력되도록 체크
                    if (pureValue.isEmpty()) {
                        lowerLimit = 0
                    } else if (pureValue.all { it.isDigit() }) {
                        val potentialValue = pureValue.toLong()
                        if (potentialValue <= 1_000_000_000) {  // 10억으로 제한
                            lowerLimit = potentialValue.toInt()
                        } else {
                            lowerLimit = 1_000_000_000
                        }
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide() // 키보드 숨기기
                }),
                placeholder = {
                    Text(text = "원단위로 숫자만 입력해주세요", color = Color.Gray)
                },
                trailingIcon = {
                    Text(text = "원")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

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
        }
        // 이전, 등록 버튼 부분
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = {
                    val selectedIndex: Long = selectedGifticon?.gifticonIdx ?: -1L
                    auctionViewModel.registerAuctionItem(upperLimit, lowerLimit, postContent, selectedIndex)
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            ) {
                Text("등록")
            }

            Spacer(modifier = Modifier.width(24.dp))

            Button(
                onClick = {
                    navController.navigate("AuctionCreatePage")
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            ) {
                Text("이전")
            }

            LaunchedEffect(navigateToIdx) {
                navigateToIdx?.let { auctionIdx ->
                    navController.navigate("AuctionDetailPage/${auctionIdx}")
                    auctionViewModel.resetNavigation()
                }
            }
        }
    }
}