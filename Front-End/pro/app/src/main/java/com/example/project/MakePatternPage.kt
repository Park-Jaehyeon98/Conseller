package com.example.project

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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

    when (val state = viewModel.patternState.collectAsState().value) {
        is PatternState.Confirm -> {
            Text("패턴을 다시 그려주세요.")
        }
        is PatternState.Mismatch -> {
            Text("패턴이 일치하지 않습니다. 처음부터 다시 시도하세요.")
            firstPattern.value = null
        }
        is PatternState.Matched -> {
            Text("패턴 등록 완료!")
        }
        else -> {
            Text("패턴을 그려서 등록하세요.")
        }
    }

    when (authenticationState) {
        is AuthenticationState.SUCCESS -> {
            Text("패턴 등록 성공!")
        }
        is AuthenticationState.FAILURE -> {
            Text("패턴 등록 실패!")
        }
        is AuthenticationState.ERROR -> {
            Text("패턴 등록 오류: ${authenticationState.message}")
        }
        else -> {}
    }

    ComposeLock(
        modifier = Modifier
            .width(400.dp)
            .height(400.dp)
            .fillMaxWidth(),
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
}

sealed class PatternState {
    object Initial : PatternState()
    object Confirm : PatternState()
    object Mismatch : PatternState()
    object Matched : PatternState()
}