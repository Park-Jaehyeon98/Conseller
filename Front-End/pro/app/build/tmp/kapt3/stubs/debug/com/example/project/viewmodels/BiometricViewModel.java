package com.example.project.viewmodels;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.project.MyFirebaseMessagingService;
import com.example.project.PatternState;
import com.example.project.api.ErrorLoginResponse;
import com.example.project.api.IdPwLoginResponse;
import com.example.project.api.LoginService;
import com.example.project.api.PatternSaveRequest;
import com.example.project.api.PatternVerificationRequest;
import com.example.project.di.CustomException;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\tJ\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u000bJ\u000e\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\r8F\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\r8F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/project/viewmodels/BiometricViewModel;", "Landroidx/lifecycle/ViewModel;", "loginService", "Lcom/example/project/api/LoginService;", "sharedPreferencesUtil", "Lcom/example/project/sharedpreferences/SharedPreferencesUtil;", "(Lcom/example/project/api/LoginService;Lcom/example/project/sharedpreferences/SharedPreferencesUtil;)V", "_authenticationState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/project/viewmodels/AuthenticationState;", "_patternState", "Lcom/example/project/PatternState;", "authenticationState", "Lkotlinx/coroutines/flow/StateFlow;", "getAuthenticationState", "()Lkotlinx/coroutines/flow/StateFlow;", "patternState", "getPatternState", "authenticateWithBiometrics", "", "savePattern", "pattern", "", "setAuthenticationState", "state", "setPatternState", "verifyPattern", "app_debug"})
public final class BiometricViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.project.api.LoginService loginService = null;
    private final com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.viewmodels.AuthenticationState> _authenticationState = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.PatternState> _patternState = null;
    
    @javax.inject.Inject
    public BiometricViewModel(@org.jetbrains.annotations.NotNull
    com.example.project.api.LoginService loginService, @org.jetbrains.annotations.NotNull
    com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.viewmodels.AuthenticationState> getAuthenticationState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.PatternState> getPatternState() {
        return null;
    }
    
    public final void setPatternState(@org.jetbrains.annotations.NotNull
    com.example.project.PatternState state) {
    }
    
    public final void verifyPattern(@org.jetbrains.annotations.NotNull
    java.lang.String pattern) {
    }
    
    public final void savePattern(@org.jetbrains.annotations.NotNull
    java.lang.String pattern) {
    }
    
    public final void authenticateWithBiometrics() {
    }
    
    public final void setAuthenticationState(@org.jetbrains.annotations.NotNull
    com.example.project.viewmodels.AuthenticationState state) {
    }
}