package com.example.project.api;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\t\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\b\b\u0001\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u00112\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\u0012\u001a\u00020\u0013H\'J!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\r\u001a\u00020\u0015H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\r\u001a\u00020\u0018H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/example/project/api/LoginService;", "", "accessToken", "Lretrofit2/Response;", "Lcom/example/project/api/accessToken;", "useridx", "", "fingerLogin", "Lcom/example/project/api/IdPwLoginResponse;", "userIdx", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "savePattern", "Ljava/lang/Void;", "request", "Lcom/example/project/api/PatternSaveRequest;", "(Lcom/example/project/api/PatternSaveRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendToken", "Lretrofit2/Call;", "token", "Lcom/example/project/api/firebaseToken;", "textLogin", "Lcom/example/project/api/IdPwLoginRequest;", "(Lcom/example/project/api/IdPwLoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyPattern", "Lcom/example/project/api/PatternVerificationRequest;", "(Lcom/example/project/api/PatternVerificationRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface LoginService {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/user/verifypattern")
    public abstract java.lang.Object verifyPattern(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.PatternVerificationRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.IdPwLoginResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/user/finger/{userIdx}")
    public abstract java.lang.Object fingerLogin(@retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.IdPwLoginResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/user/savepattern")
    public abstract java.lang.Object savePattern(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.PatternSaveRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/user/login")
    public abstract java.lang.Object textLogin(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.IdPwLoginRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.IdPwLoginResponse>> continuation);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "api/user/firebaseToken/{userIdx}")
    public abstract retrofit2.Call<java.lang.Void> sendToken(@retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.firebaseToken token);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "/api/user/refresh/{userIdx}")
    public abstract retrofit2.Response<com.example.project.api.accessToken> accessToken(@retrofit2.http.Path(value = "userIdx")
    long useridx);
}