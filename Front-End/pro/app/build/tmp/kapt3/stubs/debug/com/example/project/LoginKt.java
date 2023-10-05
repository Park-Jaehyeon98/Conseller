package com.example.project;

import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import com.example.project.viewmodels.BiometricViewModel;
import com.example.project.viewmodels.AuthenticationState;
import androidx.compose.runtime.*;
import androidx.compose.ui.Modifier;
import androidx.compose.foundation.layout.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.text.font.FontWeight;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavHostController;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import com.example.project.viewmodels.FireBaseViewModel;
import com.mrhwsn.composelock.ComposeLockCallback;
import com.mrhwsn.composelock.Dot;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0007\u001a\u0010\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a\u0010\u0010\r\u001a\u00020\u00012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u001a\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\b\u001a\u00020\tH\u0007\u00a8\u0006\u0013"}, d2 = {"LoginPage", "", "navController", "Landroidx/navigation/NavHostController;", "sharedPreferencesUtil", "Lcom/example/project/sharedpreferences/SharedPreferencesUtil;", "NavigationButtons", "PatternAuthentication", "viewModel", "Lcom/example/project/viewmodels/BiometricViewModel;", "ShowAlertDialog", "message", "", "authenticateWithBiometrics", "biometricPrompt", "Landroidx/biometric/BiometricPrompt;", "rememberBiometricPrompt", "fragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "app_debug"})
public final class LoginKt {
    
    @androidx.compose.runtime.Composable
    public static final void LoginPage(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull
    com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void PatternAuthentication(@org.jetbrains.annotations.NotNull
    com.example.project.viewmodels.BiometricViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void NavigationButtons(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController) {
    }
    
    public static final void authenticateWithBiometrics(@org.jetbrains.annotations.Nullable
    androidx.biometric.BiometricPrompt biometricPrompt) {
    }
    
    @org.jetbrains.annotations.Nullable
    @androidx.compose.runtime.Composable
    public static final androidx.biometric.BiometricPrompt rememberBiometricPrompt(@org.jetbrains.annotations.Nullable
    androidx.fragment.app.FragmentActivity fragmentActivity, @org.jetbrains.annotations.NotNull
    com.example.project.viewmodels.BiometricViewModel viewModel) {
        return null;
    }
    
    @androidx.compose.runtime.Composable
    public static final void ShowAlertDialog(@org.jetbrains.annotations.NotNull
    java.lang.String message) {
    }
}