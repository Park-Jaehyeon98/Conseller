package com.example.project;

import android.net.Uri;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.navigation.NavHostController;
import com.example.project.api.RegistRequest;
import com.example.project.api.userModifyRequest;
import com.example.project.viewmodels.MyPageViewModel;
import com.example.project.viewmodels.SignupViewModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0007\u001a\u0014\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0007\u00a8\u0006\r"}, d2 = {"ModifyUserProfile", "", "initNickname", "", "initPassword", "initEmail", "initAccout", "initAccoutBank", "MyPageModifyPage", "navController", "Landroidx/navigation/NavHostController;", "ProfileModifyImage", "profileImage", "app_debug"})
public final class MyPageModifyPageKt {
    
    @androidx.compose.runtime.Composable
    public static final void MyPageModifyPage(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ProfileModifyImage(@org.jetbrains.annotations.Nullable
    java.lang.String profileImage) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ModifyUserProfile(@org.jetbrains.annotations.NotNull
    java.lang.String initNickname, @org.jetbrains.annotations.NotNull
    java.lang.String initPassword, @org.jetbrains.annotations.NotNull
    java.lang.String initEmail, @org.jetbrains.annotations.NotNull
    java.lang.String initAccout, @org.jetbrains.annotations.NotNull
    java.lang.String initAccoutBank) {
    }
}