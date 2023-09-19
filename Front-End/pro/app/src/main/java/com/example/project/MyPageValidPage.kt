package com.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.project.api.IdPwLoginRequest
import com.example.project.api.userValidRequest
import com.example.project.reuse_component.CustomTextField
import com.example.project.ui.theme.BrandColor1
import com.example.project.viewmodels.MyPageViewModel
import com.example.project.viewmodels.ResponseState

@Composable
fun MyPageValidPage(navController: NavHostController) {
    val viewModel: MyPageViewModel = hiltViewModel()

    val useridx=viewModel.getUserIdFromPreference()

    val checkUserValidState by viewModel.validUserResponse.collectAsState()

    var password by remember { mutableStateOf(TextFieldValue("")) }
    val checkUserValid = {
        val request = userValidRequest(
            userIdx = useridx,
            userPassword = password.text
        )
        viewModel.userValid(request)
    }

    LaunchedEffect(checkUserValidState) {
        if (checkUserValidState.status == -1) {
            navController.navigate("MyPageModify")
        } else {
            println(checkUserValidState.message)
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
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    CustomTextField(
                        label = "비밀번호 확인",
                        value = password,
                        onValueChange = { password = it },
                    )
                    Button(
                        onClick = {checkUserValid },
                        modifier = Modifier
                            .height(50.dp)
                            .clip(shape),
                        colors = ButtonDefaults.buttonColors(BrandColor1)
                    ) {
                        Text(
                            text = "확인", fontWeight = FontWeight.Bold
                        )
                    }

                }
            }
        }
    }
}