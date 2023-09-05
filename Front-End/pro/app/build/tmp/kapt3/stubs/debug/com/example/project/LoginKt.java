package com.example.project;

import androidx.biometric.BiometricPrompt;
import com.example.project.viewmodels.BiometricViewModel;
import com.example.project.viewmodels.AuthenticationState;
import androidx.compose.runtime.*;
import androidx.compose.ui.Modifier;
import androidx.compose.foundation.layout.*;
import androidx.compose.ui.Alignment;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavHostController;
import com.mrhwsn.composelock.ComposeLockCallback;
import com.mrhwsn.composelock.Dot;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a\u0010\u0010\t\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u00a8\u0006\u0010"}, d2 = {"BiometricAuthenticationButton", "", "fragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "viewModel", "Lcom/example/project/viewmodels/BiometricViewModel;", "LoginPage", "navController", "Landroidx/navigation/NavHostController;", "PatternAuthentication", "ShowAlertDialog", "message", "", "TemporaryNavigationButtons", "rememberBiometricPrompt", "Landroidx/biometric/BiometricPrompt;", "app_debug"})
public final class LoginKt {
    
    @androidx.compose.runtime.Composable
    public static final void LoginPage(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void PatternAuthentication(@org.jetbrains.annotations.NotNull
    com.example.project.viewmodels.BiometricViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void TemporaryNavigationButtons(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void BiometricAuthenticationButton(@org.jetbrains.annotations.Nullable
    androidx.fragment.app.FragmentActivity fragmentActivity, @org.jetbrains.annotations.NotNull
    com.example.project.viewmodels.BiometricViewModel viewModel) {
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