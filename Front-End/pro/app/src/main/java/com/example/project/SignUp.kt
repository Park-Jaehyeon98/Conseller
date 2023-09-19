package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.project.api.RegistRequest
import com.example.project.reuse_component.CustomDropdown
import com.example.project.reuse_component.CustomTextField
import com.example.project.reuse_component.CustomTextFieldWithButton
import com.example.project.reuse_component.EmailTextFieldWithDomain
import com.example.project.ui.theme.BlueChecker
import com.example.project.ui.theme.BrandColor1
import com.example.project.viewmodels.SignupViewModel

@Composable
fun SignUpPage(navController: NavHostController) {
    val viewModel: SignupViewModel = hiltViewModel()
    val signUpResult by viewModel.signupresponse.collectAsState()

    // 유효성 검사
    val checkIdResult by viewModel.checkId.collectAsState()
    val checkEmailResult by viewModel.checkEmail.collectAsState()
    val checkPhoneNumberResult by viewModel.checkPhoneNumber.collectAsState()
    val checkNickNameResult by viewModel.checkNickname.collectAsState()

    // 회원가입 request 값들
    var userId by remember { mutableStateOf(TextFieldValue("")) }
    var gender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf(TextFieldValue("")) }
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var nickname by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var checkPhoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var checkPassword by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf("") }
    var account by remember { mutableStateOf("은행명을 선택하세요") }
    var accountBank by remember { mutableStateOf(TextFieldValue("")) }

    val registClick = {
        val request = RegistRequest(
            userId = userId.text,
            userGender=gender,
            userAge=age.text.toInt(),
            userAccount = account,
            userEmail = email,
            userPassword = password.text,
            userAccountBank = accountBank.text,
            userNickname = nickname.text,
            userPhoneNumber = phoneNumber.text
        )
        viewModel.registerUser(request)
    }

    // 이메일 명
    var emailPart by remember { mutableStateOf("") }
    // 이메일 도메인
    var Domain by remember { mutableStateOf("gmail.com") }

    // 스크롤
    val scrollState = rememberScrollState()

    val checkMarkId = remember { mutableStateOf(false) }
    val checkMarkNickname = remember { mutableStateOf(false) }
    val checkMarkPhone = remember { mutableStateOf(false) }
    val checkMarkEmail = remember { mutableStateOf(false) }

    // 인증번호 TextField의 표시 여부를 관리하는 상태
    val showCheckPhoneNumber = remember { mutableStateOf(false) }

    // 이메일 검증
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // 비밀번호 검증
    fun isValidPassword(password: String): Boolean {
        val specialCharCount = password.count { it in "!@#$%^&*()-_=+|<>?/" }
        val digitCount = password.count { it.isDigit() }
        val letterCount = password.count { it.isLetter() }

        return password.length >= 8 && specialCharCount >= 1 && digitCount >= 1 && letterCount >= 1
    }

    //닉네임 검증
    fun isValidNickName(nickName: String): Boolean {
        return 1 <= nickName.length && nickName.length <= 45
    }

    //이름 검증 
    fun isValidName(name: String): Boolean {
        return 1 <= name.length && name.length <= 45
    }


    //유효성 검사 상태들
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var checkPasswordError by remember { mutableStateOf<String?>(null) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var nickNameError by remember { mutableStateOf<String?>(null) }


    // 로그인 결과에 따른 값 변화
    LaunchedEffect(key1 = signUpResult) {
        if (signUpResult.status == 1) {
            navController.navigate("Home") {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        } else if (signUpResult.status == 0) {
            // signup 실패 알람
        }
    }
    //인증마크

    LaunchedEffect(checkIdResult, checkEmailResult, checkPhoneNumberResult, checkNickNameResult) {
        if (checkIdResult.status == 1) {
            checkMarkId.value = true
        } else if (checkEmailResult.status == 1) {
            checkMarkEmail.value = true
        } else if (checkPhoneNumberResult.status == 1) {
            checkMarkPhone.value = true
        } else if (checkNickNameResult.status == 1) {
            checkMarkNickname.value = true
        } else {
            // 유효성 검사 실패 알람
        }
    }


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
                    CustomTextField(label = "이름", value = name, onValueChange = {
                        name = it
                        nameError = if (isValidName(it.text)) null else "이름은 1글자 이상 45글자 이하여야 합니다."
                    }, error = nameError, showIcon = isValidName(name.text))
                    GenderSelection(label="성별",gender = gender, onGenderSelected = { selectedGender ->
                        gender = selectedGender
                    })
                    CustomTextField(label = "나이", value = age, onValueChange = {
                            newValue ->
                        if (newValue.text.all { it.isDigit() }) {
                            age = newValue
                        }
                    })

                    CustomTextFieldWithButton(
                        label = "아이디",
                        buttonLabel = "중복확인",
                        value = userId,
                        onValueChange = { userId = it },
                        onButtonClick = { viewModel.checkDuplicateId(userId.text) },
                        showIcon = checkMarkId.value
                    )
                    CustomTextField(
                        label = "비밀번호", value = password,
                        onValueChange = {
                            password = it
                            passwordError =
                                if (isValidPassword(it.text)) null else "비밀번호는 8자리 이상\n특수 문자, 영문, 숫자를 1개이상 포함해야 합니다."
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        // 비밀번호 필드 아래에 오류 메시지 표시
                        error = passwordError,
                        showIcon = isValidPassword(password.text) && checkPassword.text == password.text,
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
                        onValueChange = {
                            nickname = it
                            nickNameError =
                                if (isValidNickName(it.text)) null else "닉네임은 1글자 이상 45글자 이하여야 합니다."
                        },
                        onButtonClick = { viewModel.checkDuplicateNickname(nickname.text) },
                        showIcon = checkMarkNickname.value,
                        error = nickNameError
                    )
                    CustomTextFieldWithButton(
                        label = "전화번호",
                        buttonLabel = "본인인증",
                        value = phoneNumber,
                        onValueChange = { newValue ->
                            if (newValue.text.all { it.isDigit() }) {
                                phoneNumber = newValue
                            }
                        },
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

                    EmailTextFieldWithDomain(
                        label = "이메일",
                        emailValue = emailPart,
                        onValueChange = { newEmailPart ->
                            emailPart = newEmailPart
                            email = "$newEmailPart@$Domain"
                            emailError = if (isValidEmail(email)) null else "이메일 형식이 틀립니다."
                        },
                        error = emailError,
                        selectedDomain = Domain,
                        onDomainSelected = { newDomain ->
                            Domain = newDomain
                            email = "$emailPart@$Domain"
                            emailError = if (isValidEmail(email)) null else "이메일 형식이 틀립니다."
                        },
                        showIcon = checkMarkEmail.value,

                        )

                    Button(
                        onClick = { viewModel.checkDuplicateEmail(email) },
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
                    CustomDropdown(selectedBank = account, onBankSelected = { bank ->
                        account = bank

                    })
                    CustomTextField(
                        label = "계좌번호",
                        value = accountBank,
                        onValueChange = { newValue ->
                            if (newValue.text.all { it.isDigit() }) {
                                accountBank = newValue
                            }
                        },
                    )
                    Button(
                        onClick = { registClick },
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
fun GenderSelection(label:String,gender: String, onGenderSelected: (String) -> Unit) {
    Column(modifier=Modifier.fillMaxWidth()) {
        Text(text = label, fontSize = 19.sp, fontWeight = FontWeight.Bold)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(16.dp)
        ) {
            Button(
                onClick = {
                    onGenderSelected("M")
                }, colors = ButtonDefaults.buttonColors(
                    if (gender == "M") BlueChecker else BrandColor1
                ),
                modifier = Modifier.weight(5f)
            ) {
                Text("남성", fontSize = 22.sp)
            }

            Button(
                onClick = {
                    onGenderSelected("F")
                }, colors = ButtonDefaults.buttonColors(
                    if (gender == "F")  Color(0xFFEC9393) else BrandColor1
                ),
                modifier = Modifier.weight(5f)
            ) {
                Text("여성", fontSize = 22.sp)
            }
        }
    }
}

