package com.example.project.reuse_component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
                textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center),
                visualTransformation = visualTransformation,
                modifier = Modifier
                    .height(40.dp)
                    .weight(1f)
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
    showIcon: Boolean = false
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
                    textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center),
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .horizontalScroll(scrollState)
                )
            }
            Button(
                onClick = { onButtonClick() },
                modifier = Modifier
                    .weight(4f)
                    .fillMaxHeight()
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

