package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.project.viewmodels.AuthenticationState
import com.example.project.viewmodels.BiometricViewModel
import com.mrhwsn.composelock.ComposeLock
import com.mrhwsn.composelock.ComposeLockCallback
import com.mrhwsn.composelock.Dot

@Composable
fun MakePatternPage(navController: NavHostController) {
    val viewModel: BiometricViewModel = hiltViewModel()
    val firstPattern = remember { mutableStateOf<String?>(null) }
    val authenticationState = viewModel.authenticationState.collectAsState().value
    val showAlert = remember { mutableStateOf(false) }

    if (authenticationState is AuthenticationState.SUCCESS) {
        showAlert.value = true  // 성공 상태가 되면 알림창을 표시
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.align(Alignment.CenterHorizontally).scale(0.8f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            when (val state = viewModel.patternState.collectAsState().value) {
                is PatternState.Confirm -> {
                    StyledText("패턴을 1번 더 똑같이 그려주세요.")
                }
                is PatternState.Mismatch -> {
                    StyledText("패턴이 일치하지 않습니다.\n 처음부터 다시 시도하세요.")
                    firstPattern.value = null
                }
                is PatternState.Matched -> {
                    StyledText("패턴 등록 완료!")
                }
                else -> {
                    StyledText("패턴을 그려서 등록하세요.")
                }
            }

            when (authenticationState) {
                is AuthenticationState.SUCCESS -> {
                    StyledText("패턴 등록 성공!")
                }
                is AuthenticationState.ERROR -> {
                    StyledText("패턴 등록 실패: ${authenticationState.message}")
                }
                is AuthenticationState.WAIT -> {
                    // 그리기 전
                }
                else -> {
                    StyledText("패턴 등록 오류!")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            ComposeLock(
                modifier = Modifier
                    .width(400.dp)
                    .height(400.dp),
                dimension = 3,
                sensitivity = 100f,
                dotsColor = Color.Black,
                dotsSize = 20f,
                linesColor = Color.Black,
                linesStroke = 30f,
                animationDuration = 200,
                animationDelay = 100,
                callback = object : ComposeLockCallback {
                    override fun onStart(dot: Dot) {}
                    override fun onDotConnected(dot: Dot) {}
                    override fun onResult(result: List<Dot>) {
                        val patternString = result.joinToString("-") { it.id.toString() }
                        if (firstPattern.value == null) {
                            firstPattern.value = patternString
                            viewModel.setPatternState(PatternState.Confirm)
                        } else if (firstPattern.value == patternString) {
                            viewModel.savePattern(patternString)
                        } else {
                            viewModel.setPatternState(PatternState.Mismatch)
                        }
                    }
                }
            )
            if (showAlert.value) {
                AlertDialog(
                    onDismissRequest = {
                        showAlert.value = false
                        navController.navigate("home")  // 알림창 닫힐 때 홈으로 이동
                    },
                    title = { StyledText("알림") },
                    text = { StyledText("패턴 등록 성공!") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showAlert.value = false
                                navController.navigate("home")  // 확인 버튼 클릭 시 홈으로 이동
                            }
                        ) {
                            Text("확인")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun StyledText(text: String) {
    Text(text, fontSize = 20.sp, fontWeight = FontWeight.Bold)
}

sealed class PatternState {
    object Initial : PatternState()
    object Confirm : PatternState()
    object Mismatch : PatternState()
    object Matched : PatternState()
}