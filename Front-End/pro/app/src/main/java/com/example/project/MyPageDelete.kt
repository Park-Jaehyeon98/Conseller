package com.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.project.reuse_component.CustomTextField
import com.example.project.viewmodels.MyPageViewModel

@Composable
fun MypageDelete(navController: NavHostController) {
    val viewModel: MyPageViewModel = hiltViewModel()
    // 스테이트 변수들을 정의합니다.
    var userPassword by remember { mutableStateOf(TextFieldValue("")) }
    var deleteWord by remember { mutableStateOf(TextFieldValue("")) }
    var isDialogVisible by remember { mutableStateOf(false) }
    val deleteUserResponse by viewModel.deleteUserResponse.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 레이아웃을 구성합니다.
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 설명 텍스트
            Text(text = "회원탈퇴를 진행하시겠습니까?", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "회원탈퇴를 하게 되면 콘셀러의 서비스를 더 이상 이용할 수 없습니다.", fontSize = 16.sp, color = Color.Red, textAlign = TextAlign.Center)


            // 탈퇴 문구 입력 필드
            CustomTextField(
                value = deleteWord,
                onValueChange = { deleteWord = it },
                label = "탈퇴문구 \"콘셀러 탈퇴\"를 적어주세요",
            )

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 탈퇴 버튼
                Button(onClick = {
                    isDialogVisible = true
                }) {
                    Text("회원탈퇴")
                }

                // 취소 버튼
                Button(onClick = {
                    // 이전 페이지로 돌아갑니다.
                    navController.popBackStack()
                }) {
                    Text("취소")
                }
            }

            // 회원탈퇴 확인 대화상자
            if (isDialogVisible) {
                AlertDialog(
                    onDismissRequest = { isDialogVisible = false },
                    title = { Text("회원탈퇴") },
                    text = { Text("정말로 탈퇴하시겠습니까?") },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.userDelete()
                            if(deleteUserResponse){
                                navController.navigate("TextLoginPage")
                            }else{
                                // 예외처리
                            }
                        }) {
                            Text("확인")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            // 대화상자를 닫습니다.
                            isDialogVisible = false
                        }) {
                            Text("취소")
                        }
                    }
                )
            }
        }
    }
}
