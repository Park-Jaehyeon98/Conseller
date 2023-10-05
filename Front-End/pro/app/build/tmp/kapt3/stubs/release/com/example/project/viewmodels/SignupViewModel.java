package com.example.project.viewmodels;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.project.api.BasicResponse;
import com.example.project.api.RegistRequest;
import com.example.project.api.SignupService;
import com.example.project.api.findIdRequest;
import com.example.project.api.findIdResponse;
import com.example.project.api.findPwRequest;
import com.example.project.di.CustomException;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0014J\u000e\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020\u0014J\u000e\u0010.\u001a\u00020*2\u0006\u0010/\u001a\u00020\u0014J\u000e\u00100\u001a\u00020*2\u0006\u00101\u001a\u00020\u0014J\u000e\u00102\u001a\u00020*2\u0006\u00103\u001a\u000204J\u000e\u00105\u001a\u00020*2\u0006\u00103\u001a\u000206J\u0006\u00107\u001a\u00020*J\u000e\u00108\u001a\u00020*2\u0006\u00103\u001a\u000209R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u00178F\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u00178F\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u00178F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u0019R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\u00178F\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010\u0019R\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00178F\u00a2\u0006\u0006\u001a\u0004\b#\u0010\u0019R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00100\u00178F\u00a2\u0006\u0006\u001a\u0004\b%\u0010\u0019R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00100\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00120\u00178F\u00a2\u0006\u0006\u001a\u0004\b(\u0010\u0019\u00a8\u0006:"}, d2 = {"Lcom/example/project/viewmodels/SignupViewModel;", "Landroidx/lifecycle/ViewModel;", "service", "Lcom/example/project/api/SignupService;", "sharedPreferencesUtil", "Lcom/example/project/sharedpreferences/SharedPreferencesUtil;", "(Lcom/example/project/api/SignupService;Lcom/example/project/sharedpreferences/SharedPreferencesUtil;)V", "_CheckEmailResponse", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/project/api/BasicResponse;", "_CheckIdResponse", "_CheckNickNameResponse", "_CheckPhoneNumberResponse", "_FindId", "Lcom/example/project/api/findIdResponse;", "_FindPw", "", "_SignupResponse", "", "_error", "", "_isLoading", "checkEmail", "Lkotlinx/coroutines/flow/StateFlow;", "getCheckEmail", "()Lkotlinx/coroutines/flow/StateFlow;", "checkId", "getCheckId", "checkNickname", "getCheckNickname", "checkPhoneNumber", "getCheckPhoneNumber", "error", "getError", "findId", "getFindId", "findPw", "getFindPw", "isLoading", "signupresponse", "getSignupresponse", "checkDuplicateEmail", "", "email", "checkDuplicateId", "userId", "checkDuplicateNickname", "nickname", "checkDuplicatePhoneNumber", "phoneNumber", "findUserId", "request", "Lcom/example/project/api/findIdRequest;", "findUserPw", "Lcom/example/project/api/findPwRequest;", "initSignUpResult", "registerUser", "Lcom/example/project/api/RegistRequest;", "app_release"})
public final class SignupViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.project.api.SignupService service = null;
    private final com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _SignupResponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.BasicResponse> _CheckEmailResponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.BasicResponse> _CheckNickNameResponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.BasicResponse> _CheckPhoneNumberResponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.BasicResponse> _CheckIdResponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.findIdResponse> _FindId = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _FindPw = null;
    
    @javax.inject.Inject
    public SignupViewModel(@org.jetbrains.annotations.NotNull
    com.example.project.api.SignupService service, @org.jetbrains.annotations.NotNull
    com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getSignupresponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.BasicResponse> getCheckEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.BasicResponse> getCheckNickname() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.BasicResponse> getCheckPhoneNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.BasicResponse> getCheckId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.findIdResponse> getFindId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getFindPw() {
        return null;
    }
    
    public final void findUserId(@org.jetbrains.annotations.NotNull
    com.example.project.api.findIdRequest request) {
    }
    
    public final void findUserPw(@org.jetbrains.annotations.NotNull
    com.example.project.api.findPwRequest request) {
    }
    
    public final void initSignUpResult() {
    }
    
    public final void registerUser(@org.jetbrains.annotations.NotNull
    com.example.project.api.RegistRequest request) {
    }
    
    public final void checkDuplicateNickname(@org.jetbrains.annotations.NotNull
    java.lang.String nickname) {
    }
    
    public final void checkDuplicateEmail(@org.jetbrains.annotations.NotNull
    java.lang.String email) {
    }
    
    public final void checkDuplicateId(@org.jetbrains.annotations.NotNull
    java.lang.String userId) {
    }
    
    public final void checkDuplicatePhoneNumber(@org.jetbrains.annotations.NotNull
    java.lang.String phoneNumber) {
    }
}