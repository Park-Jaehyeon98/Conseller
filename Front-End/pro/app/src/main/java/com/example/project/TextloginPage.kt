package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TextLoginPage(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 로고용 이미지
            Text(text = "CONSELLER", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(300.dp)
            )
            //이미지 아래 공간
            Spacer(modifier = Modifier.height(12.dp))

            // 로그인 아이디 및 비밀번호 텍스트 입력창
            var loginText by remember { mutableStateOf(TextFieldValue("")) }
            var passwordText by remember { mutableStateOf(TextFieldValue("")) }
            Row(

            ){
                BasicTextField(
                    value = loginText,
                    onValueChange = { loginText = it },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(16.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(1.dp)
                    .background(Color.Black)
            )


            BasicTextField(
                value = passwordText,
                onValueChange = { passwordText = it },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(16.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(1.dp)
                    .background(Color.Black)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "아이디")
                Text(text = "/비밀번호")
                Text(text = "찾기")
            }
            Button(
                onClick = {}, colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text("로그인")
            }
            Button(
                onClick = { navController.navigate("SignUp") },
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text("회원가입")
            }
        }
    }
}

