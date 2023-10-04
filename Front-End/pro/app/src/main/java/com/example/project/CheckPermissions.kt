package com.example.project

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.project.sharedpreferences.SharedPreferencesUtil

@Composable
fun CheckPermission(navController: NavHostController, sharedPreferencesUtil: SharedPreferencesUtil) {
    val context = LocalContext.current
    val showPermissionDialog = remember { mutableStateOf(false) }

    val permissions = if (Build.VERSION.SDK_INT >= 33) {
        arrayOf(
            android.Manifest.permission.READ_MEDIA_IMAGES
        )
    } else {
        arrayOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            sharedPreferencesUtil.setPermissionsChecked()
            navController.navigate("TextLoginPage")
        } else {
            showPermissionDialog.value = true
        }
    }

    LaunchedEffect(Unit) {
        val permissionsNotGranted = permissions.filter {
            ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
        }
        if (permissionsNotGranted.isNotEmpty()) {
            launcherMultiplePermissions.launch(permissionsNotGranted.toTypedArray())
        }
    }

    if (showPermissionDialog.value) {
        AlertDialog(
            onDismissRequest = { showPermissionDialog.value = false; (context as? Activity)?.finish() },
            title = { Text(text = "권한 알림") },
            text = { Text("권한이 없으면 앱 사용이 불가합니다.") },
            confirmButton = {
                Button(onClick = { showPermissionDialog.value = false; (context as? Activity)?.finish() }) {
                    Text("확인")
                }
            }
        )
    }
}