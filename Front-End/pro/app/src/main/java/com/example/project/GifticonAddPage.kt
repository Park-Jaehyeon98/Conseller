package com.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.project.ui.theme.BrandColor1

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
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "ConSeller 기프티콘 등록 가이드 📋", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            CenteredTextColumn()
            Spacer(modifier = Modifier.height(23.dp))
            Button(
                colors = ButtonDefaults.buttonColors(BrandColor1),
                onClick = {navController.navigate("MyGifticonAddDetail")},
                modifier = Modifier
                    .size(250.dp, 50.dp) // 버튼의 크기 설정
            ) {
                Text(
                    text = "기프티콘 등록",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )

            }
        }
    }
}

@Composable
fun CenteredTextColumn() {
    Surface(shadowElevation = 6.dp, shape = RoundedCornerShape(12.dp)) {
        Column(
            modifier = Modifier
                .background(
                    color = Color.White, shape = RoundedCornerShape(12.dp)
                )
                .border( // 테두리 추가
                    width = 2.dp, color = Color(0xFFF76A4D), shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "1. 🖼️ 기프티콘의 원본 이미지를 업로드해주세요.\n시스템이 이미지를 자동으로 인식하고\n 기프티콘을 등록합니다.",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                "2. ✅ 등록 후 입력된 정보를 반드시 확인해주세요.\n올바르지 않은 정보가 있다면 수정해 주세요.\n 더 발전하는 ConSeller가 되겠습니다!",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                "3. ✔️ 기프티콘 등록을 완료해주세요.\n 완료 후 🎉 ConSeller를 즐겨주세요!",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}




