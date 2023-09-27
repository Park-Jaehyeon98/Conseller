package com.example.project

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.api.RegistRequest
import com.example.project.api.userModifyRequest
import com.example.project.reuse_component.CustomDropdown
import com.example.project.reuse_component.CustomTextField
import com.example.project.reuse_component.CustomTextFieldWithButton
import com.example.project.reuse_component.EmailTextFieldWithDomain
import com.example.project.ui.theme.BrandColor1
import com.example.project.viewmodels.MyPageViewModel
import com.example.project.viewmodels.SignupViewModel
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@Composable
fun MyPageModifyPage(navController: NavHostController) {
    // MyPage 뷰모델
    val viewModel: MyPageViewModel = hiltViewModel()
    // 개인정보 뷰모델
    // 개인정보 불러오는 로직
    val checkIdResult by viewModel.getMyinfoResponse.collectAsState()
    val chekModifyState by viewModel.modifyUserResponse.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState(initial = false)
    //스크롤
    val scrollState = rememberScrollState()
    // 개인정보
    LaunchedEffect(Unit) {
        viewModel.getMyInfo()
    }

    LaunchedEffect(chekModifyState) {
        if (chekModifyState.code == "200") {
            navController.navigate("MyPage")
        } else {
            println(chekModifyState.message)
        }
    }
    // 메인
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ProfileModifyImage(checkIdResult.userProfileUrl)
        // 나머지 회원정보 수정
        // getmyinfo가 완성되면 실행되게끔
        if (!isLoading) {
            ModifyUserProfile(
                initNickname = checkIdResult.userNickname,
                initPassword = checkIdResult.userPassword,
                initEmail = checkIdResult.userEmail,
                initAccout = checkIdResult.userAccountBank,
                initAccoutBank = checkIdResult.userAccount
            )
        }

    }


}


@Composable
fun ProfileModifyImage(
    profileImage: String? = null
) {
    val viewModel: MyPageViewModel = hiltViewModel()
    var selectImage by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            selectImage = uri
        }
    val currentImage = selectImage

    LaunchedEffect(selectImage) { // selectImage의 변화를 감지
        val currentImage = selectImage
        if (currentImage != null) {
            val inputStream = getInputStreamFromUri(context, currentImage)
            val byteArray = getBytesFromInputStream(inputStream!!)

            // RequestBody 생성
            val requestFile: RequestBody = byteArray.toRequestBody("image/*".toMediaTypeOrNull())

            // MultipartBody.Part 생성
            val multipartImage: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file", "profile2.jpg", requestFile
            )

            Log.d("UserProfile", "Multipart data length: ${byteArray.size}")
            viewModel.profileSend(multipartImage) // 이미지 변화 시 viewModel을 통해 업로드
        }
    }


    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val imageToShow = selectImage ?: profileImage
        if (imageToShow != null) {
            Image(
                painter = rememberAsyncImagePainter(imageToShow),
                contentDescription = "유저 프로필 이미지",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillHeight
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.defaultimage),
                contentDescription = "Default User Image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillHeight
            )
        }
        Button(
            onClick = { galleryLauncher.launch("image/*") }, colors = ButtonDefaults.buttonColors(
                BrandColor1
            )
        ) {
            Text(text = "사진 수정")
        }
    }
}

