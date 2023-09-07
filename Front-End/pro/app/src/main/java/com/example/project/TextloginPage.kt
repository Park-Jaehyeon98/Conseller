package com.example.project

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.time.format.TextStyle

@Composable
fun TextLoginPage(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(13.dp))
            // 로고용 이미지
            Text(text = "CONSELLER", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(500.dp, 250.dp)
            )

            var loginText by remember { mutableStateOf(TextFieldValue("")) }
            var passwordText by remember { mutableStateOf(TextFieldValue("")) }

            //아이디 입력창
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "Email",
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xFF1F59B6)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)  // Fill remaining space
                        .size(300.dp, 70.dp)
                        .padding(16.dp)
                ) {
                    BasicTextField(
                        value = loginText,
                        onValueChange = { loginText = it },
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 23.sp),
                        modifier = Modifier
                            .size(300.dp, 70.dp)

                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.Gray)
                            .align(Alignment.BottomStart)
                    )
                }
            }

            // 비밀번호 입력창
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.lock),
                    contentDescription = "Lock",
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xFF1F59B6)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)  // Fill remaining space
                        .size(300.dp, 70.dp)
                        .padding(16.dp)
                ) {
                    BasicTextField(
                        value = passwordText,
                        onValueChange = { passwordText = it },
                        visualTransformation = PasswordVisualTransformation(),
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 23.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopStart)
                    )

                    // Spacer to simulate the bottom border
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.Gray)
                            .align(Alignment.BottomStart)
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(end = 16.dp)
            ) {
                Spacer(modifier = Modifier.weight(0.2f))
                Text(text = "아이디", color = Color(0xFF1F59B6), modifier = Modifier.clickable {
                    navController.navigate("FindIdPage")
                })
                Text(
                    text = "/", color = Color(0xFF1F59B6)
                )
                Text(text = "비밀번호", color = Color(0xFF1F59B6), modifier = Modifier.clickable {
                    navController.navigate("FindPwPage")
                })
                Text(
                    text = " 찾기", color = Color(0xFF1F59B6) // Here, set the color
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            Button(
                onClick = { navController.navigate("Home") },
                Modifier.size(181.dp, 45.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF1F59B6))
            ) {
                Text("로그인", fontSize = 22.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { navController.navigate("SignUp") },
                Modifier.size(181.dp, 45.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF1F59B6))
            ) {
                Text("회원가입", fontSize = 22.sp)
            }
        }
    }
}
