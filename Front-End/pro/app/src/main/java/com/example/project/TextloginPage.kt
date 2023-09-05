package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TextLoginPage(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 로고용 이미지
            Image(
                painter = painterResource(id = R.drawable.alert),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(150.dp)
            )
            //이미지 아래 공간
            Spacer(modifier = Modifier.height(32.dp))

            // 로그인 아이디 및 비밀번호 텍스트 입력창
            var loginText by remember { mutableStateOf(TextFieldValue("")) }
            var passwordText by remember { mutableStateOf(TextFieldValue("")) }

            BasicTextField(
                value = loginText,
                onValueChange = { loginText = it },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .background(Color.Gray)
                    .padding(16.dp)
            )

            BasicTextField(
                value = passwordText,
                onValueChange = { passwordText = it },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .background(Color.Gray)
                    .padding(16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "아이디/비밀번호 찾기", modifier = Modifier.weight(1f))
                Button(onClick = {}) {
                    Text("로그인")
                }
            }

            Button(onClick = {}) {
                Text("회원가입")
            }
        }
    }
}
