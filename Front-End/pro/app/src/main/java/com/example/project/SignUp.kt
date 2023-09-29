package com.example.project

import SelectButton
import android.util.Log
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
import androidx.compose.material3.AlertDialog
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
    // 모델 결과
    val signUpResult by viewModel.signupresponse.collectAsState()

    val errorMessage by viewModel.error.collectAsState()
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
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var checkPassword by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf("") }
    var account by remember { mutableStateOf("은행명을 선택하세요") }
    var accountBank by remember { mutableStateOf(TextFieldValue("")) }

    // 알림
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    // 회원가입 request
    val request = RegistRequest(
        userId = userId.text,
        userGender = gender,
        userName = name.text,
        userAge = if (age.text.isNotBlank()) {
            Integer.parseInt(age.text)
        } else {
            // 기본값 또는 오류 처리
            0 // 예를 들어, 기본 나이를 0으로 설정하거나 오류 메시지를 표시
        },
        userAccount = accountBank.text,
        userEmail = email,
        userPassword = password.text,
        userAccountBank = account,
        userNickname = nickname.text,
        userPhoneNumber = phoneNumber.text
    )


    // 이메일 명 +  도메인
    var emailPart by remember { mutableStateOf("") }
    var Domain by remember { mutableStateOf("gmail.com") }

    // 스크롤
    val scrollState = rememberScrollState()

    // 유효성결과
    val checkMarkId = remember { mutableStateOf(false) }
    val checkMarkNickname = remember { mutableStateOf(false) }
    val checkMarkPhone = remember { mutableStateOf(false) }
    val checkMarkEmail = remember { mutableStateOf(false) }

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
    var idError by remember { mutableStateOf<String?>(null) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var nickNameError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }

    // signupmodal 용
    var Signstatus by remember { mutableStateOf(false) }

    // 로그인 결과에 따른 값 변화
    LaunchedEffect(signUpResult) {
        val checker = signUpResult
        Log.d("why", "Sibal${checker}")
        Log.d("why", "Sibal22${Signstatus}")
        if (checker == 1 && Signstatus) {
            dialogMessage = "회원가입 성공\n 패턴로그인을 추가하겠습니까?"
            showDialog = true
        }
        if (checker == 2 && Signstatus) {
            dialogMessage = "회원가입이 실패했습니다.\n 원인: ${errorMessage}"
            showDialog2 = true
            viewModel.initSignUpResult()
        }
    }
    //인증마크 관리 로직
    LaunchedEffect(checkIdResult, checkEmailResult, checkPhoneNumberResult, checkNickNameResult) {
        if (checkIdResult.status == 1) {
            checkMarkId.value = true
            idError = null
        } else if (checkIdResult.status == 0) {
            checkMarkId.value = false
            idError = "중복된 아이디입니다"

        }
        if (checkEmailResult.status == 1) {
            checkMarkEmail.value = true
            emailError = null
        } else if (checkEmailResult.status == 0) {
            checkMarkEmail.value = false
            emailError = "중복된 이메일입니다"

        }
        if (checkPhoneNumberResult.status == 1) {
            checkMarkPhone.value = true
            phoneError = null
        } else if (checkPhoneNumberResult.status == 0) {
            checkMarkPhone.value = false
            phoneError = "중복된 번호입니다"

        }
        if (checkNickNameResult.status == 1) {
            checkMarkNickname.value = true
            nickNameError = null
        } else if (checkNickNameResult.status == 0) {
            checkMarkNickname.value = false
            nickNameError = "중복된 닉네임입니다"

        }
    }

    fun validateUser(user: RegistRequest): Boolean {
        val errorMessage = StringBuilder()
        if (!isValidName(user.userName)) {
            errorMessage.append("이름 양식이 틀렸습니다.\n")
        }
        if (!isValidNickName(user.userNickname)) {
            errorMessage.append("닉네임 양식이 틀렸습니다.\n")
        }
        if (!isValidPassword(user.userPassword)) {
            errorMessage.append("비밀번호 양식이 틀렸습니다.\n")
        }

        if (!isValidEmail(user.userEmail)) {
            errorMessage.append("이메일 양식이 틀렸습니다.\n")
        }


        if (errorMessage.isNotEmpty()) {
           dialogMessage=errorMessage.toString()
            return false
        }

        return true
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val shape = RoundedCornerShape(8.dp)
        // 알림
        if (showDialog) {
            AlertDialog(onDismissRequest = {
                showDialog = false
            }, title = {
                Text("알림")
            }, text = {
                Text(dialogMessage)
            }, confirmButton = {
                SelectButton(
                    text = "아니요",
                    onClick = {
                        showDialog = false
                        navController.navigate("TextLoginPage")
                    }
                )
            },
                dismissButton = {
                    SelectButton(
                        text = "예",
                        onClick = {
                            showDialog = false
                            navController.navigate("MakePatternPage") {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
            })
        }
        if (showDialog2) {
            AlertDialog(onDismissRequest = {
                showDialog2 = false
            }, title = {
                Text("알림")
            }, text = {
                Text(dialogMessage)
            }, confirmButton = {
                Button(onClick = {
                    showDialog2 = false
                    Signstatus = false
                }) {
                    Text("확인")
                }

            })
        }
        Box(
            modifier = Modifier
                .size(350.dp, 700.dp)
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

                    // 이름
                    CustomTextField(label = "이름", value = name, onValueChange = {
                        name = it
                        nameError = if (isValidName(it.text)) null else "이름은 1글자 이상 45글자 이하여야 합니다."
                    }, error = nameError, showIcon = isValidName(name.text))

                    //성별
                    GenderSelection(label = "성별",
                        gender = gender,
                        onGenderSelected = { selectedGender ->
                            gender = selectedGender
                        })

                    //나이
                    CustomTextField(label = "나이", value = age, onValueChange = { newValue ->
                        if (newValue.text.all { it.isDigit() }) {
                            age = newValue
                        }
                    })

                    //아이디
                    CustomTextFieldWithButton(label = "아이디",
                        buttonLabel = "중복확인",
                        value = userId,
                        onValueChange = {
                            userId = it
                        },
                        onButtonClick = {
                            viewModel.checkDuplicateId(userId.text)
                        },
                        showIcon = checkMarkId.value,
                        error = idError
                    )

                    //비밀번호
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

                    //비밀번호 체크
                    CustomTextField(
                        label = "비밀번호 확인", value = checkPassword, onValueChange = {
                            checkPassword = it
                            checkPasswordError =
                                if (it.text == password.text) null else "비밀번호가 일치하지 않습니다."
                        }, visualTransformation = PasswordVisualTransformation(),
                        // 비밀번호 확인 필드 아래에 오류 메시지 표시
                        error = checkPasswordError
                    )

                    //닉네임
                    CustomTextFieldWithButton(label = "닉네임",
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

                    //전화번호
                    CustomTextFieldWithButton(
                        label = "전화번호",
                        buttonLabel = "중복확인",
                        value = phoneNumber,
                        onValueChange = { newValue ->
                            if (newValue.text.all { it.isDigit() }) {
                                phoneNumber = newValue
                            }
                        },
                        onButtonClick = { viewModel.checkDuplicatePhoneNumber(phoneNumber.text) },
                        showIcon = checkMarkPhone.value,
                        error = phoneError
                    )

                    // 이메일
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

                    //이메일 중복확인
                    Button(
                        onClick = {
                            viewModel.checkDuplicateEmail(email)
                        },
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

                    // 은행 확인
                    CustomDropdown(selectedBank = account, onBankSelected = { bank ->
                        account = bank

                    })

                    // 계좌번호
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
                        onClick = {
                            if (checkIdResult.status + checkEmailResult.status + checkPhoneNumberResult.status + checkNickNameResult.status == 4&&validateUser(request)) {
                                viewModel.registerUser(request)
                                Signstatus = true
                            } else if(!validateUser(request)) {
                                showDialog2 = true
                            }else{
                                dialogMessage = "중복확인해주세요."
                                showDialog2 = true
                            }

                        },
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


// 성별 선택
@Composable
fun GenderSelection(label: String, gender: String, onGenderSelected: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontSize = 19.sp, fontWeight = FontWeight.Bold)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(16.dp)
        ) {
            Button(
                onClick = {
                    onGenderSelected("M")
                }, colors = ButtonDefaults.buttonColors(
                    if (gender == "M") BlueChecker else BrandColor1
                ), modifier = Modifier.weight(5f)
            ) {
                Text("남성", fontSize = 22.sp)
            }

            Button(
                onClick = {
                    onGenderSelected("F")
                }, colors = ButtonDefaults.buttonColors(
                    if (gender == "F") Color(0xFFEC9393) else BrandColor1
                ), modifier = Modifier.weight(5f)
            ) {
                Text("여성", fontSize = 22.sp)
            }
        }
    }
}

