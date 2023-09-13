package com.example.project.reuse_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.project.R

@Composable
fun UserProfile(
    profileImage: String?=null,
    userNickName: String,
    userEmail:String,
    userPhoneNumber:String
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (profileImage != null) {
            Image(
                painter = rememberAsyncImagePainter(profileImage),
                contentDescription = "유저 프로필 이미지",
                modifier = Modifier.size(100.dp).clip(CircleShape)  // 크기 조절
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Default User Image",
                modifier = Modifier.size(100.dp).clip(CircleShape)
            )
        }
        Text(text=userNickName)
        Text(text=userEmail)
        Text(text=userPhoneNumber)

    }
}
