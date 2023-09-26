package com.example.project

import androidx.biometric.BiometricPrompt
import com.example.project.viewmodels.BiometricViewModel
import com.example.project.viewmodels.AuthenticationState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mrhwsn.composelock.ComposeLock
import com.mrhwsn.composelock.ComposeLockCallback
import com.mrhwsn.composelock.Dot

@Composable
fun LoginPage(navController: NavHostController) {
    val viewModel: BiometricViewModel = hiltViewModel()
    val authState by viewModel.authenticationState.collectAsState()
    val context = LocalContext.current
    val fragmentActivity = context as? FragmentActivity

    when (authState) {
        is AuthenticationState.SUCCESS -> {
            navController.navigate("Home")
        }
        is AuthenticationState.ERROR -> {
            val errorMessage = (authState as AuthenticationState.ERROR).message
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
        PatternAuthentication(viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        TemporaryNavigationButtons(navController)
        Spacer(modifier = Modifier.height(16.dp))
        BiometricAuthenticationButton(fragmentActivity, viewModel)
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
    Button(onClick = { navController.navigate("Home") }) {
        Text("임시 로그인")
    }
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = { navController.navigate("SignUp") }) {
        Text("회원가입임시")
    }
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = { navController.navigate("TextloginPage") }) {
        Text("텍스트로그인임시")
    }
}

@Composable
fun BiometricAuthenticationButton(fragmentActivity: FragmentActivity?, viewModel: BiometricViewModel) {
    val biometricPrompt = rememberBiometricPrompt(fragmentActivity, viewModel)

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Title")
        .setSubtitle("Subtitle")
        .setDescription("Description")
        .setNegativeButtonText("Cancel")
        .build()

    if (biometricPrompt != null) { // Null check 추가
        Button(onClick = { biometricPrompt.authenticate(promptInfo) }) {
            Text("지문인식 임시 세팅")
        }
    }
}

@Composable
fun rememberBiometricPrompt(
    fragmentActivity: FragmentActivity?,
    viewModel: BiometricViewModel
): BiometricPrompt? {
    val context = LocalContext.current

    val authenticationCallback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            viewModel.setAuthenticationState(AuthenticationState.ERROR(errString.toString()))
        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            viewModel.setAuthenticationState(AuthenticationState.SUCCESS)
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
                Text(text = "Authentication Error")
            },
            text = {
                Text(text = message)
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