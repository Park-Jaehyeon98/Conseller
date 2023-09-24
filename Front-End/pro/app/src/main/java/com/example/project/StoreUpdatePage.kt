package com.example.project

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.StoreViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun StoreUpdatePage(navController: NavHostController, index: String?) {
    val viewModel: StoreViewModel = hiltViewModel()
    val storeItems by viewModel.storeItems.collectAsState()
    val storeDetail by viewModel.storeDetail.collectAsState()
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    val selectedStoreItem = storeItems?.find { storeItem -> storeItem.storeIdx == index?.toLong() }

    var postContent by remember { mutableStateOf(storeDetail?.postContent ?: "") }
    var storePrice by remember { mutableStateOf(selectedStoreItem?.storePrice ?: 0) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showUpdateConfirmDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                val imagePainter =
                    rememberAsyncImagePainter(model = selectedStoreItem?.gifticonDataImageName)
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop,
                )
            }

            Text("판매가", modifier = Modifier.padding(bottom = 8.dp), fontSize = 20.sp)
            OutlinedTextField(
                value = storePrice.toString(),
                onValueChange = { newValue ->
                    val pureValue = newValue.filter { char -> char.isDigit() }
                    if (pureValue.isEmpty()) {
                        storePrice = 0
                    } else if (pureValue.all { char -> char.isDigit() }) {
                        val potentialValue = pureValue.toLong()
                        if (potentialValue <= 1_000_000_000) {
                            storePrice = potentialValue.toInt()
                        } else {
                            storePrice = 1_000_000_000
                        }
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
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
                onValueChange = { newText ->
                    postContent = newText
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(200.dp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                })
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                SelectButton(
                    text = "수정하기",
                    onClick = { showUpdateConfirmDialog = true },
                    modifier = Modifier
                        .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                SelectButton(
                    text = "삭제하기",
                    onClick = { showDeleteDialog = true },
                    modifier = Modifier
                        .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
                )
            }

            if (showUpdateConfirmDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showUpdateConfirmDialog = false
                    },
                    title = {
                        Text(text = "게시글 수정")
                    },
                    text = {
                        Text("수정하기겠습니까?", fontSize = 18.sp)
                    },
                    dismissButton = {
                        SelectButton(
                            text = "네",
                            onClick = {
                                viewModel.updateStoreItem(index!!.toLong(), "123", postContent)
                                navController.navigate("StoreDetailPage/${index}")
                                showUpdateConfirmDialog = false
                            }
                        )
                    },
                    confirmButton = {
                        SelectButton(
                            text = "아니오",
                            onClick = { showUpdateConfirmDialog = false }
                        )
                    }
                )
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
                        Text("정말 삭제하시겠습니까?", fontSize = 18.sp)
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