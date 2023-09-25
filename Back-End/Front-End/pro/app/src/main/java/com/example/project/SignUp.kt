package com.example.project

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.project.reuse_component.CustomTextField
import com.example.project.reuse_component.CustomTextFieldWithButton
import com.example.project.ui.theme.BrandColor1

@Composable
fun SignUpPage(navController: NavHostController) {
    var userId by remember { mutableStateOf(TextFieldValue("")) }
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var nickname by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var checkPhoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var checkPassword by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var account by remember { mutableStateOf(TextFieldValue("")) }
    var accountBank by remember { mutableStateOf(TextFieldValue("")) }

    // 스크롤
    val scrollState = rememberScrollState()
    var checkMarkId = remember { mutableStateOf(false) }
    var checkMarkNickname = remember { mutableStateOf(false) }
    var checkMarkPhone = remember { mutableStateOf(false) }
    var checkMarkEmail = remember { mutableStateOf(false) }
    // 인증번호 TextField의 표시 여부를 관리하는 상태
    var showCheckPhoneNumber = remember { mutableStateOf(false) }

    // 이메일 검증
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // 비밀번호 검증
    fun isValidPassword(password: String): Boolean {
        val specialCharCount = password.count { it in "!@#$%^&*()-_=+|<>?/" }
        return password.length >= 8 && specialCharCount >= 2
    }

    //유효성 검사 상태들
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var checkPasswordError by remember { mutableStateOf<String?>(null) }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val shape = RoundedCornerShape(8.dp)
        Box(
            modifier = Modifier
                .size(350.dp, 700.dp)
//                .border(BorderStroke(0.dp, BrandColor1), shape = RoundedCornerShape(16.dp))
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                // 로고용 이미지
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(500.dp, 120.dp)
                )
                Text(text = "회원가입", fontSize = 27.sp, fontWeight = FontWeight.Bold)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.verticalScroll(scrollState)
                ) {
                    CustomTextField(label = "이름", value = name, onValueChange = { name = it })

                    CustomTextFieldWithButton(
                        label = "아이디",
                        buttonLabel = "중복확인",
                        value = userId,
                        onValueChange = { userId = it },
                        onButtonClick = { checkMarkId.value = true },
                        showIcon = checkMarkId.value
                    )
                    CustomTextField(
                        label = "비밀번호", value = password, onValueChange = {
                            password = it
                            passwordError =
                                if (isValidPassword(it.text)) null else "비밀번호는 8자리 이상\n특수 문자 2개를 포함해야 합니다."
                        }, visualTransformation = PasswordVisualTransformation(),
                        // 비밀번호 필드 아래에 오류 메시지 표시
                        error = passwordError
                    )

                    CustomTextField(
                        label = "비밀번호 확인", value = checkPassword, onValueChange = {
                            checkPassword = it
                            checkPasswordError =
                                if (it.text == password.text) null else "비밀번호가 일치하지 않습니다."
                        }, visualTransformation = PasswordVisualTransformation(),
                        // 비밀번호 확인 필드 아래에 오류 메시지 표시
                        error = checkPasswordError
                    )
                    CustomTextFieldWithButton(
                        label = "닉네임",
                        buttonLabel = "중복확인",
                        value = nickname,
                        onValueChange = { nickname = it },
                        onButtonClick = { checkMarkNickname.value = true },
                        showIcon = checkMarkNickname.value
                    )
                    CustomTextFieldWithButton(
                        label = "전화번호",
                        buttonLabel = "본인인증",
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        onButtonClick = { showCheckPhoneNumber.value = true },
                        showIcon = checkMarkPhone.value
                    )
                    if (showCheckPhoneNumber.value) {
                        CustomTextFieldWithButton(
                            label = "인증번호",
                            buttonLabel = "인증하기",
                            value = checkPhoneNumber,
                            onValueChange = { checkPhoneNumber = it },
                            onButtonClick = { checkMarkPhone.value = true },
                        )
                    }


                    CustomTextField(
                        label = "이메일", value = email, onValueChange = {
                            email = it
                            emailError = if (isValidEmail(it.text)) null else "올바른 이메일 주소를 입력해주세요."
                        },
                        // 이메일 필드 아래에 오류 메시지 표시
                        error = emailError, showIcon = checkMarkEmail.value
                    )

                    Button(
                        onClick = { checkMarkEmail.value = true },
                        Modifier
                            .size(120.dp, 40.dp)
                            .fillMaxHeight()
                            .clip(shape),
                        colors = ButtonDefaults.buttonColors(BrandColor1)
                    ) {
                        Text(
                            text = "중복확인", fontWeight = FontWeight.Bold
                        )
                    }
                    CustomTextField(label = "계좌은행",
                        value = account,
                        onValueChange = { account = it })

                    CustomTextField(label = "계좌번호",
                        value = accountBank,
                        onValueChange = { accountBank = it })

                    Button(
                        onClick = { navController.navigate("TextLoginPage") },
                        Modifier.size(120.dp, 40.dp),
                        colors = ButtonDefaults.buttonColors(BrandColor1)
                    ) {
                        Text("회원 가입", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun BankSelecter() {


}
