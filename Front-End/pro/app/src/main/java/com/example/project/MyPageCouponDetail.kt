package com.example.project

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.project.viewmodels.MyPageViewModel

@Composable
fun MyPageCouponDetail(navController: NavHostController, index: String?) {
    val viewModel: MyPageViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        index?.toLongOrNull()?.let {
            viewModel.getUserGifticonInfo(it)
        }
    }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val window = context.findWindow()
        val params = window.attributes
        params.screenBrightness = 1f
        window.attributes = params
    }


    val getMyGiftInfo by viewModel.getMyGifticonInfoResponse.collectAsState()

    getMyGiftInfo.gifticonAllImageUrl?.let { imageUrl ->
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

fun Context.findWindow(): Window {
    val context = this
    return when (context) {
        is Activity -> context.window
        is ContextWrapper -> context.baseContext.findWindow()
        else -> throw IllegalStateException("Window not found")
    }
}