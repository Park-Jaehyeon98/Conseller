package com.example.project

import FormattedDateText
import SelectButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.project.viewmodels.StoreViewModel

@Composable
fun StoreDetailPage(navController: NavHostController, index: String?) {
    val viewModel: StoreViewModel = hiltViewModel()
    val storeItems by viewModel.storeItems.collectAsState()
    val storeDetail by viewModel.storeDetail.collectAsState()
    val scrollState = rememberScrollState()
    val userIdFromPreference = viewModel.getUserIdFromPreference()

    var selectedItemIndex by remember { mutableStateOf(userIdFromPreference) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    val currentItem = storeItems.find { it.storeIdx.toString() == index }

    LaunchedEffect(key1 = index) {
        index?.toLongOrNull()?.let {
            viewModel.fetchStoreDetail(it)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(scrollState)
        ) {
            currentItem?.let {
                Spacer(modifier = Modifier.height(16.dp))

                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(it.gifticonDataImageName)
                        .crossfade(true)
                        .build()
                )

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    val imagePainter = rememberAsyncImagePainter(model = it.gifticonDataImageName)
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                val textStyle = Modifier.padding(vertical = 4.dp)

                Text("판매가 : ${it.storePrice} 원", modifier = textStyle, fontSize = 18.sp)
                FormattedDateText(it.gifticonEndDate, modifier = textStyle, fontSize = 18.sp, fontWeight = FontWeight.Bold)

                storeDetail?.let {
                    Text("User: ${it.storeUserNickname}")
                    Text("User: ${it.postContent}")
                }
                Text("판매자 : ${storeDetail?.storeUserNickname}", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text("내용 : ${storeDetail?.postContent}", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (selectedItemIndex != storeDetail?.storeUserIdx && true/*개발용*/) {
                        SelectButton(
                            text = "구매하기",
                            onClick = { navController.navigate("StoreTradePage/${currentItem.storeIdx}") }
                        )
                    } else {
                        SelectButton(
                            text = "수정하기",
                            onClick = { navController.navigate("storeUpdate/${currentItem.storeIdx}") }
                        )

                        Spacer(modifier = Modifier.width(24.dp))

                        SelectButton(
                            text = "삭제하기",
                            onClick = { showDeleteDialog = true }
                        )
                    }
                }
            }

            if (showDeleteDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showDeleteDialog = false
                    },
                    title = {
                        Text(text = "게시글 삭제")
                    },
                    text = {
                        Text("정말 삭제하시겠습니까?")
                    },
                    dismissButton = {
                        SelectButton(
                            text = "네",
                            onClick = {
                                viewModel.deleteStoreItem(index!!.toLong())
                                navController.navigate("StorePage")
                                showDeleteDialog = false
                            }
                        )
                    },
                    confirmButton = {
                        SelectButton(
                            text = "아니오",
                            onClick = { showDeleteDialog = false }
                        )
                    }
                )
            }
        }
    }
}