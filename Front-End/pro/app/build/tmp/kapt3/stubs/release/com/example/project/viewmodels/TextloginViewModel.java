package com.example.project.viewmodels;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.project.MyFirebaseMessagingService;
import com.example.project.api.LoginService;
import com.example.project.api.IdPwLoginRequest;
import com.example.project.api.IdPwLoginResponse;
import com.example.project.api.accessToken;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\u001e\u001a\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u0006\u0010&\u001a\u00020#R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\'"}, d2 = {"Lcom/example/project/viewmodels/TextloginViewModel;", "Landroidx/lifecycle/ViewModel;", "loginService", "Lcom/example/project/api/LoginService;", "sharedPreferencesUtil", "Lcom/example/project/sharedpreferences/SharedPreferencesUtil;", "(Lcom/example/project/api/LoginService;Lcom/example/project/sharedpreferences/SharedPreferencesUtil;)V", "_checkError", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_getAccessToken", "Lcom/example/project/api/accessToken;", "_idPwLoginState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/project/viewmodels/ResponseState;", "Lcom/example/project/api/IdPwLoginResponse;", "checkError", "Lkotlinx/coroutines/flow/StateFlow;", "getCheckError", "()Lkotlinx/coroutines/flow/StateFlow;", "firebaseService", "Lcom/example/project/MyFirebaseMessagingService;", "getFirebaseService", "()Lcom/example/project/MyFirebaseMessagingService;", "getaccessToken", "getGetaccessToken", "idPwLoginState", "Landroidx/lifecycle/LiveData;", "getIdPwLoginState", "()Landroidx/lifecycle/LiveData;", "getAccessToken", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserIdFromPreference", "", "loginWithIdPw", "", "request", "Lcom/example/project/api/IdPwLoginRequest;", "reSetPreference", "app_release"})
public final class TextloginViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.project.api.LoginService loginService = null;
    private final com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.project.MyFirebaseMessagingService firebaseService = null;
    private final androidx.lifecycle.MutableLiveData<com.example.project.viewmodels.ResponseState<com.example.project.api.IdPwLoginResponse>> _idPwLoginState = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.example.project.viewmodels.ResponseState<com.example.project.api.IdPwLoginResponse>> idPwLoginState = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.accessToken> _getAccessToken = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _checkError = null;
    
    @javax.inject.Inject
    public TextloginViewModel(@org.jetbrains.annotations.NotNull
    com.example.project.api.LoginService loginService, @org.jetbrains.annotations.NotNull
    com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.MyFirebaseMessagingService getFirebaseService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.project.viewmodels.ResponseState<com.example.project.api.IdPwLoginResponse>> getIdPwLoginState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.accessToken> getGetaccessToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getCheckError() {
        return null;
    }
    
    public final long getUserIdFromPreference() {
        return 0L;
    }
    
    public final void reSetPreference() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAccessToken(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    public final void loginWithIdPw(@org.jetbrains.annotations.NotNull
    com.example.project.api.IdPwLoginRequest request) {
    }
}