@Composable
fun ModifyUserProfile(
    initNickname: String,
    initPassword: String,
    initEmail: String,
    initAccout: String,
    initAccoutBank: String
) {
    val mypageModel: MyPageViewModel = hiltViewModel()


    val checkModel: SignupViewModel = hiltViewModel()

    var modifyNickname by remember { mutableStateOf(TextFieldValue(initNickname)) }
    var modifyPassword by remember { mutableStateOf(TextFieldValue("")) }
    var modifyCheckPassword by remember { mutableStateOf(TextFieldValue("")) }
    var modifyEmail by remember { mutableStateOf(initEmail) }
    var modifyAccount by remember { mutableStateOf(initAccout) }
    var modifyAccountBank by remember { mutableStateOf(TextFieldValue(initAccoutBank)) }

    val emailParts = initEmail.split("@")
    var emailPart by remember { mutableStateOf(emailParts.getOrNull(0) ?: "") }
    var Domain by remember { mutableStateOf(emailParts.getOrNull(1) ?: "gmail.com") }

    val checkMarkNickname = remember { mutableStateOf(false) }
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

    //유효성 검사 상태들
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var checkPasswordError by remember { mutableStateOf<String?>(null) }
    var nickNameError by remember { mutableStateOf<String?>(null) }

    val request = userModifyRequest(
        userAccount = modifyAccount,
        userEmail = modifyEmail,
        userPassword = modifyPassword.text.takeIf { it.isNotBlank() } ?: initPassword,
        userAccountBank = modifyAccountBank.text,
        userNickname = modifyNickname.text,
    )


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
            // 닉네임
            CustomTextFieldWithButton(label = "닉네임",
                buttonLabel = "중복확인",
                value = modifyNickname,
                onValueChange = {
                    modifyNickname = it
                    nickNameError =
                        if (isValidNickName(it.text)) null else "닉네임은 1글자 이상 45글자 이하여야 합니다."
                },
                onButtonClick = { checkModel.checkDuplicateNickname(modifyNickname.text) },
                showIcon = checkMarkNickname.value,
                error = nickNameError
            )
            //비밀번호
            CustomTextField(
                label = "비밀번호", value = modifyPassword,
                onValueChange = {
                    modifyPassword = it
                    passwordError =
                        if (isValidPassword(it.text)) null else "비밀번호는 8자리 이상\n특수 문자, 영문, 숫자를 1개이상 포함해야 합니다."
                },
                visualTransformation = PasswordVisualTransformation(),
                // 비밀번호 필드 아래에 오류 메시지 표시
                error = passwordError,
                showIcon = isValidPassword(modifyPassword.text) && modifyCheckPassword.text == modifyPassword.text,
            )
            //비밀번호 확인
            CustomTextField(
                label = "비밀번호 확인", value = modifyCheckPassword, onValueChange = {
                    modifyCheckPassword = it
                    checkPasswordError =
                        if (it.text == modifyPassword.text) null else "비밀번호가 일치하지 않습니다."
                }, visualTransformation = PasswordVisualTransformation(),
                // 비밀번호 확인 필드 아래에 오류 메시지 표시
                error = checkPasswordError
            )
            //이메일 확인
            EmailTextFieldWithDomain(
                label = "이메일",
                emailValue = emailPart,
                onValueChange = { newEmailPart ->
                    emailPart = newEmailPart
                    modifyEmail = "$newEmailPart@$Domain"
                    emailError = if (isValidEmail(modifyEmail)) null else "이메일 형식이 틀립니다."
                },
                error = emailError,
                selectedDomain = Domain,
                onDomainSelected = { newDomain ->
                    Domain = newDomain
                    modifyEmail = "$emailPart@$Domain"
                    emailError = if (isValidEmail(modifyEmail)) null else "이메일 형식이 틀립니다."
                },
                showIcon = checkMarkEmail.value,
            )
            //계좌은행
            CustomDropdown(selectedBank = modifyAccount, onBankSelected = { bank ->
                modifyAccount = bank

            })
            //계좌번호
            CustomTextField(label = "계좌번호", value = modifyAccountBank, onValueChange = { newValue ->
                if (newValue.text.all { it.isDigit() }) {
                    modifyAccountBank = newValue
                }
            })
            Button(
                onClick = {       mypageModel.userModify(request) },
                Modifier.size(120.dp, 40.dp),
                colors = ButtonDefaults.buttonColors(BrandColor1)
            ) {
                Text("정보 수정", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

        }
    }
}