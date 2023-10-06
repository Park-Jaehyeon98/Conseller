package com.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.project.api.findIdResponse
import com.example.project.api.findPwRequest
import com.example.project.reuse_component.CustomTextField
import com.example.project.reuse_component.EmailTextFieldWithDomain
import com.example.project.viewmodels.SignupViewModel

@Composable
fun FindPwPage(navController: NavHostController) {
    val viewModel: SignupViewModel = hiltViewModel()
    var userId by remember { mutableStateOf(TextFieldValue()) }
    var userEmail by remember { mutableStateOf("") }
    var emailPart by remember { mutableStateOf("") }
    var Domain by remember { mutableStateOf("gmail.com") }
    var emailError by remember { mutableStateOf<String?>(null) }
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    val request=findPwRequest(
        userId = userId.text,
        userEmail = userEmail
    )
    val findPwResponse by viewModel.findPw.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    LaunchedEffect(findPwResponse) {
        if(!findPwResponse){
            showDialog = true
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("비밀번호 찾기", fontSize = 32.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(label = "아이디", value = userId, onValueChange = { userId = it })

            EmailTextFieldWithDomain(
                label = "이메일",
                emailValue = emailPart,
                onValueChange = { newEmailPart ->
                    emailPart = newEmailPart
                    userEmail = "$newEmailPart@$Domain"
                    emailError = if (isValidEmail(userEmail)) null else "이메일 형식이 틀립니다."
                },
                error = emailError,
                selectedDomain = Domain,
                onDomainSelected = { newDomain ->
                    Domain = newDomain
                    userEmail = "$emailPart@$Domain"
                    emailError = if (isValidEmail(userEmail)) null else "이메일 형식이 틀립니다."
                },
                showIcon = false,
            )

            Spacer(modifier = Modifier.height(16.dp))

            NormalButton(label = "비밀번호 찾기", buttonSize = 50, textSize = 20, onClick = {viewModel.findUserPw(request)})
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        // 다이얼로그 외부를 클릭하여 닫기를 원할 때의 동작
                        showDialog = false
                    },
                    title = { Text("임시 비밀번호 전송") },
                    text = { Text("귀하의 이메일로 \n 임시 비밀번호가 발송되었습니다..") },
                    confirmButton = {
                        Button(onClick = {
                            // 확인 버튼 클릭 시의 동작
                            showDialog = false
                            navController.navigate("TextLoginPage")
                        }) {
                            Text("확인")
                        }
                    }
                )
            }
        }
    }
}
