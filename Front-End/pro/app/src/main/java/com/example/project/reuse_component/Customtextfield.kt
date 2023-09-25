package com.example.project.reuse_component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.R
import com.example.project.ui.theme.BrandColor1

@Composable
fun CustomTextField(
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    error: String? = null,
    showIcon: Boolean = false

//    Keyboard: KeyboardType = KeyboardType.Text
) {
    val scrollState = rememberScrollState()
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (showIcon) {
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = "check",
                    modifier = Modifier
                        .size(20.dp)
                        .offset(y = (-4).dp),
                    tint = Color(0xFFF76A4D)
                )
            }
            Text(text = label, fontSize = 19.sp, fontWeight = FontWeight.Bold)
        }
        if (error != null) {
            Text(text = error, fontSize = 15.sp, color = Color.Red)  // 오류 메시지를 빨간색으로 표시
        }
        Surface(
            modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(8.dp))
                .fillMaxWidth()
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Left),
                visualTransformation = visualTransformation,
                modifier = Modifier
                    .height(40.dp)
                    .weight(1f)
                    .padding(start=8.dp)
                    .horizontalScroll(scrollState)
            )
        }
    }
}

@Composable
fun CustomTextFieldWithButton(
    label: String,
    buttonLabel: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    onButtonClick: () -> Unit,
    showIcon: Boolean = false,
    error: String? = null,
) {
    val shape = RoundedCornerShape(8.dp) // 모서리 둥글게 만들기 위한 shape 정의
    val scrollState = rememberScrollState()

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (showIcon) {
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = "check",
                    modifier = Modifier
                        .size(20.dp)
                        .offset(y = (-4).dp),
                    tint = Color(0xFFF76A4D)
                )
            }
            Text(text = label, fontSize = 19.sp, fontWeight = FontWeight.Bold)
        }
        if (error != null) {
            Text(text = error, fontSize = 15.sp, color = Color.Red)  // 오류 메시지를 빨간색으로 표시
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                modifier = Modifier
                    .shadow(4.dp, shape)
                    .fillMaxWidth()
                    .weight(7f)
                    .clip(shape)
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Left),
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .padding(start=8.dp)
                        .horizontalScroll(scrollState)
                )
            }
            Button(
                onClick = { onButtonClick() },
                modifier = Modifier
                    .weight(4f)
//                    .fillMaxHeight()
                    .clip(shape),
                colors = ButtonDefaults.buttonColors(BrandColor1)
            ) {
                Text(
                    text = buttonLabel, fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun EmailTextFieldWithDomain(
    label: String,
    emailValue: String,
    onValueChange: (String) -> Unit,
    selectedDomain: String = "이메일 선택",
    onDomainSelected: (String) -> Unit,
    showIcon: Boolean,
    error: String? = null,
) {
    val domains = listOf("gmail.com", "yahoo.com", "hotmail.com", "naver.com", "직접입력")
    var expanded by remember { mutableStateOf(false) }
    var showDomainTextField by remember { mutableStateOf(false) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (showIcon) {
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = "check",
                    modifier = Modifier
                        .size(20.dp)
                        .offset(y = (-4).dp),
                    tint = Color(0xFFF76A4D)
                )
            }
            Text(text = label, fontSize = 19.sp, fontWeight = FontWeight.Bold)
        }
        if (error != null) {
            Text(text = error, fontSize = 15.sp, color = Color.Red)  // 오류 메시지를 빨간색으로 표시
        }
        Surface(
            modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(8.dp))
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                val scrollState = rememberScrollState()
                val focusRequester = remember { FocusRequester() }
                var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

                BasicTextField(
                    value = emailValue,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Right),
                    maxLines = 1,
                    onTextLayout = { textLayoutResult = it },
                    modifier = Modifier
                        .weight(5f)
                        .padding(end = 8.dp)
                        .horizontalScroll(scrollState)
                        .focusRequester(focusRequester)
                )

                LaunchedEffect(emailValue) {
                    if (emailValue.isNotEmpty()) {
                        focusRequester.requestFocus()
                        textLayoutResult?.let {
                            val cursorPosition = it.getCursorRect(emailValue.length).right.toInt()
                            if (cursorPosition > scrollState.maxValue) {
                                scrollState.animateScrollTo(cursorPosition)
                            }
                        }
                    }
                }
                Text(
                    text = "@",
                    style = TextStyle(color = Color.Black, fontSize = 22.sp),
                    modifier = Modifier.padding(end = 8.dp)
                )

                Box(modifier = Modifier.weight(4f)) {
                    val scrollState = rememberScrollState()
                    if (showDomainTextField) {
                        BasicTextField(
                            value = selectedDomain,
                            onValueChange = onDomainSelected,
                            textStyle=TextStyle(fontSize=20.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(BrandColor1, RoundedCornerShape(8.dp))
                                .padding(start=8.dp)
                                .horizontalScroll(scrollState)
                        )
                    } else {
                        Text(text = when (selectedDomain) {
                             "직접입력" -> ""
                            else -> selectedDomain
                        },
                            style = TextStyle(fontSize = 22.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expanded = !expanded }
                                .background(BrandColor1, RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp))
                    }

                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        domains.forEach { domain ->
                            DropdownMenuItem(text = { Text(domain, fontSize = 16.sp)}, onClick = {
                                if (domain == "직접입력") {
                                    showDomainTextField = true
                                    onDomainSelected("")
                                } else {
                                    onDomainSelected(domain)
                                    showDomainTextField = false
                                }
                                expanded = false
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomDropdown(
    selectedBank: String,
    onBankSelected: (String) -> Unit,
    error: String? = null,
) {
    val banks = listOf("신한은행", "국민은행", "카카오페이", "우리은행", "하나은행", "농협은행")
    var expanded by remember { mutableStateOf(false) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "계좌은행", fontSize = 19.sp, fontWeight = FontWeight.Bold)
        }
        Surface(
            modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(8.dp))
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .wrapContentHeight()
            ) {
                Text(
                    text = selectedBank,
                    fontSize = 19.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { expanded = !expanded })
                        .padding(8.dp)
                )
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    banks.forEach { bank ->
                        DropdownMenuItem(text = { Text(bank, fontSize = 22.sp) }, onClick = {
                            onBankSelected(bank)
                            expanded = false
                        })
                    }
                }
            }
        }
    }
}


@Composable
fun CustomGiftTextField(
    label:String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,

//    Keyboard: KeyboardType = KeyboardType.Text
) {
    val scrollState = rememberScrollState()
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
        }
        Text(text=label, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Surface(
            modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(8.dp))
                .background(BrandColor1)
                .fillMaxWidth(0.8f)
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Left, color = Color.White),
                modifier = Modifier
                    .height(40.dp)
                    .weight(0.8f)
                    .padding(start=8.dp)
                    .horizontalScroll(scrollState)
                    .background(BrandColor1)
            )
        }
    }
}