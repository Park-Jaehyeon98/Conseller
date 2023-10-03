package com.example.project

import SelectButton
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import com.example.project.viewmodels.BiometricViewModel
import com.example.project.viewmodels.AuthenticationState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.project.viewmodels.FireBaseViewModel
import com.mrhwsn.composelock.ComposeLock
import com.mrhwsn.composelock.ComposeLockCallback
import com.mrhwsn.composelock.Dot

@Composable
fun LoginPage(navController: NavHostController) {
    val viewModel: BiometricViewModel = hiltViewModel()
    val fireBaseViewModel: FireBaseViewModel = hiltViewModel()
    val authenticationState by viewModel.authenticationState.collectAsState()
    val context = LocalContext.current
    val fragmentActivity = context as? FragmentActivity
    val biometricPrompt = rememberBiometricPrompt(fragmentActivity, viewModel)
    val currentBiometricPrompt = rememberUpdatedState(biometricPrompt)


    LaunchedEffect(Unit) {
        authenticateWithBiometrics(currentBiometricPrompt.value)
    }

    when (authenticationState) {
        is AuthenticationState.SUCCESS -> {
            fireBaseViewModel.getFirebaseToken()
            navController.navigate("Home")
        }
        is AuthenticationState.ERROR -> {
            val errorMessage = (authenticationState as AuthenticationState.ERROR).message
            ShowAlertDialog(message = errorMessage)
        }
        is AuthenticationState.FAILURE -> {
            ShowAlertDialog(message = "패턴이 일치하지 않습니다. 다시 시도해주세요.")
        }
        else -> {
            // 아무것도 하지 않거나 필요한 처리 수행
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.align(Alignment.CenterHorizontally).scale(0.8f)
        )
        Spacer(modifier = Modifier.height(16.dp))

        PatternAuthentication(viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        TemporaryNavigationButtons(navController)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun PatternAuthentication(viewModel: BiometricViewModel) {
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
            override fun onStart(dot: Dot) {

            }
            override fun onDotConnected(dot: Dot) {

            }
            override fun onResult(result: List<Dot>) {
                val patternString = result.joinToString("-") { it.id.toString() }
                viewModel.verifyPattern(patternString)
            }
        }
    )
}

@Composable
fun TemporaryNavigationButtons(navController: NavHostController) {

    SelectButton(
        text = "아이디로 로그인 하기",
        onClick = { navController.navigate("TextloginPage") }
    )

    Spacer(modifier = Modifier.height(16.dp))

    SelectButton(
        text = "회원가입임시",
        onClick = { navController.navigate("SignUp") }
    )
}

fun authenticateWithBiometrics(
    biometricPrompt: BiometricPrompt?
) {
    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Title")
        .setSubtitle("Subtitle")
        .setDescription("Description")
        .setNegativeButtonText("Cancel")
        .build()

    biometricPrompt?.authenticate(promptInfo)
}

@Composable
fun rememberBiometricPrompt(
    fragmentActivity: FragmentActivity?,
    viewModel: BiometricViewModel
): BiometricPrompt? {
    val context = LocalContext.current

    val authenticationCallback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            when (errorCode) {
                BiometricPrompt.ERROR_NEGATIVE_BUTTON,
                BiometricPrompt.ERROR_CANCELED,
                BiometricPrompt.ERROR_USER_CANCELED -> {
                    // 사용자가 인증을 취소한 경우
                    // 현재는 아무 동작도 하지 않습니다. 필요하다면 추가 동작을 여기에 구현.
                }
                else -> {
                    // 그 외의 에러들에 대한 처리
                    viewModel.setAuthenticationState(AuthenticationState.ERROR(errString.toString()))
                }
            }
        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            viewModel.authenticateWithBiometrics()
            // 성공시 토큰 호출하고 성공했다는 메세지를 해서 홈으로 보내기
        }

        override fun onAuthenticationFailed() {
            viewModel.setAuthenticationState(AuthenticationState.FAILURE)
        }
    }

    // fragmentActivity null check 추가
    return if (fragmentActivity != null) {
        BiometricPrompt(
            fragmentActivity,
            ContextCompat.getMainExecutor(context),
            authenticationCallback
        )
    } else {
        null
    }
}

// 실패 알람
@Composable
fun ShowAlertDialog(message: String) {

    val openDialog = remember(message) { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "패턴 인증 실패", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            },
            text = {
                Text(text = message, fontSize = 18.sp)
            },
            confirmButton = {
                Button(onClick = {
                    openDialog.value = false
                }) {
                    Text("OK")
                }
            }
        )
    }
}