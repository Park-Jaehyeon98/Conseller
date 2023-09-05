package com.example.project.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.project.api.ApiService;
import com.example.project.api.PatternVerificationRequest;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tJ\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/example/project/viewmodels/BiometricViewModel;", "Landroidx/lifecycle/ViewModel;", "apiService", "Lcom/example/project/api/ApiService;", "sharedPreferencesUtil", "Lcom/example/project/sharedpreferences/SharedPreferencesUtil;", "(Lcom/example/project/api/ApiService;Lcom/example/project/sharedpreferences/SharedPreferencesUtil;)V", "_authenticationState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/project/viewmodels/AuthenticationState;", "authenticationState", "Landroidx/lifecycle/LiveData;", "getAuthenticationState", "()Landroidx/lifecycle/LiveData;", "setAuthenticationState", "", "state", "verifyPattern", "pattern", "", "app_debug"})
public final class BiometricViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.project.api.ApiService apiService = null;
    private final com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil = null;
    private final androidx.lifecycle.MutableLiveData<com.example.project.viewmodels.AuthenticationState> _authenticationState = null;
    
    @javax.inject.Inject
    public BiometricViewModel(@org.jetbrains.annotations.NotNull
    com.example.project.api.ApiService apiService, @org.jetbrains.annotations.NotNull
    com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.project.viewmodels.AuthenticationState> getAuthenticationState() {
        return null;
    }
    
    public final void verifyPattern(@org.jetbrains.annotations.NotNull
    java.lang.String pattern) {
    }
    
    public final void setAuthenticationState(@org.jetbrains.annotations.NotNull
    com.example.project.viewmodels.AuthenticationState state) {
    }
}