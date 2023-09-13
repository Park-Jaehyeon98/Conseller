package com.example.project

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.project.viewmodels.BarterViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BarterUpdatePage(index: String?, navController: NavHostController) {
    val viewModel: BarterViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    // 게시글 제목 및 내용을 위한 상태값
    var postTitle by remember { mutableStateOf("") }
    var postContent by remember { mutableStateOf("") }
    var showDeleteDialog by remember { mutableStateOf(false) }

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
                options = listOf("대분류", "a", "b", "c"),
            ) { selectedOption ->
                filter1Selected = selectedOption
                filter2Selected = "소분류"
            }

            Spacer(modifier = Modifier.width(24.dp))

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
            Button(
                onClick = {
                    viewModel.updateBarterItem(
                        index!!.toLong(),
                        filter1Selected,
                        filter2Selected,
                        postTitle,
                        postContent,
                        "123"
                    )
                    navController.navigate("BarterDetailPage/${index}")
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            ) {
                Text("수정하기")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    showDeleteDialog = true
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp)
            ) {
                Text(text = "삭제하기")
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
                confirmButton = {
                    Button(onClick = {
                        showDeleteDialog = false
                    }) {
                        Text("아니오")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        viewModel.deleteBarterItem(index!!.toLong())
                        navController.navigate("BarterPage")
                        showDeleteDialog = false
                    }) {
                        Text("네")
                    }
                }
            )
        }
    }
}