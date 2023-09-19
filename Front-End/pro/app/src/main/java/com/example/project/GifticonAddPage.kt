package com.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun GifticonAddPage(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(23.dp)
        ) {
            Text(
                text = "ConSeller 기프티콘 등록",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(text = "ConSeller 기프티콘 등록 가이드 📋", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            CenteredTextColumn()
            Button(
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                onClick = {},
                modifier = Modifier
                    .size(150.dp, 50.dp) // 버튼의 크기 설정
                    .border(
                        width = 3.dp, color = Color(0xFFF76A4D), shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Text(text = "기프티콘 등록", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CenteredTextColumn() {
    Surface(shadowElevation = 6.dp, shape = RoundedCornerShape(12.dp)) {
        Column(
            modifier = Modifier
                .background( // 안내문 배경색 및 스타일 설정
                    color = Color.White, // 배경색은 원하는 색상으로 변경 가능
                    shape = RoundedCornerShape(12.dp) // 라운드된 모서리
                )
                .border( // 테두리 추가
                    width = 2.dp, color = Color(0xFFF76A4D), // 테두리 색상은 원하는 대로 변경 가능
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp), // 안내문 내부에 대한 여백 추가
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "1. 🖼️ 기프티콘의 원본 이미지를 업로드해주세요.\n시스템이 이미지를 자동으로 인식하고\n 기프티콘을 등록합니다.",
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "2. ✅ 등록 후 입력된 정보를 반드시 확인해 주세요.\n올바르지 않은 정보가 있다면 수정해 주세요.\n 더 발전하는 ConSeller가 되겠습니다!",
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "3. ✔️ 기프티콘 등록을 완료해주세요.\n 완료 후 🎉 ConSeller를 즐겨주세요!",
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}




